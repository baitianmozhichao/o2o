var shopApp = angular.module('shopApp', []);
shopApp.controller('mainPageCtrl', function ($scope, $http) {

    //轮播图列表
    $scope.headLineList = [];

    //一级类别展示列表
    $scope.rootCategoryList = null;

    //获取首页信息
    $scope.listMainPageInfo = function () {
        $http.get("/o2o/mainpage/info").success(function (data) {
            //轮播图列表
            $scope.headLineList = data.headLineList;

            //一级类别展示列表
            var rcList = data.rootCategoryList;
            $scope.rootCategoryList = new Array(rcList.length / 2);
            for (var i = 0; i < rcList.length / 2; i++) {
                $scope.rootCategoryList[i] = new Array(2);
            }
            for (i = 0; i < rcList.length/2; i++) {
                $scope.rootCategoryList[i][0] = rcList[i];
                $scope.rootCategoryList[i][1] = rcList[i+rcList.length/2];
            }
        });
    };

    $scope.listMainPageInfo();

});