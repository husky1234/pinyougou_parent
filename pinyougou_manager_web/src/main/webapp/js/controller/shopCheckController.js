app.controller('shopCheckController',function ($scope,$controller,shopCheckService) {
    $controller('baseController',{$scope:$scope});
    $scope.obtainAllCheckInfo = ()=>{
        shopCheckService.obtainAllCheckInfo().success(response=>{
                  $scope.allInfo = response;
        })
    }
    $scope.allSellerInfo = (entity)=>{
        $scope.entity = entity;
    }
    $scope.agree =(id)=>{
        shopCheckService.agree(id).success(response=>{
            if (response.success){
                $scope.obtainAllCheckInfo();
            }
        })
    }
})