package com.killer.undercover.dto.mini.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@Data
@ApiModel
public class MiniAppUserLoginReq {


  @ApiModelProperty(notes = "小程序appid")
  private String appId;


  @ApiModelProperty(notes = "授权code")
  private String code;


  @ApiModelProperty(notes = "rawdata")
  private String rawData;


  @ApiModelProperty(notes = "加密数据")
  private String encryptedData;


  @ApiModelProperty(notes = "签名")
  private String signature;


  @ApiModelProperty(notes = "iv")
  private String iv;

}
