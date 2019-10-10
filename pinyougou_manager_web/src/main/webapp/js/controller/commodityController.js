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

})