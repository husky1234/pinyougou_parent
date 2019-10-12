app.service('contentMService',function ($http) {
    this.getAllContent = ()=>{
        return $http.get("/content/getAll.do");
    }
    this.getContentCategory = ()=>{
        return $http.get("/content/getAllContentCategory.do");
    }
    this.upload = ()=>{
        var formData = new FormData;
        formData.append('file',file.files[0])
        return $http({
            method:'post',
            url:'../upload.do',
            data:formData,
            headers:{"content-Type":undefined},
            transformRequest:angular.identity
        })
    }
    this.save = ()=>{
        return $http.post("/content/save.do",$scope.entity);
    }
})