package com.mzc.o2o.web.admin;

import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.entity.Area;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.enums.ShopStateEnum;
import com.mzc.o2o.service.AreaService;
import com.mzc.o2o.service.ShopService;
import com.mzc.o2o.util.FileUploadUtil;
import com.mzc.o2o.util.ValidationUtil;
import com.mzc.o2o.util.VerifyCodeUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.vo.ShopQueryCondition;
import com.mzc.o2o.vo.ShopVo;
import com.mzc.o2o.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private RedisTemplate redisTemplate;

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

//        使用校验工具类 TODO TEST
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(shop);
        if(validResult.hasErrors()){
            String errors = validResult.getErrors();
            return buildFailResultVo(errors,0);
        }

        //TODO 商铺的owner是否为当前登陆用户，如果是，就从session获取，取决于业务
//        PersonInfo personInfo = (PersonInfo)request.getSession().getAttribute("user");
//        shop.setOwnerId(personInfo.getUserId());

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
//           TODO 将当前用户下的所有商铺存储在session
            HttpSession session = request.getSession();
            List<Shop> shopList = (List<Shop>) session.getAttribute("shopList");
            if (shopList == null) {
                shopList = new ArrayList<>();
            }
            shopList.add(shop);
            session.setAttribute("shopList", shopList);
            return buildResultVo(shop, 1);
        }
        return buildEmptyResultVo();
    }

    /**
     * 商铺修改 TODO
     *
     * @param regShopStr
     * @param verifyCode
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/updateShop")
    public ResultVo<String> updateShop(String regShopStr, String verifyCode,
                                       @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        boolean verifyCodeOk = VerifyCodeUtil.checkVerifyCode(verifyCode, request);
        if (!verifyCodeOk) {
            log.error("【addShop】验证码校验失败...");
            return buildEmptyResultVo();
        }
        JSONObject jsonObj = JSONObject.fromObject(regShopStr);
        Shop shop = (Shop) JSONObject.toBean(jsonObj, Shop.class);

        if (StringUtils.isBlank(shop.getShopId() + "")) {
            return buildResultVo("shopId不可为空", 0);
        }
        if (file != null) {
            try {
                String url = "";
                url = FileUploadUtil.uploadFile(file);
                shop.setShopImg(url);
            } catch (Exception e) {
                return buildResultVo("上传图片失败", 0);
            }
        }

        shop.setLastEditTime(new Date());
        boolean re = shopService.updateById(shop);
        Shop _shop = shopService.selectById(shop.getShopId());
        if (re) {
            redisTemplate.opsForHash().get(CommonConst.SHOPDETAIL, shop.getShopId());
            return buildResultVo(_shop, 1);
        }
        return buildFailResultVo(null, 0);
    }

    /**
     * 获取名字为shopName的商铺个数
     *
     * @param shopName
     * @return
     */
    @GetMapping("/getCountByName/{shopName}")
    public ResultVo<String> getCountByName(@PathVariable("shopName") String shopName) {
        Integer count = (Integer) redisTemplate.opsForHash().get(CommonConst.SHOPNUM, shopName);
        if (count == null) {
            count = shopService.getCountByName(shopName);
            redisTemplate.opsForHash().put(CommonConst.SHOPNUM, shopName, count);
        }
        return buildResultVo("", count);
    }

    /**
     * 查看商铺详情：带area名、owner名等
     *
     * @param shopId
     * @return
     */
    @GetMapping("/queryShopWithName/{shopId}")
    public Map<String, Object> queryShopWithName(@PathVariable("shopId") Integer shopId) {
        Map<String, Object> modelMap = new HashMap<>();
        ShopVo shopVo = (ShopVo) redisTemplate.opsForHash().get(CommonConst.SHOPDETAIL, shopId);
        if(shopVo == null){
            shopVo = shopService.queryShopWithName(shopId);
            redisTemplate.opsForHash().put(CommonConst.SHOPDETAIL, shopId, shopVo);
        }
        List<Area> areaList = areaService.queryList();
        modelMap.put("shopVo", shopVo);
        modelMap.put("areaList", areaList);
        modelMap.put("success", true);
        return modelMap;
    }

    /**
     * 按商铺条件进行分页查询，ShopQueryCondition
     *
     * @param condition
     * @param current
     * @param size
     * @return
     */
    @PostMapping("/queryByConditionsPage/{current}/{size}")
    public ResultVo<List<Shop>> queryByConditionsPage(@RequestBody ShopQueryCondition condition,
                                                      @PathVariable(value = "current", required = false) Integer current,
                                                      @PathVariable(value = "size", required = false) Integer size) {
        ResultVo<List<Shop>> resultVo = new ResultVo<>();
        List<Shop> shopList = shopService.queryByConditionsPage(condition, current, size);
        List<Shop> countShopList = shopService.queryByConditionsPage(condition, 1, Integer.MAX_VALUE);
        return buildResultVoPage(shopList, countShopList.size(), current, size);
    }
}