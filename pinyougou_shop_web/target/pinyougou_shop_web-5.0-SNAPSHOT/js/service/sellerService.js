//服务层
app.service('sellerService',function($http){
this.register = (entity)=>{
	 return  $http.post("/register.do",entity);
}
});
