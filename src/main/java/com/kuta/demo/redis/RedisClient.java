package com.kuta.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @notes: redis 客户端
 * @author: kuta.li
 * @date: 2016年3月18日-下午5:08:48
 */
public class RedisClient {
    
    //redis 主机IP
    public static final String HOST = "192.168.37.110";
    //redis 主机端口
    public static final Integer PORT = 6379;
    
    public static Jedis jedis;
    
    public static Jedis getInstance(){
        if(jedis == null){
            synchronized (RedisClient.class) {
                jedis = new Jedis(HOST, PORT);
//              redis.auth("redis");//验证密码    
            }
        }
        return jedis;
    }
}
