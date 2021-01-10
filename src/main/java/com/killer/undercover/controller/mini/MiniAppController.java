package com.killer.undercover.controller.mini;

import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import com.killer.undercover.common.response.Response;
import com.killer.undercover.controller.socket.WebSocket;
import com.killer.undercover.dto.mini.req.CreateRoomReq;
import com.killer.undercover.dto.mini.req.JoinRoomReq;
import com.killer.undercover.dto.mini.req.MiniAppUserLoginReq;
import com.killer.undercover.dto.mini.res.MemberUserInfoRes;
import com.killer.undercover.dto.mini.res.RoomRes;
import com.killer.undercover.service.mini.MiniAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: MiniAppController</p>
 * <p>Description: 小程序端接口</p>
 *
 * @author: huanghuiqiang
 * @create: 21.1.9 10:09
 */

@Api(tags = "小程序端相关接口")
@RestController
@RequestMapping("/mini")
public class MiniAppController {

    /**
     * 需要实现的接口如下
     * 保存游戏规则
     * 获取游戏规则
     * 获取小程序用户信息接口
     * 创建房间接口
     * 开始游戏发放词语接口
     * 发言接口
     * 房主双击杀死用户接口
     * 输入房间号加入房间接口
     */


    @Resource
    private HttpServletRequest request;

    @Resource
    private MiniAppService miniAppService;

    @GetMapping("/broadcast")
    public void broadcast(){
        WebSocket.broadcast();
    }

    @PostMapping("/room")
    @ApiOperation("创建房间")
    public Response<RoomRes> createRoom(@RequestBody CreateRoomReq createRoomReq) {
        return Response.success(miniAppService.createRoom(createRoomReq));
    }

    @PostMapping("/join")
    @ApiOperation("加入房间")
    public Response<RoomRes> joinRoom(@RequestBody JoinRoomReq joinRoomReq) {
        RoomRes roomRes = miniAppService.joinRoom(joinRoomReq);
        return Response.success(roomRes);
    }



}