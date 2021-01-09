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
 * 房间-玩家中间表
 *
 * @author: huanghuiqiang
 * @create: 21.1.9 10:15
 */
@ApiModel(value = "com-killer-undercover-entity-RoomPlayer")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "room_player")
public class RoomPlayer extends BaseEntity implements Serializable {
    /**
     * 房间ID
     */
    @TableField(value = "room_id")
    @ApiModelProperty(value = "房间ID")
    private Long roomId;

    /**
     * 玩家ID
     */
    @TableField(value = "player_id")
    @ApiModelProperty(value = "玩家ID")
    private Long playerId;

    private static final long serialVersionUID = 1L;


}