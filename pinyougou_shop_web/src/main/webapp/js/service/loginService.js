app.service('loginService',function ($http) {
    this.getName= ()=>{
       return  $http.get("/loginName.do");
    }
})