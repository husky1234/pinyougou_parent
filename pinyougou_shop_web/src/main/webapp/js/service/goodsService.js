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
});
