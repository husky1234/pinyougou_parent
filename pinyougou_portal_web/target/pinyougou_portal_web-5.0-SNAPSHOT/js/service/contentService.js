app.service("contentService",function ($http) {
    //1.根据分类id查询分类列表
    this.findContentByCategoryId=(categoryId)=>{
        return $http.get("../content/findContentByCategoryId.do?categoryId=" + categoryId);
    }
})