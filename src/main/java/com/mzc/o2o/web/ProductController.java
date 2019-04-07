package com.mzc.o2o.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.entity.Product;
import com.mzc.o2o.service.ProductService;
import com.mzc.o2o.util.FileUploadUtil;
import com.mzc.o2o.vo.ProductQueryCondition;
import com.mzc.o2o.vo.ProductVo;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * TODO 未测试
 *
 * @auther: mzc
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResultVo<Product> addProduct(Product product, MultipartFile file) {

        String url = "";
        if (file != null) {
            url = FileUploadUtil.uploadFile(file);
        }
        product.setImgAddr(url);
        productService.insert(product);
        return buildResultVo(product, 1);
    }

    @PostMapping("/editProduct")
    public ResultVo<Product> editProduct(Product product, MultipartFile file) {

        if (file != null) {
            String url = "";
            url = FileUploadUtil.uploadFile(file);
            product.setImgAddr(url);
        }
        productService.updateById(product);
        return buildResultVo(product, 1);
    }

    /**
     * 根据id删除
     * @param productId
     * @return
     */
    @DeleteMapping("/deleteProduct/{productId}")
    public ResultVo<Integer> deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteById(productId);
        return buildResultVo(productId, 1);
    }

    /**
     * 条件分页查询
     * @param condition
     * @param current
     * @param size
     * @return
     */
    @RequestMapping("/getByConditionPage/{current}/{size}")
    public ResultVo<List<Product>> getByConditionPage(ProductQueryCondition condition, Integer current, Integer size) {
        List<Product> productList = productService.getByConditionPage(condition, current, size);
        return buildResultVo(productList, productList.size());
    }

    /**
     * 查询某个类别下的所有商品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/getByCatId/{categoryId}")
    public ResultVo<List<Product>> getByCatId(@PathVariable("categoryId") Integer categoryId) {
        List<Product> productList = productService.selectList(
                new EntityWrapper<Product>().eq("product_category_id", categoryId));
        return buildResultVo(productList, productList.size());
    }

    /**
     * 根据productId获取商品详情，带分类名、所属店铺名
     *
     * @param productId
     * @return
     */
    @RequestMapping("/queryByIdWithName/{productId}")
    public ResultVo<ProductVo> queryByIdWithName(@PathVariable("productId") Integer productId) {
        ProductVo productVo = productService.queryByIdWithName(productId);
        return buildResultVo(productVo, 1);
    }
}
