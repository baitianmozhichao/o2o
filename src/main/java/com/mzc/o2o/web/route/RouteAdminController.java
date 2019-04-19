package com.mzc.o2o.web.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/admin")
public class RouteAdminController {
    @RequestMapping("/productList")
    public String productList(){
        return "admin/productList";
    }

    @RequestMapping("/productAdd")
    public String productAdd(){
        return "admin/productAdd";
    }

    @RequestMapping("/productDetail")
    public String productDetail(){
        return "admin/productDetail";
    }
}
