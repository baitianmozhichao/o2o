package com.mzc.o2o.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/4/1 20:10
 */
public interface ProductDao extends BaseMapper<Product> {

    @Select("SELECT product.*,productCat.product_category_name,shop.shop_name\n" +
            "FROM\n" +
            "\ttb_product product\n" +
            "\tLEFT JOIN tb_product_category productCat ON product.product_category_id = productCat.product_category_id \n" +
            "\tLEFT JOIN tb_shop shop ON product.shop_id = shop.shop_id \n" +
            "WHERE product.product_id = 4")
    ProductVo queryByIdWithName(@Param("productId") Integer productId);
}
