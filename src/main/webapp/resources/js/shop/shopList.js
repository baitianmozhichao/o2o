var shopApp = angular.module('shopApp', []);
shopApp.controller('shopListCtrl', function ($scope, $http) {
    $scope.ownerName = "莫志超";

    $scope.shopList = [];

    $scope.areaList = [];
    $scope.shopStatusList = [];

    //区域分类
    $scope.getAreaCat = function () {
        $http.get("/o2o/area/getAll").success(function (data) {
            $scope.areaList = data.moudule[0];
            console.log($scope.areaList)
        })
    };

    //获取商铺状态列表
    $scope.getShopStatusList = function () {
        $http.get("/o2o/common/status/getShopStatusList").success(function (data) {
            $scope.shopStatusList = data.moudule;
            console.log($scope.shopStatusList)
        })
    };

    //搜索实体
    $scope.shopCondition = {
        //TODO ownerId如何确定
        ownerId:8,
        shopName:'',
        areaId:'',
        enableStatus:''
    };

    //搜索
    $scope.getShopList = function () {
        $http.post("/o2o/shop/queryByConditionsPage/1/60",$scope.shopCondition).success(function (data) {
            $scope.shopList = data.moudule;
            if(!$scope.shopList){
                return;
            }
            for (var i = 0;i<$scope.shopList.length; i++){
                if($scope.shopList[i].enableStatus === 0){
                    $scope.shopList[i].enableStatusDesc = '待审核';
                }if($scope.shopList[i].enableStatus === 1){
                    $scope.shopList[i].enableStatusDesc = '审核通过';
                }if($scope.shopList[i].enableStatus === 2){
                    $scope.shopList[i].enableStatusDesc = '审核不通过';
                }
            }
        })
    };

    //进入商铺管理
    $scope.enterManage = function(shopId){
        window.location.href = "../shop/shopManage.html?shopId="+shopId;
    };

    $scope.getShopList();
    $scope.getAreaCat();
    $scope.getShopStatusList();
});