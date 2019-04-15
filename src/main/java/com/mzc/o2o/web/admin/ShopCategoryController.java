package com.mzc.o2o.web.admin;

import com.mzc.o2o.entity.ShopCategory;
import com.mzc.o2o.service.ShopCategoryService;
import com.mzc.o2o.util.BindingResultUtil;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    /**
     * 根据id获取
     * @param catId
     * @return
     */
    @GetMapping("/getByCatId/{catId}")
    public ResultVo<ShopCategory> getByCatId(@PathVariable("catId") Integer catId){
        ShopCategory shopCategory = shopCategoryService.selectById(catId);
        return buildResultVo(shopCategory, 1);
    }

    /**
     * 新增商铺分类
     * @param shopCategory
     * @param bindingResult
     * @return
     */
    @PostMapping("/addShopCategory")
    public ResultVo<ShopCategory> addShopCategory(@Valid ShopCategory shopCategory, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return buildFailResultVo(BindingResultUtil.bindResult2Str(bindingResult),0);
        }
        boolean sffectNum = shopCategoryService.insert(shopCategory);
        if(sffectNum){
            return buildResultVo(shopCategory,1);
        }
        return buildResultVo("新增失败",0);
    }
}
