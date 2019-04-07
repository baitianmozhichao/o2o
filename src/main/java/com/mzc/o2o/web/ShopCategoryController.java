package com.mzc.o2o.web;

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
     * 获取所有个人信息,用于新增商铺信息时shopCategory的选择
     * @return
     */
    @GetMapping("/getByParentId/{parentId}")
    public ResultVo<List<ShopCategory>> getByParentId(@PathVariable(value = "parentId",required = false) Integer parentId) {
        List<ShopCategory> shopCategoryList = shopCategoryService.queryByParentId(parentId);
        return buildResultVo(shopCategoryList,shopCategoryList.size());
    }

    @GetMapping("/getRootCats")
    public ResultVo<List<ShopCategory>> getRootCats(){
        List<ShopCategory> shopCategoryList = shopCategoryService.queryRootCats();
        return buildResultVo(shopCategoryList,shopCategoryList.size());
    }
}
