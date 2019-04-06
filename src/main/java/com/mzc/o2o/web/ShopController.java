package com.mzc.o2o.web;

import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.enums.ShopStateEnum;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.util.FileUploadUtil;
import com.mzc.o2o.util.VerifyCodeUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.vo.ShopVo;
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

    /**
     * 商铺增加
     *
     * @param regShopStr
     * @param verifyCode
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/addShop")
    public ResultVo<Shop> addShop(String regShopStr, String verifyCode,
                                  @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

        boolean verifyCodeOk = VerifyCodeUtil.checkVerifyCode(verifyCode, request);
        if (!verifyCodeOk) {
            log.error("【addShop】验证码校验失败...");
            return buildEmptyResultVo();
        }
        JSONObject jsonObj = JSONObject.fromObject(regShopStr);
        Shop shop = (Shop) JSONObject.toBean(jsonObj, Shop.class);

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
        boolean re = shopService.insert(shop);
        if (re) {
            return buildResultVo(shop, 1);
        }
        return buildEmptyResultVo();
    }

    /**
     * 根据查询shopId
     * @param shopId
     * @return
     */
//    @GetMapping("/findByShopId/{shopId}")
//    public ResultVo<Shop> findByShopId(@PathVariable("shopId") Integer shopId){
//        Shop shop = shopService.query(shopId);
//        return buildResultVo(shop, 1);
//    }

    /**
     * 商铺修改
     *
     * @param shop
     * @param file
     * @return
     */
    @PostMapping("/updateShop")
    public ResultVo<Shop> updateShop(Shop shop, @RequestParam(value = "file",required = false) MultipartFile file) {
        if (shop == null || shop.getShopId() == null) {
            return buildFailResultVo("shop更改，参数错误", 1);
        }
        if (file != null) {
            try {
                String url = "";
                url = FileUploadUtil.uploadFile(file);
                shop.setShopImg(url);
            }catch (Exception e){
                return buildFailResultVo("上传图片失败", 0);
            }
        }
        shop.setLastEditTime(new Date());
        boolean re = shopService.updateById(shop);
        if (re) {
            return buildResultVo("", 1);
        }
        return buildEmptyResultVo();
    }

    /**
     * 获取名字为shopName的商铺个数
     *
     * @param shopName
     * @return
     */
    @GetMapping("/getCountByName/{shopName}")
    public ResultVo<String> getCountByName(@PathVariable("shopName") String shopName) {
        Integer count = shopService.getCountByName(shopName);
        return buildResultVo("", count);
    }

    /**
     * 查看商铺详情：带area名、owner名等
     *
     * @param shopId
     * @return
     */
    @GetMapping("/queryShopWithName/{shopId}")
    public ResultVo<ShopVo> queryShopWithName(@PathVariable("shopId") Integer shopId) {
        ShopVo shopVo = shopService.queryShopWithName(shopId);
        return buildResultVo(shopVo, shopVo == null ? 0 : 1);
    }
}
