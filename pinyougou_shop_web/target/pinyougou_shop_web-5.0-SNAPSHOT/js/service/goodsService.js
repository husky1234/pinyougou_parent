//服务层
app.service('goodsService',function($http){
	this.init = ()=>{
		return $http.get("/goods/findAll.do");
	}
	this.addInfo = (entity)=>{
		return $http.post("/goods/addInfo.do",entity);
	}
	this.getLevel = (id)=>{
		return $http.get("/goods/getLevel.do?id="+id);
	}
	this.getTemplateInfo =(templateId)=>{
		return $http.get("/goods/getTemplateInfo.do?id="+templateId);
	}
	this.getspecs = (ids)=>{
		return $http.get("/goods/getspecs.do?ids="+ids);
	}
	this.getCategory = () => {
		return $http.get("/goods/addCategoryList.do");
	}
	this.getLevelAllInfo =()=>{
		return $http.get("/goods/getLevelAllInfo.do");
	}
	this.uploadFile = ()=>{
		var data = new FormData();
		data.append("file",file.files[0]);
		return $http({
			url:'../upload.do',
			data:data,
			method:'post',
			headers:{'Content-Type':undefined},
			transformRequest:angular.identity
		})
	}
});
