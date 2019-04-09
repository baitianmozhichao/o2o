package com.mzc.o2o.web.admin;

import com.mzc.o2o.entity.ShopCategory;
import com.mzc.o2o.service.ShopCategoryService;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: mzc
 */
@RestController
@RequestMapping("/shopCategory")
public class ShopCategoryController extends BaseController {
    @Autowired
    private ShopCategoryService shopCategoryService;

    /**
     * 获取某一根分类下的所有分类信息
     * @return
     */
    @GetMapping("/getByParentId/{parentId}")
    public ResultVo<List<ShopCategory>> getByParentId(@PathVariable(value = "parentId",required = false) Integer parentId) {
        List<ShopCategory> shopCategoryList = shopCategoryService.queryByParentId(parentId);
        return buildResultVo(shopCategoryList,shopCategoryList.size());
    }

    /**
     * 获取根分类
     * @return
     */
    @GetMapping("/getRootCats")
    public ResultVo<List<ShopCategory>> getRootCats(){
        List<ShopCategory> shopCategoryList = shopCategoryService.queryRootCats();
        return buildResultVo(shopCategoryList,shopCategoryList.size());
    }

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/getAll")
    public ResultVo<List<ShopCategory>> getAll() {
        List<ShopCategory> shopCategoryList = shopCategoryService.selectList(null);
        return buildResultVo(shopCategoryList, shopCategoryList.size());
    }
}
