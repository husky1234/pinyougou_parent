app.service("searchService",function ($http) {
    //1.进行查询
    this.search = (searchMap)=>{
        return $http.post("./item/search.do",searchMap);
    }
})