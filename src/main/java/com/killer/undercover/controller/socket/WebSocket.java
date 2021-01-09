package com.killer.undercover.controller.socket;


import com.killer.undercover.common.exception.BusinessException;
import com.killer.undercover.common.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
        this.session = session;
        List<String> roomKeys = Optional.of(session.getRequestParameterMap().get("roomKey"))
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED));
        List<String> playerIds = Optional.of(session.getRequestParameterMap().get("playerId"))
                .orElseThrow(() -> new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED));
        if (roomKeys.size() != 1 || playerIds.size() != 1) {
            throw new BusinessException(ErrorCodeEnum.AUTHENTICATION_FAILED);
        }
        String playerId = playerIds.get(0);

        WEB_SOCKET_MAP.put(playerId, this);

        addCount();
        log.info("新的连接加入：{}", session.getId());
    }

    /**
     * 接受消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端{}消息：{}", session.getId(), message);
        try {
            this.sendMessage("收到消息：" + message);
        } catch (Exception e) {
            e.printStackTrace();
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
        WEB_SOCKET_MAP.remove(this.session.getId());
        reduceCount();
        log.info("连接关闭:{}", this.session.getId());
    }


    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
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
            }
        });
    }

    //获取在线连接数目
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
