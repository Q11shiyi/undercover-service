package com.killer.undercover.service.mini;

import com.killer.undercover.dto.mini.req.CreateRoomReq;
import com.killer.undercover.dto.mini.req.JoinRoomReq;
import com.killer.undercover.dto.mini.req.MiniAppUserLoginReq;
import com.killer.undercover.dto.mini.res.MemberUserInfoRes;
import com.killer.undercover.dto.mini.res.RoomRes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:34
 */
public interface MiniAppService {

    /**
     * 创建房间
     * @param createRoomReq 请求
     * @return 房间
     */
    RoomRes createRoom(CreateRoomReq createRoomReq);

    /**
     * 尝试加入该房间
     * @param joinRoomReq 请求
     * @return 房间
     */
    RoomRes joinRoom(JoinRoomReq joinRoomReq);
}