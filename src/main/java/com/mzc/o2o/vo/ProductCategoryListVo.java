package com.mzc.o2o.vo;

import com.mzc.o2o.entity.ProductCategory;
import lombok.Data;

import java.util.List;

/**
 * @auther: mzc
 */
@Data
public class ProductCategoryListVo {
    private List<ProductCategory> productCategoryList;
}
