var productApp = angular.module('productApp', []);
productApp.controller('productListCtrl', function ($scope, $http,$timeout) {

    //url中的shopId
    $scope.shopId = '';

    $scope.roductQueryCondition = {
        key: '',
        productCategoryId:''
    };

    //获取url的shopId参数的值
    $scope.getshopId = function () {
        var url = window.location.href;
        var startIndex = url.indexOf("=");
        if(startIndex === -1){
            $.toast("shopId不存在");
            return;
        }
        $scope.shopId = url.substr(startIndex + 1, url.length);
    };

    //原型链，覆盖toLocaleString()
    Date.prototype.toLocaleString = function () {
        var year = this.getFullYear();
        var month = this.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }
        var day = this.getDate();
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    };

    $scope.shop = {};

    $scope.getShopDetail = function () {
        $http.get("/o2o/shop/queryShopWithName/"+$scope.shopId).success(function (data) {
            $scope.shop = data.shopVo;
            var unixTimestamp = new Date($scope.shop.createTime);
            $scope.shop.createTimeDesc = unixTimestamp.toLocaleString();
        });
    };

    $scope.productCatList = [];

    //根据shopId获取所有相关的商品类别
    $scope.getProCatByShopId = function(){
        $http.get("/o2o/productCat/getProCatsByShopId/"+$scope.shopId).success(function (data) {
            var productCatList = data.moudule;
            if(!productCatList){
                return;
            }

            //维护一个样式字段
            for (var i = 0; i < productCatList.length; i++) {
                productCatList[i].itemClass = 'catItem';
            }

            if (productCatList.length / 3 < 1) {
                $scope.productCatList = new Array(1);
            } else {
                $scope.productCatList = new Array(Math.ceil(productCatList.length / 3));
            }
            for (i = 0; i < productCatList.length / 3; i++) {
                $scope.productCatList[i] = new Array(3);
            }
            var step = 3;
            for (i = 0; i < Math.ceil(productCatList.length / 3); i++) {
                $scope.productCatList[i][0] = productCatList[i * step];
                if (i * step + 1 < productCatList.length) {
                    $scope.productCatList[i][1] = productCatList[i * step + 1];
                }
                if (i * step + 2 < productCatList.length) {
                    $scope.productCatList[i][2] = productCatList[i * step + 2];
                }
            }
        });
    };

    var timeout;
    var layTime = 1500;
    //对roductQueryCondition.key的变化做延时处理
    $scope.$watch("roductQueryCondition.key",function (newVal) {
        if(timeout){
            $timeout.cancel(timeout)
        }
        timeout=$timeout(function(){
            $scope.roductQueryCondition.key = newVal;
            $http.post("/o2o/product/getByConditionPage/1/30", $scope.roductQueryCondition).success(function (data) {
                $scope.productList = data.moudule;
                if(!$scope.productList){
                    return;
                }
                for (var i = 0; i < $scope.productList.length; i++) {
                    var unixTimestamp = new Date($scope.productList[i].lastEditTime);
                    $scope.productList[i].lastEditTimeDesc = unixTimestamp.toLocaleString();
                }
            })
        },layTime)
    });

    var date = new Date();
    //选择类别发起搜索
    $scope.searchByProductCat = function(productCategoryId){
        for (var i = 0; i < $scope.productCatList.length; i++) {
            var to = 0;
            if ($scope.productCatList[i][2]) {
                to = 3
            } else if ($scope.productCatList[i][1]) {
                to = 2
            } else {
                to = 1
            }
            for (var j = 0; j < to; j++) {
                if ($scope.productCatList[i][j].productCategoryId === productCategoryId) {
                    $scope.productCatList[i][j].itemClass = 'catItemSel'
                } else {
                    $scope.productCatList[i][j].itemClass = 'catItem'
                }
            }
        }

        $scope.roductQueryCondition.productCategoryId = productCategoryId;
        $http.post("/o2o/product/getByConditionPage/1/30", $scope.roductQueryCondition).success(function (data) {
            $scope.productList = data.moudule;
            if(!$scope.productList){
                return;
            }
            for (var i = 0; i < $scope.productList.length; i++) {
                var unixTimestamp = new Date($scope.productList[i].lastEditTime);
                $scope.productList[i].lastEditTimeDesc = unixTimestamp.toLocaleString();
            }
        })
    };

    $scope.getshopId();
    $scope.getShopDetail();
    $scope.getProCatByShopId();
});