var productApp = angular.module('productApp', []);
productApp.controller('productManageCtrl', function ($scope, $http) {

    $scope.shopId = '';

    //获取url的shopId参数的值
    $scope.getParentCatId = function () {
        var url = window.location.href;
        var startIndex = url.indexOf("=");
        if(startIndex === -1){
            $.toast("shopId不存在");
            return;
        }
        $scope.shopId = url.substr(startIndex + 1, url.length);
    };

    $scope.productCatList = [];

    //获取shopId关联的所有商品分类
    $scope.queryProCatsByShopId = function () {
        $http.get("/o2o/productCat/getProCatsByShopId/"+$scope.shopId).success(function (data) {
            $scope.productCatList = data.moudule
        });
    };

    //新增一个分类
    $scope.addProductCatItem = function(){
        $scope.productCatList.push({'productCategoryName':'','priority':'','shopId':$scope.shopId});
    };

    //删除某个分类
    $scope.delCatItem = function(index){
        $scope.productCatList.splice(index,1);
    };

    //保存商品分类 【重要】
    $scope.saveBatchProCat = function(){
        for (var i = 0; i < $scope.productCatList.length; i++) {
            delete  $scope.productCatList[i].$$hashKey
        }
        var productCatListStr = JSON.stringify($scope.productCatList);
        $http.post("/o2o/productCat/saveBatchProCat",
            {"productCatListStr":productCatListStr,"shopId":$scope.shopId})
            .success(function (data) {
            if(data.resultCode === 'F'){
                $.toast("保存失败");
            }else if(data.resultCode === 'T'){
                $.toast("保存成功");
            }
        }).error(function (data) {
            $.toast(data);
        });
    };

    $scope.getParentCatId();
    $scope.queryProCatsByShopId();
});