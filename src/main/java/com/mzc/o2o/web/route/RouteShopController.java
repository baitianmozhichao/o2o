package com.mzc.o2o.web.route;

import com.mzc.o2o.service.AreaService;
import com.mzc.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/shop")
public class RouteShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    @GetMapping("/shopReg")
    public String shopReg(){
        return "shop/shopRegistry";
    }

    @GetMapping("/shopList")
    public String shopList(){
        return "shop/shopList";
    }
}
