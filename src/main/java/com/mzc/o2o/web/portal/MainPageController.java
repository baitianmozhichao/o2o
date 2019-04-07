package com.mzc.o2o.web.portal;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.entity.HeadLine;
import com.mzc.o2o.entity.ShopCategory;
import com.mzc.o2o.enums.HeadLineStateEnum;
import com.mzc.o2o.service.HeadLineService;
import com.mzc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户前端系统的后台
 *
 * @auther: mzc
 */
@RestController
@RequestMapping("/mainpage")
public class MainPageController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private HeadLineService headLineService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询首页需要展示的信息
     *
     * @return
     */
    @GetMapping("/info")
    public Map<String, Object> listMainPageInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
//            商铺根分类
            List<ShopCategory> rootCategoryList = shopCategoryService.queryRootCats();
            modelMap.put("rootCategoryList", rootCategoryList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "查询商铺根分类信息失败");
            return modelMap;
        }

        try {
//            可展示的头条
            List<HeadLine> headLineList = (List<HeadLine>) redisTemplate.opsForList().leftPop(CommonConst.HEADLINE);
            if (headLineList == null || headLineList.size() == 0) {
                headLineList = headLineService.selectList(
                        new EntityWrapper<HeadLine>().eq("enable_status", HeadLineStateEnum.ACTIVE.getCode()));
                redisTemplate.opsForList().leftPush(CommonConst.HEADLINE, headLineList);
            }
            modelMap.put("headLineList", headLineList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "查询可展示的头条信息失败");
            return modelMap;
        }
        modelMap.put("success", true);
        return modelMap;
    }
}
