package com.mzc.o2o.web;

import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.enums.ShopStateEnum;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.util.FileUploadUtil;
import com.mzc.o2o.util.VerifyCodeUtil;
import com.mzc.o2o.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/addShop")
    public ResultVo<Shop> addShop(String regShopStr, String verifyCode,
                                  @RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) {

        boolean verifyCodeOk = VerifyCodeUtil.checkVerifyCode(verifyCode, request);
        if (!verifyCodeOk){
            log.error("【addShop】验证码校验失败...");
            return buildEmptyResultVo();
        }
        JSONObject jsonObj = JSONObject.fromObject(regShopStr);
        Shop shop = (Shop) JSONObject.toBean(jsonObj,Shop.class);

        //TODO 商铺的owner是否为当前登陆用户，如果是，就从session获取，取决于业务

        if (shop == null || StringUtils.isBlank(shop.getOwnerId() + "") || StringUtils.isBlank(shop.getShopName())) {
            throw new RuntimeException("shop新增，参数错误,shop:[" + shop + "]");
        }
        String url = "";
        if (file != null) {
            url = FileUploadUtil.uploadFile(file);
        }
        shop.setShopImg(url);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        int effectiveNum = shopService.add(shop);
        if (effectiveNum == 0) {
            return buildEmptyResultVo();
        }
        return buildResultVo(shop, effectiveNum);
    }

    @PostMapping("/updateShop")
    public ResultVo<Shop> updateShop(Shop shop, @RequestParam("file") MultipartFile file) {
        //TODO
        if (shop == null || shop.getShopId() == null) {
            throw new RuntimeException("shop更改，参数错误,shop:[" + shop + "]");
        }
        String url = "";
        if (file != null) {
            url = FileUploadUtil.uploadFile(file);
        }
        shop.setShopImg(url);
        shop.setLastEditTime(new Date());
        int effectiveNum = shopService.update(shop);
        if (effectiveNum == 0) {
            return buildEmptyResultVo();
        }
        return buildResultVo(shop, effectiveNum);
    }

    @GetMapping("/getCountByName/{shopName}")
    public ResultVo<String> getCountByName(@PathVariable("shopName") String shopName){
        Integer count = shopService.getCountByName(shopName);
        return buildResultVo("",count);
    }
}
