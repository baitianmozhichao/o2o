package com.mzc.o2o.web.wechat;

import com.mzc.o2o.util.weixin.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("wechat")
public class WechatController {

    private static Logger log = LoggerFactory.getLogger(WechatController.class);

    @GetMapping
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin get...");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                log.debug("weixin get success....");
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null){
                out.close();
            }
        }
    }
}
