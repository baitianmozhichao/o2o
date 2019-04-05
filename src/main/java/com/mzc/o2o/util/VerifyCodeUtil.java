package com.mzc.o2o.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 验证码校验工具类
 *
 * @auther: mzc
 */
public class VerifyCodeUtil {
    public static boolean checkVerifyCode(String verifyCode, HttpServletRequest request){
        if (StringUtils.isBlank(verifyCode)){
            return false;
        }
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if(code.equalsIgnoreCase(verifyCode)){
            return true;
        }
        return false;
    }
}
