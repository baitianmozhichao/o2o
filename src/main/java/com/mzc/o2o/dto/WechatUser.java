package com.mzc.o2o.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户实体类
 *
 * @auther: mzc
 */
@Data
public class WechatUser implements Serializable {
    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("sex")
    private int sex;

    @JsonProperty("province")
    private String province;

    @JsonProperty("city")

    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("headimgurl")
    private String headimgurl;

    @JsonProperty("language")
    private String language;

    @JsonProperty("privilege")
    private String[] privilege;

}
