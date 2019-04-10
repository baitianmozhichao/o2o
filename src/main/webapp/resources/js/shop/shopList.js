var shopApp = angular.module('shopApp', []);
shopApp.controller('shopListCtrl', function ($scope, $http) {
    $scope.ownerName = "莫志超";

    $scope.shopList = [];

    $scope.areaList = [];
    $scope.shopStatusList = [];

    //区域分类
    $scope.getAreaCat = function () {
        $http.get("/o2o/area/getAll").success(function (data) {
            $scope.areaList = data.moudule;
        })
    };

    //获取商铺状态列表
    $scope.getShopStatusList = function () {
        $http.get("/o2o/common/status/getShopStatusList").success(function (data) {
            $scope.shopStatusList = data;
        })
    };

    $scope.shopCondition = {
        ownerId:8,
        shopName:'',
        areaId:'',
        enableStatus:''
    };

    $scope.getShopList = function () {
        //TODO 类型转换
        $scope.shopCondition.enableStatus = 0;

        $http.post("/o2o/shop/queryByConditionsPage/1/6",$scope.shopCondition).success(function (data) {
            $scope.shopList = data.moudule;
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

    $scope.getShopList();
    $scope.getAreaCat();
    $scope.getShopStatusList();
});