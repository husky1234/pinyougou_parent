 //控制层 
app.controller('sellerController' ,function($scope,$controller,sellerService){

	$controller('baseController',{$scope:$scope});//继承
	$scope.register =()=>{
		sellerService.register($scope.entity).success(response=>{
			if(response.success){
				location.href="shoplogin.html";
			}else {
				alert(response.message);
			}
		})
	}
});	
