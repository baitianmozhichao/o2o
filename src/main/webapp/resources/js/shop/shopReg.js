var shopApp = angular.module('shopApp', []);
shopApp.controller('shopRegCtrl', function($scope,$http) {

    $scope.parentCatList = [];
    $scope.shopCatList = [];
    $scope.areaCatList = [];

    $scope.ownerName = '';

    var flags = [false,false,false];

    //验证码
    $scope.verifyCode = '';

    $scope.regShop = {
        shopName:'',//
        parentCategoryId : '0',
        shopCategoryId:'',
        areaId:'',
        shopDesc:'',
        shopAddr:'',
        ownerId:'0',//
        phone:''//
    };

    //商铺大分类
    $scope.getParentShopCat = function(){
        $http.get("/o2o/shopCategory/getRootCats").success(function (data) {
            $scope.parentCatList = data.moudule;
        })
    };

    //商铺详细分类
    $scope.$watch('regShop.parentCategoryId',function (newVal) {
        $http.get("/o2o/shopCategory/getByParentId/"+newVal).success(function (data) {
            $scope.shopCatList = data.moudule;
        })
    });

    //区域分类
    $scope.getAreaCat = function(){
        $http.get("/o2o/area/getAll").success(function (data) {
            $scope.areaCatList = data.moudule;
        })
    };

    //初始化信息
    $scope.initInfo = function () {
        $scope.getParentShopCat();
        $scope.getAreaCat();
    };

    //立即执行
    $scope.initInfo();

    $scope.checkShopName = function(shopName){
        $http.get("/o2o/shop/getCountByName/"+shopName).success(function (data) {
            if(data.count > 0){
                flags[0] = false;
                $.toast("已存在该店铺名，请换一个");
            }else {
                flags[0] = true;
            }
        });
    };

    //检查输入的店长，并获取其id
    $scope.checkOwner = function(name){
        $http.get("/o2o/personInfo/findByName/"+name).success(function (data) {
            if(data.count === 1){
                $scope.regShop.ownerId = data.moudule.userId;
                flags[1] = true;
            }else {
                $.toast("不存在该用户，请重新输入店长名字");
                flags[1] = false;
            }
        })
    };

    //手机号码检查
    $scope.checkPhone = function(phone){
        if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
            $.toast("手机号码有误，请重填");
            flags[2] = false;
        }else {
            flags[2] = true;
        }
    };

    //注册商铺，入口
    $scope.subAddShop = function () {
        if($scope.regShop.shopName === ''
            ||$scope.regShop.parentCategoryId===''
            ||$scope.regShop.shopCategoryId===''
            ||$scope.regShop.areaId === ''
            ||$scope.regShop.shopDesc === ''
            ||$scope.regShop.shopAddr === ''
            ||$scope.regShop.ownerId === ''
            ||$scope.regShop.phone === ''
            ||$scope.verifyCode === ''){
            $.toast("请填写全部信息");
            return
        }
        if(flags[0]===false||flags[1]===false||flags[2]===false){
            $.toast("请按要求填写信息");
            return
        }
        $scope.register().success(function (response) {
            if(response.resultCode === 'F'){
                $.toast("注册失败，验证码输入错误")
            }else {
                $.toast("恭喜注册店铺【"+$scope.regShop.shopName+"】成功")
            }
        }).error(function () {
            $.toast("注册失败，请联系管理员:18826139502")
        })
    };

    //注册店铺
    $scope.register = function () {
        var formdata = new FormData();
        formdata.append('file', file.files[0]);
        // formdata.append('shop',$scope.regShop);
        var regShopStr = JSON.stringify($scope.regShop);
        formdata.append('regShopStr',regShopStr);
        formdata.append('verifyCode',$scope.verifyCode);
        return $http({
            url:'/o2o/shop/addShop.do',
            method:'post',
            data:formdata,
            headers: {'Content-Type':undefined},
            transformRequest: angular.identity
        })
    };

    //更换验证码
    $scope.changeVerifyCode = function () {
        var img = $('#verifyCode_img')[0];
        img.src = "/o2o/Kaptcha?"+Math.floor(Math.random() * 100);
    }
});