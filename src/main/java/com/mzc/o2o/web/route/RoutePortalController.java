package com.mzc.o2o.web.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("/route/portal")
public class RoutePortalController {

    @RequestMapping("/mainpage")
    public String mainpage(){
        return "portal/index";
    }
}
