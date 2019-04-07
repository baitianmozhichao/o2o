package com.mzc.o2o.web.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/shop")
public class RouteShopController {

    @GetMapping("/shopReg")
    public String shopReg(){
        return "shop/shopRegistry";
    }

    @GetMapping("/shopList")
    public String shopList(){
        return "shop/shopList";
    }
}
