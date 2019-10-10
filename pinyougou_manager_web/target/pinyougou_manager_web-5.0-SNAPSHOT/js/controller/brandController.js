app.controller("brandController",function ($scope,$controller,brandService) {
    $controller("baseController",{$scope:$scope});
    /*初始的查询*/
    $scope.getAll = function () {
        brandService.getAll().success(
            (response)=>{
                $scope.list = response;
            }
        )
    }

        /*带分页的查询*/
    $scope.pagination = ()=>{
        brandService.pagination($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage).success(response=>{
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }
    /*模糊查询*/
    $scope.searchentity =()=>{

        brandService.searbyentity($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage,$scope.sentity).success(response=>{
            $scope.list = response.rows;
            $scope.entity=response.rows[1];//这行代码的主要作用是说明entity的格式不做他用
            $scope.entity={};//与上面的代码配合
            $scope.paginationConf.totalItems = response.total;
        })

    }
    $scope.updatainfo = function (brand){
        $scope.entity.id=brand.id;
        $scope.entity.name=brand.name;
        $scope.entity.firstChar = brand.firstChar;
        $("#editModal").modal("show");
    }
    $scope.save =()=>{
            brandService.save($scope.entity).success(response=>{
            if (response.success) {
                alert(response.message);
                $scope.searchentity();
                $scope.entity = {};
            }else{
                alert(response.message);
                $scope.entity = {};
            }
        })
    }
    $scope.delet = ()=>{
        brandService.delet($scope.selectId).success(response=>{
            if (response.success){
                alert(response.message);
            }else {
                alert(response.message);
            }
        })
    }

})