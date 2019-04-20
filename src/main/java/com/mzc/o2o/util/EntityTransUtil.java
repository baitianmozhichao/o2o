package com.mzc.o2o.util;

import com.mzc.o2o.dto.WechatUser;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.enums.RoleTypeEnum;

import java.util.Date;

/**
 * 对象转换工具类
 *
 * @auther: mzc
 */
public class EntityTransUtil {

    /**
     * 根据微信账号信息生成部分本系统账号信息: wechatUser---->PersonInfo
     * @param wechatUser
     * @param roleType
     * @return
     */
    public static PersonInfo wechatUser2PersonInfo(WechatUser wechatUser, String roleType){
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(wechatUser.getNickName());
        personInfo.setGender(String.valueOf(wechatUser.getSex()));
        personInfo.setProfileImg(wechatUser.getHeadimgurl());
        personInfo.setCreateTime(new Date());
        personInfo.setLastEditTime(new Date());
        if(String.valueOf(RoleTypeEnum.CUSTOMER.getRoleType()).equals(roleType)){
            personInfo.setCustomerFlag(1);
            personInfo.setShopOwnerFlag(0);
            personInfo.setAdminFlag(0);
        }else if(String.valueOf(RoleTypeEnum.SELLER.getRoleType()).equals(roleType)){
            personInfo.setCustomerFlag(1);
            personInfo.setShopOwnerFlag(1);
            personInfo.setAdminFlag(0);
        }else if(String.valueOf(RoleTypeEnum.ADMIN.getRoleType()).equals(roleType)){
            personInfo.setCustomerFlag(1);
            personInfo.setShopOwnerFlag(1);
            personInfo.setAdminFlag(1);
        }
        return personInfo;
    }
}
