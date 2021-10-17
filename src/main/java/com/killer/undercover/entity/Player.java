package com.killer.undercover.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.killer.undercover.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 玩家表
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@ApiModel(value = "com-killer-undercover-entity-Player")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "player")
@Accessors(chain = true)
public class Player extends BaseEntity implements Serializable {
    /**
     * 用户名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 性别:1男,2女
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别:1男,2女")
    private Integer sex;

    /**
     * 微信号
     */
    @TableField(value = "wx_id")
    @ApiModelProperty(value = "微信号")
    private String wxId;

    /**
     * 用户在小程序中的openid
     */
    @TableField(value = "open_id")
    @ApiModelProperty(value = "用户在小程序中的openid")
    private String openId;

    /**
     * 用户在小程序所属开放平台中的unionid
     */
    @TableField(value = "union_id")
    @ApiModelProperty(value = "用户在小程序所属开放平台中的unionid")
    private String unionId;

    /**
     * 用户头像图片地址
     */
    @TableField(value = "avatar_url")
    @ApiModelProperty(value = "用户头像图片地址")
    private String avatarUrl;

}