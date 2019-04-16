package com.mzc.o2o.web.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.common.ErrorType;
import com.mzc.o2o.entity.ProductCategory;
import com.mzc.o2o.service.ProductCategoryService;
import com.mzc.o2o.util.BindingResultUtil;
import com.mzc.o2o.util.ValidationUtil;
import com.mzc.o2o.vo.BatchSaveProCatVo;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        if(productCategoryList == null || productCategoryList.size()==0){
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
    public ResultVo<ProductCategory> addProductCat(@Valid ProductCategory productCategory, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return buildFailResultVo(BindingResultUtil.bindResult2Str(bindingResult),0);
        }
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

    /**
     * 批量 新增/修改 商品分类
     * @param batchSaveProCatVo
     * @return
     */
    @PostMapping("/saveBatchProCat")
    public ResultVo saveBatchProCat(@RequestBody BatchSaveProCatVo batchSaveProCatVo){
        System.out.println(batchSaveProCatVo.getProductCatListStr());
        System.out.println(batchSaveProCatVo.getShopId());
        Integer shopId = Integer.parseInt(batchSaveProCatVo.getShopId());
        JSONArray jsonArray = JSONArray.fromObject(batchSaveProCatVo.getProductCatListStr());
//        提交的
        List<ProductCategory> productCategoryList = (List<ProductCategory>) JSONArray.toCollection(jsonArray,ProductCategory.class);
        for(ProductCategory productCategory: productCategoryList){
            ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(productCategory);
            if(validResult.isHasErrors()){
                return buildFailResultVo(validResult.getErrors(),0);
            }
            productCategory.setShopId(shopId);
//            TODO
            productCategory.setCreateTime(new Date());
            productCategory.setLastEditTime(new Date());
        }
        if(productCategoryList.size() == 0){
            return buildEmptyResultVo();
        }

//        提交的Ids
        List<Integer> newProductCatIds = productCategoryList.stream().map(ProductCategory::getProductCategoryId).collect(Collectors.toList());

//        原有的Id集合
        List<Integer> oldProductCatIds = productCategoryService.getAllProCatIdList(shopId);

        oldProductCatIds.removeAll(newProductCatIds);
        if(oldProductCatIds.size() != 0){
            productCategoryService.deleteBatchIds(oldProductCatIds);
        }
        boolean result = productCategoryService.insertOrUpdateBatch(productCategoryList);
        if(result){
            Long effectNum = redisTemplate.opsForHash().delete(CommonConst.PRODUCT_CAT,productCategoryList.get(0).getShopId());
            log.info("缓存中删除{}条商品分类的记录",effectNum);
            return buildResultVo(productCategoryList,productCategoryList.size());
        }
        return buildFailResultVo("新增失败",0);
    }
}
