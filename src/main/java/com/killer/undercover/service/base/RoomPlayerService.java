package com.killer.undercover.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.killer.undercover.entity.RoomPlayer;

import java.util.List;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
public interface RoomPlayerService extends IService<RoomPlayer>{


    List<RoomPlayer> listByRoomId(Long id);

    RoomPlayer findByRooIdAndPlayerId(Long roomId, String playerId);
}
