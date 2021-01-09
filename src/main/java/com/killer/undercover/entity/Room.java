package com.killer.undercover.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.killer.undercover.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 房间表
 *
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@ApiModel(value = "com-killer-undercover-entity-Room")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "room")
public class Room extends BaseEntity implements Serializable {
    /**
     * 房间号
     */
    @TableField(value = "key")
    @ApiModelProperty(value = "房间号")
    private String key;

    /**
     * 平民数量
     */
    @TableField(value = "civilian_num")
    @ApiModelProperty(value = "平民数量")
    private Integer civilianNum;

    /**
     * 卧底数量
     */
    @TableField(value = "undercover_num")
    @ApiModelProperty(value = "卧底数量")
    private Integer undercoverNum;

    /**
     * 白板数量
     */
    @TableField(value = "whiteboard_num")
    @ApiModelProperty(value = "白板数量")
    private Integer whiteboardNum;

    /**
     * 房间存活状态(0死亡，1存活)
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "房间存活状态(0死亡，1存活)")
    private Boolean status;

    private static final long serialVersionUID = 1L;

}