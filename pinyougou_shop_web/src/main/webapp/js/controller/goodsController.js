//控制层
app.controller('goodsController', function ($scope, $controller, goodsService) {

    $controller('baseController', {$scope: $scope});//继承
    $scope.entity = {"goodsDesc": {}, "goods": {}, "item": []};
    $scope.addInfo = () => {
        $scope.entity.goodsDesc.introduction = editor.html();
        alert(editor.html())
        goodsService.addInfo($scope.entity).success(response => {
            if (response.success) {
                editor.html('');
            } else {
                alert(response.message);
            }
        })
    }
    //获取一级列表菜单
    $scope.getLevel = (id) => {
        goodsService.getLevel(id).success(response => {
            //一级列表发生变化要清理二级三级列表
            $scope.levelList = response;
            $scope.levelList1 = [];
            $scope.levelList2 = [];
        })
    }
    //获取二级列表菜单
    $scope.$watch('entity.goods.category1Id', function (newValue, oldValue) {
        goodsService.getLevel(newValue).success(response => {
            $scope.levelList1 = response;
            $scope.levelList2 = [];
        })
    })
    //获取三级列表菜单
    $scope.$watch("entity.goods.category2Id", (newValue, oldValue) => {
        goodsService.getLevel(newValue).success(response => {
            $scope.levelList2 = response;
        })
    })
    //监视三级菜单列表的变化来获取相应的模板id
    $scope.$watch("entity.goods.category3Id", (newValue, oldValue) => {
        for (let i = 0; i < $scope.levelList2.length; i++) {
            if ($scope.levelList2[i].id == newValue) {
                $scope.templateId = $scope.levelList2[i].typeId;
            }
        }
    })
    //监视模板id是否发生变化，一旦发生变化就要去获取品牌数据
    $scope.$watch("templateId", (newValue, oldValue) => {
        goodsService.getTemplateInfo(newValue).success(response => {
            $scope.templateList = [];
            //转换数据格式
            $scope.templateList = $scope.transferFormat(response.brandIds);
            //这一步是多余的可以与上一行代码合并
            $scope.entity.goods.typeTemplateId = $scope.templateId;
            $scope.entity.goodsDesc.customAttributeItems = $scope.transferFormat(response.customAttributeItems)
            //得到相应的规格id返回数据查出相应的数据\

            $scope.specificationItems = $scope.transferFormat(response.specIds);
            //不想改动其它数据添加以下代码实现查询功能
            $scope.ids = [];
            for (let i = 0; i < $scope.specificationItems.length; i++) {
                $scope.ids.push($scope.specificationItems[i].id);
            }
            goodsService.getspecs($scope.ids).success(response => {
                $scope.specInfos = response;
            })
        })
    })
    $scope.transferFormat = (origin) => {
        let transArray = origin.split("},");
        returnValue = [];
        for (let i = 0; i < transArray.length; i++) {
            if (i == 0) {
                returnValue.push(JSON.parse(transArray[i].split("[")[1] + "}"));
            } else if (i == transArray.length - 1) {
                returnValue.push(JSON.parse(transArray[i].split("]")[0]));
            } else {
                returnValue.push(JSON.parse(transArray[i] + "}"));
            }
        }
        return returnValue;
    }
    $scope.entity = {goods: {}, goodsDesc: {itemImages: [], specificationItems: []}, items: []};
    $scope.updateSelect = (event, specName, optionName) => {
        let obj = null;
        for (let i = 0; i < $scope.entity.goodsDesc.specificationItems.length; i++) {
            if ($scope.entity.goodsDesc.specificationItems[i].attributeName == specName) {
                obj = $scope.entity.goodsDesc.specificationItems[i]
            }
        }
        if (event.target.checked) {
            if (obj != null) {
                obj.attributeValue.push(optionName);
            } else {
                $scope.entity.goodsDesc.specificationItems.push({
                    attributeName: specName,
                    attributeValue: [optionName]
                })
            }
        } else {
            if (obj != null) {
                let index = obj.attributeValue.indexOf(optionName);
                obj.attributeValue.splice(index, 1);
            }
        }
        createSkuList();
    }
    //生成sku列表
    createSkuList = () => {
        //1.对sku商品列表进行初始化
        $scope.entity.items = [{spec: {}, price: 0, num: 99999, status: '0', isDefault: '0'}]
        //2.得到$scope.entity.goodsDesc.specificationItems中的数据
        var items = $scope.entity.goodsDesc.specificationItems;
        //3.遍历此集合, 对$scope.entity.items进行动态赋值
        for (let i = 0; i < items.length; i++) {
            $scope.entity.items = addColumn($scope.entity.items, items[i].attributeName, items[i].attributeValue);
        }
    }
    addColumn = (list, attributeName, attributeValue) => {
        //1.定义一个新集合，用于存放最终的数据
        let newList = [];
        //2.遍历list集合
        for (let i = 0; i < list.length; i++) {
            //2.1)得到原始数据行
            let oldRow = list[i];
            //2.2)遍历值的集合
            for (let j = 0; j < attributeValue.length; j++) {
                //2.3)深克隆得到新行数据
                let newRow = JSON.parse(JSON.stringify(oldRow));
                //2.4)为新行中的spec对象赋值
                newRow.spec[attributeName] = attributeValue[j];
                //2.5)将新行添加到新的集合newList中
                newList.push(newRow);
            }
        }
        //3.返回新的集合
        return newList;
    }
    $scope.categorylist=[]
    $scope.addCategoryList = () => {
        goodsService.getCategory().success(response=>{
            for(let i = 0; i<response.length;i++){
                if(response[i].auditStatus==0){
                 $scope.categorylist.push(response[i])
                }
            }
        })
    }
    $scope.itemCatList=[];
    $scope.getLevelAllInfo=()=>{
        goodsService.getLevelAllInfo().success(response=>{
            for(let i = 0; i<response.length; i++){
                $scope.itemCatList[response[i].id] = response[i].name;
            }
        })
    }
    $scope.status=["未审核","己审核","审核未通过","己关闭"];
    $scope.image_entity={"url":null,"color":null}
    $scope.uploadFile = ()=>{
        goodsService.uploadFile().success(response=>{
            if (response.success){
                $scope.image_entity.url=response.message;
                alert($scope.image_entity.url)
            } else {
                alert(response.message);
            }
        })
    }
    $scope.saveToItemImages = ()=>{
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }
});	
