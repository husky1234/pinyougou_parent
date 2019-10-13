app.service("typeTemplateService", function ($http) {
    this.findAll = () => {
        return $http.get("/typeTemplate/findAll.do");
    }
    this.findByKey = (key) => {
        return $http.get("/typeTemplate/findByKey.do?key=" + key);
    }
    this.selectBrandList = () => {
        return $http.get("/typeTemplate/selectBrandList.do");
    }
    this.selectSpecList = () => {
        return $http.get("/typeTemplate/selectSpecList.do");
    }
    this.saveinfo = (entity) => {
        return $http.post("/typeTemplate/saveInfo.do", entity);
    }
    this.deleTemplate = (list) => {
        return $http.get("/typeTemplate/deleTemplate.do?list=" + list);
    }
})
