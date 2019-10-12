app.controller("contentController",function ($scope,$controller,contentService) {
    $controller('baseController', {$scope: $scope});//继承

    //1.根据分类id查询分类列表
    $scope.findContentByCategoryId=(categoryId)=>{
        contentService.findContentByCategoryId(categoryId).success(response=>{
            $scope.list = response;
        })
    }
})