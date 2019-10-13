app.controller("loginController",function ($scope,loginService) {
    $scope.getName = ()=>{
        loginService.getName().success(response=>{
            $scope.loginName = response.loginName;
        })
    }
})