package com.killer.undercover.service.base;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.killer.undercover.entity.Player;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
public interface PlayerService extends IService<Player>{


    void register(WxMaUserInfo userInfo);
}
