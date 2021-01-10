package com.killer.undercover.controller.socket;


import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import com.killer.undercover.dto.mini.res.GameRes;
import com.killer.undercover.dto.mini.res.HeartbeatRes;
import com.killer.undercover.dto.mini.res.PlayerRes;
import com.killer.undercover.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


/**
 * 注解@ServerEndpoint 这个注解有什么作用？
 * <p>
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 * 注解@Component  //注册到spring容器中
 * 注解@ServerEndpoint(value = "/websocket") //接受websocket请求路径
 *
 * @author: huanghuiqiang
 * @create: 21.1.9 17:41
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocket {


    /**
     * 保存所有在线socket连接
     */
    private static final Map<String, WebSocket> WEB_SOCKET_MAP = new LinkedHashMap<>();

    /**
     * 记录当前在线数目
     */
    private static int count = 0;

    /**
     * 当前连接（每个websocket连入都会创建一个MyWebSocket实例
     */
    private Session session;

    /**
     * 当前连接玩家业务ID
     */
    private String playerId;


    /**
     * 处理连接建立
     * 客户端携带房间号和玩家ID请求服务端建立连接
     * 1. 校验该房间是否存在
     * 2. 判断当前玩家是否是第一位玩家，则设置为房主，并插入中间表记录
     * 3. 当前玩家不是第一位玩家则分配序号入座
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        String roomKey = session2roomKey(session);
        String playerId = session2playerId(session);
        this.session = session;
        this.playerId = playerId;

        WEB_SOCKET_MAP.put(playerId, this);

        addCount();
        log.info("新的玩家加入房间：{}，玩家ID：{}", roomKey, playerId);
        try {
            this.gameHandle("join");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String session2roomKey(Session session) {
        List<String> roomKeys = Optional.of(session.getRequestParameterMap().get("roomKey"))
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED));
        if (roomKeys.size() != 1) {
            throw new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED);
        }
        return roomKeys.get(0);

    }

    private String session2playerId(Session session) {
        List<String> playerIds = Optional.of(session.getRequestParameterMap().get("playerId"))
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED));
        if (playerIds.size() != 1) {
            throw new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED);
        }
        return playerIds.get(0);

    }

    /**
     * 接受消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端玩家{} 消息：{}", this.playerId, message);
        try {
            this.gameHandle(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gameHandle(String message) throws IOException {
        String url = "https://portrait.gitee.com/uploads/avatars/user/1698/5094699_hhq11_1608629649.png!avatar200";
        if ("join".equals(message)) {
            log.info("玩家推送消息：新玩家加入房间");
            GameRes gameRes = new GameRes();
            PlayerRes playerRes1 = new PlayerRes().setOwnerStatus(true).setPlayerId(Long.valueOf(this.playerId)).setRole("卧底").setWord("红茶").setAvatarUrl(url).setName("名称").setIndex(1).setAliveStatus(true);
            PlayerRes playerRes2 = new PlayerRes().setOwnerStatus(false).setPlayerId(2L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(2).setAliveStatus(true);
            PlayerRes playerRes3 = new PlayerRes().setOwnerStatus(false).setPlayerId(3L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(3).setAliveStatus(true);
            PlayerRes playerRes4 = new PlayerRes().setOwnerStatus(false).setPlayerId(4L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true);
            List<PlayerRes> playerList = new ArrayList<>();
            playerList.add(playerRes1);

            gameRes.setStatus(0).setPlayerList(playerList).setCivilianWord("绿茶").setUndercoverWord("红茶").setWinRole("卧底").setIndex(1).setWord("红茶");
            this.sendMessage(JsonUtils.toJson(gameRes));
        } else if ("start".equals(message)) {
            log.info("玩家推送消息：开始游戏");
            GameRes gameRes = new GameRes();
            PlayerRes playerRes1 = new PlayerRes().setOwnerStatus(true).setPlayerId(Long.valueOf(this.playerId)).setRole("卧底").setWord("红茶").setAvatarUrl(url).setName("名称").setIndex(1).setAliveStatus(true);
            PlayerRes playerRes2 = new PlayerRes().setOwnerStatus(false).setPlayerId(2L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(2).setAliveStatus(true);
            PlayerRes playerRes3 = new PlayerRes().setOwnerStatus(false).setPlayerId(3L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(3).setAliveStatus(true);
            PlayerRes playerRes4 = new PlayerRes().setOwnerStatus(false).setPlayerId(4L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true);

            List<PlayerRes> playerList = new ArrayList<>();
            playerList.add(playerRes1);
            playerList.add(playerRes2);
            playerList.add(playerRes3);
            playerList.add(playerRes4);
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(5L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(6L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(7L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(8L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(9L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(10L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(11L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(12L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));

            gameRes.setStatus(1).setPlayerList(playerList).setCivilianWord("绿茶").setUndercoverWord("红茶").setWinRole("卧底").setWord("红茶").setIndex(1).setWord("红茶");
            this.sendMessage(JsonUtils.toJson(gameRes));
        } else if (message.contains("kill=")) {
            String killPlayerId = message.replace("kill=", "");
            log.info("玩家推送消息：杀死玩家,玩家死亡：角色为平民"+ killPlayerId);
            GameRes gameRes = new GameRes();
            PlayerRes playerRes1 = new PlayerRes().setOwnerStatus(true).setPlayerId(Long.valueOf(this.playerId)).setRole("卧底").setWord("红茶").setAvatarUrl(url).setName("名称").setIndex(1).setAliveStatus(true);
            PlayerRes playerRes2 = new PlayerRes().setOwnerStatus(false).setPlayerId(2L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(2).setAliveStatus(true);
            PlayerRes playerRes3 = new PlayerRes().setOwnerStatus(false).setPlayerId(3L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(3).setAliveStatus(true);
            PlayerRes playerRes4 = new PlayerRes().setOwnerStatus(false).setPlayerId(4L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true);
            List<PlayerRes> playerList = new ArrayList<>();


            if ("1".equals(killPlayerId)){
                log.info("玩家推送消息：杀死玩家,玩家死亡：角色为卧底"+ killPlayerId);
                log.info("平民胜利");
                playerList.add(playerRes1.setAliveStatus(false));
                playerList.add(playerRes2);
                playerList.add(playerRes3);
                playerList.add(playerRes4);
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(5L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(6L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(7L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(8L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(9L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(10L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(11L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
                playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(12L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));


                gameRes.setStatus(3).setPlayerList(playerList).setCivilianWord("绿茶").setUndercoverWord("红茶").setWinRole("平民").setWord("红茶").setIndex(1).setWord("红茶");
                this.sendMessage(JsonUtils.toJson(gameRes));
                return;
            }
            playerList.add(playerRes1);
            playerList.add(playerRes2);
            playerList.add(playerRes3);
            playerList.add(playerRes4.setAliveStatus(false));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(5L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(6L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(7L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(8L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(9L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(10L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(11L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));
            playerList.add(new PlayerRes().setOwnerStatus(false).setPlayerId(12L).setRole("平民").setWord("绿茶").setAvatarUrl(url).setName("名称").setIndex(4).setAliveStatus(true));


            gameRes.setStatus(2).setPlayerList(playerList).setCivilianWord("绿茶").setUndercoverWord("红茶").setWord("红茶").setIndex(1).setWord("红茶");
            this.sendMessage(JsonUtils.toJson(gameRes));
        } else if ("ping".equals(message)){
            log.info("玩家推送消息：心跳");
            this.sendMessage(JsonUtils.toJson(new HeartbeatRes()));
        }
    }

    /**
     * 处理错误
     *
     * @param error
     * @param session
     */
    @OnError
    public void onError(Throwable error, Session session) {
        log.info("发生错误{},{}", session.getId(), error.getMessage());
    }

    /**
     * 处理连接关闭
     */
    @OnClose
    public void onClose() {
        WEB_SOCKET_MAP.remove(this.playerId);
        reduceCount();
        log.info("连接关闭:{}", this.playerId);
    }


    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 广播消息
     */
    public static void broadcast() {
        WebSocket.WEB_SOCKET_MAP.forEach((k, v) -> {
            try {
                log.info("测试广播触发");
                v.sendMessage("这是一条测试广播,当前在线人数：" + getCount());
            } catch (Exception e) {
                log.info("测试广播异常" + e);
            }
        });
    }

    /**
     * 获取在线连接数目
     *
     * @return 在线连接数目
     */
    public static int getCount() {
        return count;
    }

    /**
     * 操作count，使用synchronized确保线程安全
     */
    public static synchronized void addCount() {
        WebSocket.count++;
    }

    public static synchronized void reduceCount() {
        WebSocket.count--;
    }
}
