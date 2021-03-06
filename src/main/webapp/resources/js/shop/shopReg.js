var shopApp = angular.module('shopApp', []);
shopApp.controller('shopRegCtrl', function ($scope, $http) {

    $scope.parentCatList = [];
    $scope.shopCatList = [];
    $scope.areaCatList = [];

    $scope.ownerName = '';

    var flags = [false, false, false];

    //验证码
    $scope.verifyCode = '';

    $scope.regShop = {
        shopName: '',//
        parentCategoryId: '0',
        shopCategoryId: '',
        areaId: '',
        shopDesc: '',
        shopAddr: '',
        ownerId: '0',//
        phone: ''//
    };

    //商铺大分类
    $scope.getParentShopCat = function () {
        $http.get("/o2o/shopCategory/getRootCats").success(function (data) {
            $scope.parentCatList = data.moudule;
        })
    };

    //商铺详细分类
    $scope.$watch('regShop.parentCategoryId', function (newVal) {
        if (name === '') {
            return;
        }
        $http.get("/o2o/shopCategory/getByParentId/" + newVal).success(function (data) {
            $scope.shopCatList = data.moudule;
        })
    });

    //区域分类
    $scope.getAreaCat = function () {
        $http.get("/o2o/area/getAll").success(function (data) {
            $scope.areaCatList = data.moudule;
        })
    };

    $scope.getShopInfo = function (shopId) {
        $http.get("/o2o/shop/queryShopWithName/" + shopId).success(function (data) {
            $scope.regShop = data.shopVo;
            $scope.areaCatList = data.areaList;

            $scope.ownerName = data.shopVo.ownerName;

            var pCat = $("#parentCatId");
            pCat.empty();
            pCat.append($scope.regShop.parentCategoryName);

            var sCat = $("#shopCatId");
            sCat.empty();
            sCat.append($scope.regShop.shopCategoryName);

        })
    };

    //初始化信息
    $scope.initInfo = function () {
        var shopId = $scope.getLocationQueryParam("shopId");
        $scope.shopId = shopId;
        if (shopId === '') {
            $scope.getParentShopCat();
            $scope.getAreaCat();
        } else {
            $scope.getShopInfo(shopId);
        }
    };

    //获取地址栏的param
    $scope.getLocationQueryParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURIComponent(r[2]);
        }
        return '';
    };

    //立即执行
    $scope.initInfo();

    $scope.checkShopName = function (shopName) {
        $http.get("/o2o/shop/getCountByName/" + shopName).success(function (data) {
            if (data.count > 0) {
                flags[0] = false;
                $.toast("已存在该店铺名，请换一个");
            } else {
                flags[0] = true;
            }
        });
    };

    //检查输入的店长，并获取其id
    $scope.checkOwner = function (name) {
        $http.get("/o2o/personInfo/findByName/" + name).success(function (data) {
            if (data.count === 1) {
                $scope.regShop.ownerId = data.moudule.userId;
                flags[1] = true;
            } else {
                $.toast("不存在该用户，请重新输入店长名字");
                flags[1] = false;
            }
        })
    };

    //手机号码检查
    $scope.checkPhone = function (phone) {
        if (!(/^1(3|4|5|7|8)\d{9}$/.test(phone))) {
            $.toast("手机号码有误，请重填");
            flags[2] = false;
        } else {
            flags[2] = true;
        }
    };

    //商铺信息管理
    $scope.operateShop = function () {
        if ($scope.regShop.shopName === ''
            || $scope.regShop.parentCategoryId === ''
            || $scope.regShop.shopCategoryId === ''
            || $scope.regShop.areaId === ''
            || $scope.regShop.shopDesc === ''
            || $scope.regShop.shopAddr === ''
            || $scope.regShop.ownerId === ''
            || $scope.regShop.phone === ''
            || $scope.verifyCode === '') {
            $.toast("请填写全部信息");
            return
        }
        if (!$scope.regShop.shopId && (flags[0] === false || flags[1] === false || flags[2] === false)) {
            $.toast("请按要求填写信息");
            return
        }

        var operatorUrl = '';
        if ($scope.shopId) {
            operatorUrl = '/o2o/shop/updateShop';
        } else {
            operatorUrl = '/o2o/shop/addShop';
        }

        $scope.register(operatorUrl).success(function (response) {
            if (response.resultCode === 'F') {
                if (operatorUrl.indexOf("update") !== -1) {
                    $.toast("更新失败，验证码输入错误")
                } else {
                    $.toast("注册失败，验证码输入错误")
                }
            } else {
                flags = [false, false, false];
                if (operatorUrl.indexOf("update") !== -1) {
                    $.toast("更新店铺【" + $scope.regShop.shopName + "】成功")
                } else {
                    $.toast("注册店铺【" + $scope.regShop.shopName + "】成功")
                }
            }
        }).error(function () {
            if (operatorUrl.indexOf("update") !== -1) {
                $.toast("更新失败，请联系管理员:18826139502")
            } else {
                $.toast("注册失败，请联系管理员:18826139502")
            }
        })
    };

    //注册店铺
    $scope.register = function (operatorUrl) {
        var formdata = new FormData();
        formdata.append('file', file.files[0]);
        // formdata.append('shop',$scope.regShop);
        var regShopStr = JSON.stringify($scope.regShop);
        formdata.append('regShopStr', regShopStr);
        formdata.append('verifyCode', $scope.verifyCode);
        return $http({
            url: operatorUrl,
            method: 'post',
            data: formdata,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        })
    };

    //更换验证码
    $scope.changeVerifyCode = function () {
        var img = $('#verifyCode_img')[0];
        img.src = "/o2o/Kaptcha?" + Math.floor(Math.random() * 100);
    }
});