package com.mzc.o2o.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 微信账号（openId）与本系统的用户信息（userId）绑定
 */
@Data
public class WechatAuth {
    @TableId
    private Integer wechatAuthId;

    @NotNull(message = "与微信绑定的userId不可为空")
    private String userId;

    @NotNull(message = "请指定公众号下用户的openId")
    private String openId;

    private Date createTime;
}