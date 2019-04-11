package com.mzc.o2o.web.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.common.ErrorType;
import com.mzc.o2o.entity.ProductCategory;
import com.mzc.o2o.service.ProductCategoryService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: mzc
 *
 * Hash缓存：key : CommonConst.PRODUCT_CAT + shopId
 */
@RestController
@RequestMapping("/productCat")
@Slf4j
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据shopId获取所有商品分类
     * @param shopId
     * @return
     */
    @GetMapping("/getProCatsByShopId/{shopId}")
    public ResultVo<List<ProductCategory>> getProCatsByShopId(@PathVariable("shopId") Integer shopId){
        List<ProductCategory> productCategoryList = (List<ProductCategory>) redisTemplate.opsForHash().get(CommonConst.PRODUCT_CAT,shopId);
        if(productCategoryList.size()==0){
            productCategoryList = productCategoryService.selectList(new EntityWrapper<ProductCategory>().eq("shop_id", shopId));
            redisTemplate.opsForHash().put(CommonConst.PRODUCT_CAT,shopId,productCategoryList);
        }
        return buildResultVo(productCategoryList, productCategoryList.size());
    }

    /**
     * 新增
     * @param productCategory
     * @return
     */
    @PostMapping("/addProductCat")
    public ResultVo<ProductCategory> addProductCat(ProductCategory productCategory){
        boolean sffectNum = productCategoryService.insert(productCategory);
        if(sffectNum){
            Long effectNum = redisTemplate.opsForHash().delete(CommonConst.PRODUCT_CAT,productCategory.getShopId());
            return buildResultVo(productCategory,1);
        }else {
            return buildEmptyResultVo();
        }
    }

    /**
     * 根据productCategoryId删除
     * @param productCategoryId
     * @return
     */
    public ResultVo<ProductCategory> deleteById(Integer productCategoryId){
        if(productCategoryId == null){
            return buildResultVo(ErrorType.PARAM_ERROR, 0);
        }
        ProductCategory productCategory = productCategoryService.selectById(productCategoryId);
        boolean sffectNum = productCategoryService.deleteById(productCategoryId);
        if(sffectNum){
            Long effectNum = redisTemplate.opsForHash().delete(CommonConst.PRODUCT_CAT,productCategory.getShopId());
            return buildResultVo(productCategory,1);
        }else {
            return buildEmptyResultVo();
        }
    }
}
