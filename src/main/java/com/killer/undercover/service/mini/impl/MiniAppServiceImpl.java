package com.killer.undercover.service.mini.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import com.killer.undercover.dto.mini.req.CreateRoomReq;
import com.killer.undercover.dto.mini.req.JoinRoomReq;
import com.killer.undercover.dto.mini.req.MiniAppUserLoginReq;
import com.killer.undercover.dto.mini.res.MemberUserInfoRes;
import com.killer.undercover.dto.mini.res.RoomRes;
import com.killer.undercover.entity.Room;
import com.killer.undercover.service.base.*;
import com.killer.undercover.service.mini.MiniAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:35
 */
@Slf4j
@Service
public class MiniAppServiceImpl implements MiniAppService {

    @Resource
    private PlayerService playerService;
    @Resource
    private RoomPlayerService roomPlayerService;
    @Resource
    private RoomService roomService;
    @Resource
    private RoomWordsService roomWordsService;
    @Resource
    private WordsService wordsService;

    @Override
    public RoomRes createRoom(CreateRoomReq createRoomReq) {
        if (createRoomReq.getWhiteboardNum() > 1){
            throw new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED);
        }
        Room room = new Room();
        BeanUtils.copyProperties(createRoomReq,room);
        //计算随机房间号
        //优化房间号重复问题TODO
        int roomKeyInt = (int) ((Math.random() * 9 + 1) * 100000);
        String formatKey = "%06d";
        String roomKey = String.format(formatKey, roomKeyInt);
        room.setRoomKey(roomKey);
        room.setStatus(true);
        roomService.save(room);

        log.info("创建房间成功");
        return new RoomRes().setRoomKey(roomKey);
    }

    @Override
    public RoomRes joinRoom(JoinRoomReq joinRoomReq) {
        Room room = roomService.findByKeyAndStatus(joinRoomReq.getRoomKey(),true);
        if (room == null) {
            throw new BusinessException(ErrorCodeEnum.ROOM_NOT_FIND);
        }
        return new RoomRes().setRoomKey(room.getRoomKey());
    }
}