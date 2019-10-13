//服务层
app.service('itemCatService',function($http){

	//1.根据父id查询分类列表
	this.findByParentId=(parentId)=>{
		return $http.get("../itemCat/findByParentId.do?parentId=" + parentId);
	}
	//2.根据主键查询单条分类记录
	this.findOne=(id)=>{
		return $http.get("../itemCat/findOne.do?id="+id);
	}
	//3.查询所有分类
	this.findAll=()=>{
        return $http.get("../itemCat/findAll.do");
	}

});
