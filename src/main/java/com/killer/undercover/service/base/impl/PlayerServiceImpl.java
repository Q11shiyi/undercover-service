package com.killer.undercover.service.base.impl;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.killer.undercover.entity.Player;
import com.killer.undercover.service.base.PlayerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.killer.undercover.mapper.PlayerMapper;

import javax.annotation.Resource;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

    @Resource
    private PlayerMapper playerMapper;

    @Override
    public void register(WxMaUserInfo userInfo) {
        Player player = new Player()
                .setName(userInfo.getNickName())
                .setSex(Integer.valueOf(userInfo.getGender()))
                .setAvatarUrl(userInfo.getAvatarUrl())
                .setOpenId(userInfo.getOpenId())
                .setUnionId(userInfo.getUnionId())
                ;

        QueryWrapper<Player> queryWrapper = new QueryWrapper<Player>()
                .eq("open_id",userInfo.getOpenId());
        Player p = playerMapper.selectOne(queryWrapper);
        if (p == null) {
            playerMapper.insert(player);
        } else {
            player.setId(p.getId());
            playerMapper.updateById(player);
        }

    }
}
