app.controller("contentMController", function ($scope, $controller,contentMService) {
    $controller("baseController", {$scope: $scope});//继承
    $scope.getAllContent = ()=>{

        contentMService.getAllContent().success(response=>{
            $scope.list = response;
        })
    }
    $scope.isEffect = ['无效','有效'];
    $scope.getContentCategory = ()=>{
        $scope.entity={};
        contentMService.getContentCategory().success(response=>{
            $scope.categoryList = response;
        })
    }
    $scope.upload = ()=>{
        contentMService.upload().success(response=>{
            $scope.entity.pic = response.message;
        })
    }
    $scope.save = ()=>{
        contentMService.save($scope.entity).success(response=>{
            if (response.success){
                $scope.getAllContent();
            }
        })
    }
    $scope.isValid = (event)=>{
        if (event.target.checked){
            $scope.entity.status=1;
        }else {
            $scope.entity.status=0;
        }
    }
    $scope.update=(entity)=>{
        $scope.getContentCategory();
        $scope.entity = entity;
    }
    $scope.deleCategory= ()=>{
        contentMService.deleCategory($scope.selectId).success(response=>{
            if (response.success){
                $scope.getAllContent();
            } else {
                alert(response.message);
            }
        })
    }
 })
