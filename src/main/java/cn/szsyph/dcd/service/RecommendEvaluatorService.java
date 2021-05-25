package cn.szsyph.dcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RecommendEvaluatorService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

}
