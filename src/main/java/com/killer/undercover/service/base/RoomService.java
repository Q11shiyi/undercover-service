package com.killer.undercover.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.killer.undercover.entity.Room;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
public interface RoomService extends IService<Room>{


    /**
     * 获取房间
     * @param roomKey
     * @param status
     * @return
     */
    Room findByKeyAndStatus(String roomKey, boolean status);
}
