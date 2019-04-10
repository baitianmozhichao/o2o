var shopApp = angular.module('shopApp', []);
shopApp.controller('shopListQueryCtrl', function ($scope, $http) {

    $scope.shopCatList = null;

    $scope.shopList = [];

    $scope.allItemClass = 'catItem';

    $scope.shopCondition = {
        key: '',
        shopCategoryId: '',
        areaId: ''
    };

    //url中的parentCategoryId
    $scope.parentCategoryId = '';

    //获取指定parentCategoryId的商铺分类
    $scope.getAllShopCat = function () {
        $http.get("/o2o/shopCategory/getByParentId/" + $scope.parentCategoryId).success(function (data) {
            var shopList = data.moudule;
            if (!shopList) {
                return;
            }

            //维护一个样式字段
            for (var i = 0; i < shopList.length; i++) {
                shopList[i].itemClass = 'catItem';
            }

            if (shopList.length / 3 < 1) {
                $scope.shopCatList = new Array(1);
            } else {
                $scope.shopCatList = new Array(shopList.length / 3);
            }
            for (i = 0; i < shopList.length / 3; i++) {
                $scope.shopCatList[i] = new Array(3);
            }
            for (i = 0; i < (shopList.length / 3 > 1 ? shopList.length / 3 : 1); i++) {
                $scope.shopCatList[i][0] = shopList[i];
                var step = (shopList.length / 3 > 1 ? shopList.length / 3 : 1);
                if (i + step < shopList.length) {
                    $scope.shopCatList[i][1] = shopList[i + step];
                }
                if (i + 2 * step < shopList.length) {
                    $scope.shopCatList[i][2] = shopList[i + 2 * step];
                }
            }
            console.log($scope.shopCatList)
        });
    };

    //点击按关键词搜索---店铺名、店铺描述【模糊查询】
    $scope.search = function () {
        $http.post("/o2o/shop/queryByConditionsPage/1/30", $scope.shopCondition).success(function (data) {
            var date = new Date();
            $scope.shopList = data.moudule
            for (var i = 0; i < $scope.shopList.length; i++) {
                var unixTimestamp = new Date($scope.shopList[i].lastEditTime);
                $scope.shopList[i].lastEditTimeDesc = unixTimestamp.toLocaleString();
            }
        })
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

    //点击商铺分类
    $scope.searchByShopCat = function (shopCatId) {

        //更换选中shop类别的样式
        for (var i = 0; i < $scope.shopCatList.length; i++) {
            $scope.allItemClass = 'catItem';
            var to = 0;
            if ($scope.shopCatList[i][2]) {
                to = 3
            } else if ($scope.shopCatList[i][1]) {
                to = 2
            } else {
                to = 1
            }
            for (var j = 0; j < to; j++) {
                if ($scope.shopCatList[i][j].shopCategoryId === shopCatId) {
                    $scope.shopCatList[i][j].itemClass = 'catItemSel'
                } else {
                    $scope.shopCatList[i][j].itemClass = 'catItem'
                }
            }
        }
        if (!shopCatId) {
            $scope.allItemClass = 'catItemSel'
        }

        //发起搜索
        var date = new Date();
        if(shopCatId){
            $scope.shopCondition.shopCategoryId = shopCatId;
        }else {
            $scope.shopCondition.parentCategoryId = parseInt($scope.parentCategoryId);
            $scope.shopCondition.shopCategoryId = '';
        }
        $http.post("/o2o/shop/queryByConditionsPage/1/30", $scope.shopCondition).success(function (data) {
            $scope.shopList = data.moudule;
            for (var i = 0; i < $scope.shopList.length; i++) {
                var unixTimestamp = new Date($scope.shopList[i].lastEditTime);
                $scope.shopList[i].lastEditTimeDesc = unixTimestamp.toLocaleString();
            }
        })
    };

    $scope.areaList = [];

    $scope.getAllArea = function () {
        $http.get("/o2o/area/getAll").success(function (data) {
            $scope.areaList = data.moudule[0]
        })
    };

    //获取url的parentCatId参数的值
    $scope.getParentCatId = function () {
        var url = window.location.href;
        var startIndex = url.indexOf("=");
        $scope.parentCategoryId = url.substr(startIndex + 1, url.length);
    };

    $scope.getParentCatId();
    $scope.getAllArea();
    $scope.getAllShopCat();

});