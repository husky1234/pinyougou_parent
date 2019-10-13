app.service('brandService',function ($http) {
    /*发送请求查询所有*/
     this.getAll = ()=> {
         return $http.get("../brand/getAll.do")
     }
     /*发送请求查询所有带分页的查询*/
     this.pagination = (page,pagesize)=>{
         return $http.get("../brand/getBypage.do?page="+page+"&pagesize="+pagesize);
     }
     /*发送求求带模糊查询*/
     this.searbyentity =function(page,pagesize,searchentity){
         return $http.post('../brand/getByentity.do?page='+page+"&pagesize="+pagesize,searchentity);
    }
    /*添加信息或者修改信息*/
    this.save = entity =>{
        return $http.post("../brand/addInfo.do",entity);
    }
    this.delet = (select)=>{
        return $http.post("../brand/delete",select);
    }

})