package com.killer.undercover.logic;

import com.killer.undercover.dto.mini.res.GameRes;

/**
 * <p>Title: WebSocketLogic</p>
 * <p>Description: </p>
 * <p>Company: ThinkMacro</p>
 * @author: huanghuiqiang
 * @create: 21.10.17 11:44
 */
public interface WebSocketLogic {

    /**
     * 加入房间
     * @param roomKey 房间号
     * @param playerId 玩家ID
     * @return 返回游戏信息
     */
    GameRes join(String roomKey, String playerId);

    GameRes close(String roomKey, String playerId);
}