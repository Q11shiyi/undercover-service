package com.killer.undercover.logic.impl;

import com.killer.undercover.logic.WebSocketLogic;
import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.dto.mini.res.GameRes;
import com.killer.undercover.dto.mini.res.PlayerRes;
import com.killer.undercover.entity.Player;
import com.killer.undercover.entity.Room;
import com.killer.undercover.entity.RoomPlayer;
import com.killer.undercover.service.base.PlayerService;
import com.killer.undercover.service.base.RoomPlayerService;
import com.killer.undercover.service.base.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: WebSocketLogicImpl</p>
 * <p>Description: </p>
 * <p>Company: ThinkMacro</p>
 * @author: huanghuiqiang
 * @create: 21.10.17 11:48
 */

@Service
public class WebSocketLogicImpl implements WebSocketLogic {


    @Resource
    private RoomPlayerService roomPlayerService;

    @Resource
    private PlayerService playerService;

    @Resource
    private RoomService roomService;

    @Override
    public GameRes join(String roomKey, String playerId) {
        // 判断房间不存在的情况
        Room room = roomService.findByKeyAndStatus(roomKey, true);
        if (room == null) {
            throw new  BusinessException("房间已解散");
        }
        // 判断房间人数是否超出
        int totalNum = room.getWhiteboardNum() + room.getCivilianNum() + room.getUndercoverNum();
        List<RoomPlayer> roomPlayers = roomPlayerService.listByRoomId(room.getId());
        if (roomPlayers.size() >= totalNum  ) {
            throw new  BusinessException("房间已满");
        }
        // 获取玩家信息
        Player player = playerService.getById(Long.valueOf(playerId));

        // 判断玩家是否曾经存在过，存在则重连
        RoomPlayer roomPlayer = roomPlayerService.findByRooIdAndPlayerId(room.getId(), playerId);
        if (roomPlayer == null){
            // 添加房间玩家表数据
            roomPlayer = new RoomPlayer();
            roomPlayer.setPlayerId(Long.valueOf(playerId));
            roomPlayer.setRoomId(room.getId());
            roomPlayer.setName(player.getName());
            roomPlayer.setAvatarUrl(player.getAvatarUrl());
            roomPlayer.setOnline(true);
            roomPlayerService.save(roomPlayer);
        } else {
            roomPlayer.setOnline(true);
            roomPlayerService.updateById(roomPlayer);
        }

        roomPlayers.add(roomPlayer);

        // 获取房间所有玩家列表
        return getGameRes(0,roomPlayers,room);
    }

    @Override
    public GameRes close(String roomKey, String playerId) {

        // 判断房间不存在的情况
        Room room = roomService.findByKeyAndStatus(roomKey, true);
        if (room == null) {
            throw new  BusinessException("房间已解散");
        }

        // 将玩家状态置为下线
        RoomPlayer roomPlayer = roomPlayerService.findByRooIdAndPlayerId(room.getId(), playerId);
        if (roomPlayer != null){
            roomPlayer.setOnline(false);
            roomPlayerService.updateById(roomPlayer);
        }

        List<RoomPlayer> roomPlayers = roomPlayerService.listByRoomId(room.getId());

        //如果该房间玩家全部下线，则房间解散
        if (roomPlayers.size() == 0){
            room.setStatus(false);
            roomService.updateById(room);
        }

        // 获取房间所有玩家列表
        return getGameRes(0,roomPlayers,room);


    }

    private GameRes getGameRes(Integer status, List<RoomPlayer> roomPlayers,Room room) {
        GameRes gameRes = new GameRes();
        BeanUtils.copyProperties(room,gameRes);
        boolean ownerStatus = true;
        int index = 1;
        List<PlayerRes> playerList = new ArrayList<>(roomPlayers.size());
        for (RoomPlayer rp : roomPlayers) {
            playerList.add(new PlayerRes()
                    .setOwnerStatus(ownerStatus)
                    .setPlayerId(rp.getPlayerId())
                    .setAvatarUrl(rp.getAvatarUrl())
                    .setName(rp.getName())
                    .setIndex(index)
                    .setAliveStatus(true));
            ownerStatus = false;
            index ++;
        }
        gameRes.setStatus(status).setPlayerList(playerList);
        return gameRes;
    }
}