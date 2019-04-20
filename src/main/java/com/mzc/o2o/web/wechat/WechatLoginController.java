package com.mzc.o2o.web.wechat;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mzc.o2o.dto.UserAccessToken;
import com.mzc.o2o.dto.WechatUser;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.entity.WechatAuth;
import com.mzc.o2o.enums.RoleTypeEnum;
import com.mzc.o2o.service.PersonInfoService;
import com.mzc.o2o.service.WechatAuthService;
import com.mzc.o2o.service.WechatLoginService;
import com.mzc.o2o.util.EntityTransUtil;
import com.mzc.o2o.util.weixin.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: mzc
 */
@Controller
@RequestMapping("wechatlogin")
/**
 * 获取关注公众号之后的微信用户信息的接口，如果在微信浏览器里访问
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf1a5a14c7189c018&redirect_uri=http://mzcm.natapp1.cc/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 * 则这里将会获取到code,之后再可以通过code获取到access_token 进而获取到用户信息
 *
 * @author xiangze
 *
 */
public class WechatLoginController {

    private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);

    @Autowired
    private WechatAuthService wechatAuthService;

    @Autowired
    private WechatLoginService wechatLoginService;

    @Autowired
    private PersonInfoService personInfoService;

    /**
     * 登陆、账号关联、注册
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
    public String doGet(HttpServletRequest request, HttpServletResponse response) {
        log.debug("weixin login get...");
        String code = request.getParameter("code");
        String roleType = request.getParameter("state");
        log.debug("weixin login code:" + code);
        WechatUser user = null;
        String openId = null;
        if (null != code) {
            UserAccessToken token;
            try {
                token = WechatUtil.getUserAccessToken(code);
                String accessToken = token.getAccessToken();
                openId = token.getOpenId();
                user = WechatUtil.getUserInfo(accessToken, openId);
                request.getSession().setAttribute("openId", openId);
            } catch (IOException e) {
                log.error("error in getUserAccessToken or getUserInfo or findByOpenId: " + e.toString());
                e.printStackTrace();
            }
        }
        WechatAuth wechatAuth = wechatAuthService.selectOne(new EntityWrapper<WechatAuth>().eq("open_id",openId));

//        该微信账号未与系统账号绑定
        if(wechatAuth == null){
            PersonInfo personInfo = EntityTransUtil.wechatUser2PersonInfo(user, roleType);
//            关联账号 + 注册
            try {
                wechatLoginService.registryPersonInfo(personInfo,openId);
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println("openId为空，链接有问题...");
            }
        }

//        根据角色进行路由
        if(RoleTypeEnum.ADMIN.getRoleType().equals(Integer.parseInt(roleType))){
            return "admin/adminEnterPaage";
        }else if(RoleTypeEnum.SELLER.getRoleType().equals(Integer.parseInt(roleType))){
            return "shop/sellerEnterPage";
        }else if(RoleTypeEnum.CUSTOMER.getRoleType().equals(Integer.parseInt(roleType))){
            return "portal/index";
        }else {
            return null;
        }
    }


}