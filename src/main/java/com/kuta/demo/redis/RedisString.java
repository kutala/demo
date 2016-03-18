package com.kuta.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @notes:redis 操作String 
 * @author: kuta.li
 * @date: 2016年3月18日-下午5:16:09
 */
public class RedisString {
    
    public static void main(String[] args) {
        Jedis jedis = RedisClient.getInstance();
        jedis.set("foo", "bbb");
        String value = jedis.get("foo");  
        jedis.close();
        System.out.println(value);  
    }

}
