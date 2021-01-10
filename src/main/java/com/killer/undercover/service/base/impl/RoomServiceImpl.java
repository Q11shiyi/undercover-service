package com.killer.undercover.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.killer.undercover.entity.Room;
import com.killer.undercover.service.base.RoomService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.killer.undercover.mapper.RoomMapper;

import javax.annotation.Resource;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public Room findByKeyAndStatus(String roomKey, boolean status) {
        return roomMapper.selectOne(new QueryWrapper<Room>()
                .eq("room_key", roomKey)
                .eq("status", status));
    }
}
