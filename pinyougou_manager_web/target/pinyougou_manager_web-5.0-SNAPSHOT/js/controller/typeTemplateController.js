app.controller("typeTemplateController",function($scope,$controller,typeTemplateService){
    $controller("baseController",{$scope:$scope})

    $scope.findAll = ()=>{
        typeTemplateService.findAll().success(response=>{
            $scope.list = response;
            $scope.entity={};
            //必须初始化不然报错
            $scope.entity.customAttributeItems=[];

        })
    }

    $scope.findByKey = ()=>{
        typeTemplateService.findByKey($scope.name).success(response=>{
            $scope.list = response;
        })
    }
    $scope.selectBrandList = ()=>{
        typeTemplateService.selectBrandList().success(response=>{
            $scope.brandList={data:response};
        })
    }
    $scope.selectSpecList = ()=>{
        typeTemplateService.selectSpecList().success(response=>{
            $scope.specList = {data:response}
        })
    }

    $scope.addInfo = ()=>{
      $scope.entity.customAttributeItems.push({});
    }
    $scope.dele = (index)=>{
        $scope.entity.customAttributeItems.splice(index,1);
    }
    $scope.save=() =>{
        typeTemplateService.saveinfo($scope.entity).success(response=>{
            if (response.success){
                $scope.findAll();
                alert(response.message)
            }else {
                alert(response.message)
            }
        })
    }
    $scope.amend =(template)=>{
        $scope.entity=template;
        $scope.entity.customAttributeItems = [];
    }
    $scope.deleTemplate =()=>{
        typeTemplateService.deleTemplate($scope.selectselectId).success(response=>{
            if (response.success){
                $scope.findAll();
                alert(response.message);
            }else {
                alert(response.message);
            }
        })
    }
})