app.service("specificationService",function ($http) {
    this.getAll = ()=>{
        return $http.get("../specification/findAll.do")
    }
    this.findByKey = (entity)=>{
        return $http.post("../specification/findbykey.do?",entity);
    }
    this.save = (entity)=>{
    return $http.post("../specificationOption/addoptions.do",entity);
    }
    this.getOptions = (id)=>{
        return $http.get("../specificationOption/getOptions.do?id="+id);
    }
    this.dele =(list)=>{
        return $http.get("../specificationOption/dele.do?list="+list);
    }
})