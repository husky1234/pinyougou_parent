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
    $scope.entity = {goods: {}, goodsDesc: {itemImages: [], specificationItems: []},items:[]};
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
        //状态以及规格等设置
        //一下设置取决于用户的规格选择，按照用户的选择来生成相应的设置项
        let specs = $scope.entity.goodsDesc.specificationItems;
            $scope.entity.items=[{}];
            let len = $scope.entity.items.length;
               for (let i = 0; i < specs.length; i++) {
                   for (let j = 0; j < len; j++) {
                       for (let k = 0; k<specs[i].attributeValue.length; k++){
                       $scope.entity.items.push(JSON.parse(JSON.stringify(specs[i].attributeValue[k]+specs[i].attributeName)));
                       }
                   }
           }
        }
});	
