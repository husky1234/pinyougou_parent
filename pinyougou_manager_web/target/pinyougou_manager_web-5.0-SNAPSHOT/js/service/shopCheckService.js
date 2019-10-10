app.service('shopCheckService',function ($http) {
    this.obtainAllCheckInfo = ()=>{
       return  $http.get("/shop/allCheckInfo.do");
    }
    this.agree =(id)=>{
        return $http.get("/shop/agree.do?id="+id);
    }
})