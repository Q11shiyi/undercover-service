package com.killer.undercover.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.killer.undercover.entity.RoomPlayer;
import com.killer.undercover.service.base.RoomPlayerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.killer.undercover.mapper.RoomPlayerMapper;

import javax.annotation.Resource;
import java.util.List;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
@Service
public class RoomPlayerServiceImpl extends ServiceImpl<RoomPlayerMapper, RoomPlayer> implements RoomPlayerService {


    @Resource
    private RoomPlayerMapper roomPlayerMapper;

    @Override
    public List<RoomPlayer> listByRoomId(Long id) {
        // 获取该房间在线玩家
        return roomPlayerMapper.selectList(new QueryWrapper<RoomPlayer>().eq("room_id", id).eq("online",true));
    }

    @Override
    public RoomPlayer findByRooIdAndPlayerId(Long roomId, String playerId) {
        return roomPlayerMapper.selectOne(new QueryWrapper<RoomPlayer>().eq("room_id", roomId).eq("player_id",playerId));
    }
}
