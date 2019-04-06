package com.mzc.o2o.web.route;

import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/shop")
public class RouteShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopReg")
    public String shopReg(){
        return "shop/shopRegistry";
    }

    @GetMapping("/shopEdit")
    public String shopEdit(ModelMap modelMap, @RequestParam("shopId") Integer shopId){
        Shop shop = shopService.selectById(shopId);
        modelMap.addAttribute("shop",shop);
        return "shop/shopEdit";
    }
}
