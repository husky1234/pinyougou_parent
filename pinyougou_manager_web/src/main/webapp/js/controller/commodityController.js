app.controller("commodityController", function ($scope, $controller, commodityService) {
    $controller("baseController", {$scope: $scope});//继承
    $scope.findLevel = (id) => {
        commodityService.getLevelInfo(id).success(response => {
            $scope.list = response;
            $scope.entity = response[0];
            $scope.entity = {};
        })
    }
    $scope.level = [, ,];
    $scope.flag = 0;
    $scope.nextLevel = (category) => {
        $scope.flag += 1;
        if ($scope.level[0] == null) {
            $scope.level[0] = category;
            $scope.level[0].parentId = category.id;
        } else if ($scope.level[1] == null) {
            $scope.level[1] = category;
            $scope.level[1].parentId = category.id;
        }
        $scope.findLevel(category.id);
    }
    $scope.navigation = (id, index) => {
        if (index == 0) {
            $scope.level = [];
            $scope.flag = 0;
        } else if (index == 1) {
            $scope.level[1] = null;
            $scope.flag = 1;
        }
        $scope.findLevel(id);
    }
    $scope.saveCateInfo = () => {
        if ($scope.level[0] == null && $scope.level[1] == null) {
            $scope.entity.parentId = 0;
        } else if ($scope.level[0] != null && $scope.level[1] == null) {
            $scope.entity.parentId = $scope.level[0].parentId;
        } else {
            $scope.entity.parentId = $scope.level[1].parentId;
        }
        commodityService.saveCateInfo($scope.entity).success(response => {
            if (response.success) {
                $scope.findLevel(0);
                $scope.entity = {};
            } else {
                alert(response.message);
                $scope.entity = {};
            }
        })
    }
    $scope.amend = (category) => {
        $scope.entity = category;
    }
    $scope.deletcommodify = ()=>{
        commodityService.deletcommodify($scope.selectId).success(response=>{
            if (response.success){
                $scope.findLevel(0);
            } else {
                alert(response.message);
            }
        })
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
    $scope.addCategoryList = () => {
        goodsService.getCategory(1).success(response=>{
            $scope.list = response;
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

})