package com.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisUtil {

    private static final String CONNECT_STRING = "localhost:6379";

    private static RedisUtil util = null;

    public RedisUtil(){}

    public static RedisUtil getInstance(){
        if(util == null){
            synchronized (RedisUtil.class){
                util = new RedisUtil();
            }
        }
        return util;
    }

    public static RedissonClient getRedisClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(CONNECT_STRING);
        RedissonClient client = Redisson.create(config);
        return client;
    }

    public static void shutDown(RedissonClient client){
        client.shutdown();
    }

}
