app.controller("specificationController",function ($scope,$controller,specificationService) {

    $controller("baseController",{$scope:$scope});
    //模糊查询加全部查询一起做
    /*
    * 功能分析如果为空则作全部查询如果不为空则模糊查询
    * 根据一个实体
    * */
    $scope.getAll = ()=>{
        specificationService.getAll().success(respone=>{
            $scope.allspe=respone;
            $scope.searchInfo = respone[1];
            $scope.searchInfo = {};
        })
    }
    $scope.findByKey= ()=>{
        specificationService.findByKey($scope.searchInfo)
            .success(response=>{
            $scope.allspe= response;
        })
    }
    //初始化entity的格式
    $scope.entity={"spec":{},"specificationOptionList":[]}
    //初始化option对象
     //添加选项
    $scope.addOption = ()=>{
        $scope.entity.specificationOptionList.push({})
    }
    //删除相应的选项
    $scope.delOption =(index)=>{
        $scope.entity.specificationOptionList.splice(index,1);
    }
    //保存数据到数据中去
    $scope.save = ()=>{
        specificationService.save($scope.entity).success(response=>{
            if (response.success){
                $scope.getAll();
                alert(response.message);
                window.location.reload();
            }else {
                alert(response.message);
                window.location.reload();
            }
        })
    }
    $scope.amend = (name,id) =>{
        specificationService.getOptions(id).success(response=>{
            $scope.entity = response;
            $scope.entity.spec.specName=name;
        })
    }
    $scope.delspecification = ()=>{
        specificationService.dele($scope.selectId).success(reponse=>{
            if (reponse.success){
                $scope.getAll();
                reponse.message;
            }else {
                reponse.message;
            }
        })
    }
})