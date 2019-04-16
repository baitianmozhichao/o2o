var shopApp = angular.module('shopApp', []);
shopApp.controller('shopManageCtl', function ($scope, $http) {

    //获取url的ShopId参数的值
    $scope.getShopId = function () {
        var url = window.location.href;
        var startIndex = url.indexOf("=");
        if(startIndex === -1){
            $.toast("ShopId不存在");
            return;
        }
        $scope.shopId = url.substr(startIndex + 1, url.length);
    };

    $scope.toProCatList = function () {
        window.location.href = "../product/productCatManage.html?shopId="+$scope.shopId;
    };

    $scope.getShopId();
});