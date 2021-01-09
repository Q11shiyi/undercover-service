package com.killer.undercover.service.mini;

import com.killer.undercover.dto.mini.req.CreateRoomReq;
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
     * 解密用户信息
     * @param request request
     * @param miniAppUserLoginReq miniAppUserLoginReq
     * @return MemberUserInfoRes
     */
    MemberUserInfoRes authUserInfo(HttpServletRequest request, MiniAppUserLoginReq miniAppUserLoginReq);

    /**
     * 获取游戏规则
     * @return 游戏规则
     */
    String getGameRules();

    /**
     * 创建房间
     * @param createRoomReq 请求
     * @return 房间
     */
    RoomRes createRoom(CreateRoomReq createRoomReq);
}