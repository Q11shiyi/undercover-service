package com.killer.undercover.service.base.impl;

import com.killer.undercover.entity.Words;
import com.killer.undercover.service.base.WordsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.killer.undercover.mapper.WordsMapper;

/**
* @author: huanghuiqiang
* @create: 21.1.9 10:08
*/
@Service
public class WordsServiceImpl extends ServiceImpl<WordsMapper, Words> implements WordsService {

}
