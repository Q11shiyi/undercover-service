package com.killer.undercover.dto.mini.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@Data
@ApiModel(value = "会员信息")
public class MemberUserInfoRes {
  /**
   * 小程序用户id
   */
  @ApiModelProperty(value = "玩家Id")
  private Long id;

  @ApiModelProperty(value = "union_id")
  private String unionId;

  @ApiModelProperty(value = "open_id")
  private String openId;


  @ApiModelProperty(value = "手机号码")
  private String mobile;

  /**
   * 小程序appId
   */
  @ApiModelProperty(value = "小程序appId")
  private String appId;

  /**
   * 昵称
   */
  @ApiModelProperty(value = "昵称")
  private String nickName;

  /**
   * 头像地址
   */
  @ApiModelProperty(value = "头像地址")
  private String avatarUrl;

  /**
   * 国家
   */
  @ApiModelProperty(value = "国家")
  private String country;

  /**
   * 省
   */
  @ApiModelProperty(value = "省")
  private String province;

  /**
   * 城市
   */
  @ApiModelProperty(value = "城市")
  private String city;

  /**
   * 性别
   */
  @ApiModelProperty(value = "性别")
  private String gender;
}