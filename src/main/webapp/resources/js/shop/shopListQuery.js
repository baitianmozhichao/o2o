var shopApp = angular.module('shopApp', []);
shopApp.controller('shopListQueryCtrl', function ($scope, $http) {

    $scope.shopCatList = null;

    $scope.searchEntity = {key:'',areaId:''};

    $scope.getAllShopCat = function () {
        $http.get("/o2o/shopCategory/getAll").success(function (data) {
            var shopList = data.moudule;
            $scope.shopCatList = new Array(shopList.length / 3);
            for (var i = 0; i < shopList.length / 3; i++) {
                $scope.shopCatList[i] = new Array(3);
            }
            for (i = 0; i < shopList.length / 3; i++) {
                $scope.shopCatList[i][0] = shopList[i];
                if(i + shopList.length / 3 < shopList.length){
                    $scope.shopCatList[i][1] = shopList[i + shopList.length / 3];
                }
                if(i + 2 * shopList.length / 3 < shopList.length){
                    $scope.shopCatList[i][2] = shopList[i + 2 * shopList.length / 3];
                }
            }
        });
    };

    $scope.search = function(){

    };

    $scope.searchByShopCat = function(shopCatId){
    //    TODO

    };

    $scope.getAllShopCat();

});