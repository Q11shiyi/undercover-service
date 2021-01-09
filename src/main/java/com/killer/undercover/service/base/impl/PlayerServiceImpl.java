package com.killer.undercover.service.base.impl;

import com.killer.undercover.entity.Player;
import com.killer.undercover.service.base.PlayerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.killer.undercover.mapper.PlayerMapper;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

}
