package com.killer.undercover.dto.mini.res;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author: huanghuiqiang
 * @create: 21.1.10 0:21
 */
@Data
@Accessors(chain = true)
public class PlayerRes {


    /**
     * 玩家ID
     */
    @ApiModelProperty(value = "玩家ID")
    private Long playerId;

    /**
     * 玩家排序索引
     */
    @ApiModelProperty(value = "玩家排序索引")
    private Integer index;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 用户头像图片地址
     */
    @ApiModelProperty(value = "用户头像图片地址")
    private String avatarUrl;

    /**
     * 本轮词汇
     */
    @ApiModelProperty(value = "本轮词汇")
    private String word;

    /**
     * 角色身份
     */
    @ApiModelProperty(value = "角色身份")
    private String role;

    /**
     * 房主身份
     */
    @ApiModelProperty(value = "房主身份")
    private Boolean ownerStatus;

    /**
     * 存活状态
     */
    @ApiModelProperty(value = "存活状态")
    private Boolean aliveStatus;
}
