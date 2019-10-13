app.controller("searchController",function ($scope,$controller,searchService) {
    $controller('baseController', {$scope: $scope});//继承


    $scope.search=()=>{
        searchService.search($scope.searchMap).success(response=>{
            $scope.resultMap = response;
        })
    }

})