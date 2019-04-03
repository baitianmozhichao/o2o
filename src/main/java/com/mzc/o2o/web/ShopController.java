package com.mzc.o2o.web;

import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.enums.ShopStateEnum;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.util.FastDFSClient;
import com.mzc.o2o.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultVo<Shop> addShop(Shop shop, @RequestParam("file") MultipartFile file) {
        if (shop == null || StringUtils.isBlank(shop.getOwnerId() + "") || StringUtils.isBlank(shop.getShopName())) {
            throw new RuntimeException("shop新增，参数错误,shop:[" + shop + "]");
        }
        String url = "";
        if (file != null) {
            url = uploadFile(file);
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
            url = uploadFile(file);
        }
        shop.setShopImg(url);
        shop.setLastEditTime(new Date());
        int effectiveNum = shopService.update(shop);
        if (effectiveNum == 0) {
            return buildEmptyResultVo();
        }
        return buildResultVo(shop, effectiveNum);
    }

    private String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String url = "";
        try {
            FastDFSClient fastDFSClient = new FastDFSClient();
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            url = CommonConst.FILE_SERVER_URL + path;
        } catch (Exception e) {
            log.error("shop:上传图片失败..." + e);
        }
        return url;
    }
}
