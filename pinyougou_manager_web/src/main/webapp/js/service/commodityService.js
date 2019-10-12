app.service("commodityService",function ($http) {
    this.getLevelInfo = (level)=>{
        return $http.get("/category/levelInfo.do?level="+level);
    }
    this.saveCateInfo = (entity)=>{
        return $http.post("/category/addInfo.do",entity);
    }
    this.deletcommodify = (list)=>{
        return $http.get("/category/deleInfo.do?list="+list);
    }
    this.getCategory = (i) => {
        return $http.get("/goods/addCategoryList.do");
    }
    this.getLevelAllInfo =()=>{
        return $http.get("/goods/getLevelAllInfo.do");
    }
})