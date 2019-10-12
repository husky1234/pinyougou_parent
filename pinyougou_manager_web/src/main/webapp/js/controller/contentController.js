app.controller("contentMController", function ($scope, $controller,contentMService) {
    $controller("baseController", {$scope: $scope});//继承
    $scope.getAllContent = ()=>{

        contentMService.getAllContent().success(response=>{
            $scope.list = response;
        })
    }
    $scope.isEffect = ['无效','有效'];
    $scope.getContentCategory = ()=>{
        contentMService.getContentCategory().success(response=>{
            $scope.categoryList = response;
            $scope.entity = response[0];
            $scope.entity = {};
        })
    }
    $scope.upload = ()=>{
        contentMService.upload().success(response=>{
            $scope.entity.pic = response.message;
        })
    }
    $scope.save = ()=>{
        contentMService.save().success(response=>{
            if (response.success){
                $scope.getContentCategory();
            }
        })
    }
 })