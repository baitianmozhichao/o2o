package com.mzc.o2o.web.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/product")
public class RouteProductController {

    @GetMapping("/productList")
    public String productList(){

        return "product/productList";
    }
}
