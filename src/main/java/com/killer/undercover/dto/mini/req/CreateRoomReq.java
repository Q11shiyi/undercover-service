package com.killer.undercover.dto.mini.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:55
 */
@Data
@ApiModel
public class CreateRoomReq {

    /**
     * 平民数量
     */
    @ApiModelProperty(value = "平民数量")
    private Integer civilianNum;

    /**
     * 卧底数量
     */
    @ApiModelProperty(value = "卧底数量")
    private Integer undercoverNum;

    /**
     * 白板数量
     */
    @ApiModelProperty(value = "白板数量")
    private Integer whiteboardNum;
}