var shopApp = angular.module('shopApp', []);
shopApp.controller('shopListQueryCtrl', function ($scope, $http) {

    $scope.shopCatList = null;

    $scope.shopList = [];

    $scope.allItemClass = 'catItem';

    $scope.shopCondition = {
        key:'',
        shopCategoryId:'',
        areaId:''
    };

    $scope.getAllShopCat = function () {
        $http.get("/o2o/shopCategory/getAll").success(function (data) {
            var shopList = data.moudule;

            //维护一个样式字段
            for (var i = 0; i < shopList.length; i++) {
                shopList[i].itemClass = 'catItem';
            }

            $scope.shopCatList = new Array(shopList.length / 3);
            for (i = 0; i < shopList.length / 3; i++) {
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

    //点击按关键词搜索---店铺名、店铺描述【模糊查询】
    $scope.search = function(){
        $http.post("/o2o/shop/queryByConditionsPage/1/30",$scope.shopCondition).success(function (data){
            $scope.shopList = data.moudule
        })
    };

    //点击商铺分类
    $scope.searchByShopCat = function(shopCatId){

        //更换选中shop类别的样式
        for (var i = 0; i <  $scope.shopCatList.length; i++) {
            $scope.allItemClass = 'catItem';
            for (var j = 0; j < 3; j++) {
                if($scope.shopCatList[i][j].shopCategoryId === shopCatId){
                    $scope.shopCatList[i][j].itemClass = 'catItemSel'
                }else {
                    $scope.shopCatList[i][j].itemClass = 'catItem'
                }
            }
        }
        if(!shopCatId){
            $scope.allItemClass = 'catItemSel'
        }

        //发起搜索
        $scope.shopCondition.shopCategoryId = shopCatId;
        $http.post("/o2o/shop/queryByConditionsPage/1/30",$scope.shopCondition).success(function (data){
            $scope.shopList = data.moudule
        })
    };

    $scope.getAllShopCat();

});