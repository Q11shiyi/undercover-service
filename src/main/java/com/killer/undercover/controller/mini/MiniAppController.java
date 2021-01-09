package com.killer.undercover.controller.mini;

import com.killer.undercover.common.response.Response;
import com.killer.undercover.dto.mini.req.CreateRoomReq;
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

    @PostMapping("/auth-user-info")
    @ApiOperation("小程序授权用户信息")
    public Response<MemberUserInfoRes> authUserInfo(@RequestBody MiniAppUserLoginReq miniAppUserLoginReq) {
        return Response.success(miniAppService.authUserInfo(request, miniAppUserLoginReq));
    }

    @GetMapping("/game-rules")
    @ApiOperation("获取游戏规则")
    public Response<String> getGameRules() {
        return Response.success(miniAppService.getGameRules());
    }

    @PostMapping("/room")
    @ApiOperation("创建房间")
    public Response<RoomRes> createRoom(@RequestBody CreateRoomReq createRoomReq) {
        return Response.success(miniAppService.createRoom(createRoomReq));
    }



}