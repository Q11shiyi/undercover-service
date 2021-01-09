package com.killer.undercover.service.mini.impl;

import com.killer.undercover.dto.mini.req.CreateRoomReq;
import com.killer.undercover.dto.mini.req.MiniAppUserLoginReq;
import com.killer.undercover.dto.mini.res.MemberUserInfoRes;
import com.killer.undercover.dto.mini.res.RoomRes;
import com.killer.undercover.service.mini.MiniAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * @author: huanghuiqiang
 * @create: 21.1.9 10:35
 */
@Slf4j
@Service
public class MiniAppServiceImpl implements MiniAppService {

    @Override
    public MemberUserInfoRes authUserInfo(HttpServletRequest request, MiniAppUserLoginReq miniAppUserLoginReq) {
        log.info("这是我的get-user-info接口调用");
        return null;
    }

    @Override
    public String getGameRules() {
        return "我是一个接口上面获取的规则";
    }

    @Override
    public RoomRes createRoom(CreateRoomReq createRoomReq) {
        return null;
    }
}