package com.mzc.o2o.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mzc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/1 20:10
 */
public interface ProductCategoryDao extends BaseMapper<ProductCategory> {

    @Select("select product_category_id from tb_product_category where shop_id = #{shopId)}")
    List<Integer> getAllProCatIdList(Integer shopId);
}
