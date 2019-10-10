app.controller("baseController",function ($scope) {
    $scope.paginationConf = {
        currentPage:1,
        totalItems:1,
        itemsPerPage:10,
        perPageOptions:[10,20,30],
        onChange:()=>{
            /*分页查询*/
            $scope.searchentity();
        }
    }
    $scope.selectId=[];
    $scope.seleInfo = (event,id)=>{
        if (event.target.checked){
           $scope.selectId.push(id);
        }else {
            var index = $scope.selectId.indexOf(id);
            $scope.selectId.splice(index,1);
        }
    }
});