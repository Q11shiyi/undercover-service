package com.killer.undercover.dto.mini.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 23:48
 */
@Data
@Accessors(chain = true)
public class GameRes {

    /**
     * 房间状态 0:未开始 1:开始游戏 2：投票选出 3：游戏结束
     */
    @ApiModelProperty(value = "房间状态 0:新玩家进入 1:开始游戏 2：投票选出 3：游戏结束")
    private Integer status;

    /**
     * 本轮胜利角色
     */
    @ApiModelProperty(value = "本轮胜利角色")
    private String winRole;

    /**
     * 本轮我的序号
     */
    @ApiModelProperty(value = "本轮我的序号")
    private Integer index;

    /**
     * 本轮我的词语
     */
    @ApiModelProperty(value = "本轮我的词语")
    private String word;

    /**
     * 平民词语
     */
    @ApiModelProperty(value = "平民词语")
    private String civilianWord;
    /**
     * 卧底词语
     */
    @ApiModelProperty(value = "卧底词语")
    private String undercoverWord;

    /**
     * 玩家列表
     */
    @ApiModelProperty(value = "玩家列表")
    private List<PlayerRes> playerList;





}