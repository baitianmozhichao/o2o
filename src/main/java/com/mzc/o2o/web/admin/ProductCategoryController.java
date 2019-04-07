package com.mzc.o2o.web.admin;

import com.mzc.o2o.entity.Product;
import com.mzc.o2o.service.ProductService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/productCat")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public ResultVo<List<Product>> getAllProduct(){
        List<Product> productList = productService.selectList(null);
        return buildResultVo(productList, productList.size());
    }

    @DeleteMapping("/deleteById/{productId}")
    public Boolean deleteById(@PathVariable("productId") Integer productId){
        return productService.deleteById(productId);
    }

    /**
     * 根据id列表删除商品分类
     * @param productIds
     * @return
     */
    @PostMapping("/deleteByIds")
    public Boolean deleteByIds(List<Integer> productIds){
        return productService.deleteBatchIds(productIds);
    }
}
