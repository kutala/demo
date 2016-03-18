package com.kuta.demo.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @notes:Redis 操作Key 
 * @author: kuta.li
 * @date: 2016年3月18日-下午5:16:59
 */
public class RedisKey {

    public static void main(String[] args) {
        Jedis jedis = RedisClient.getInstance();
        Set<String> set = jedis.keys("*");
        //set del
        jedis.set("key", "value");
        jedis.del("key");
        //遍历keys
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
            System.out.println(jedis.get(iterator.next().toString()));
        }
        //验证是否存在key 返回true、false
        System.out.println(jedis.exists("foo"));
        //设置过期时间（0：删除） 返回1：成功、0：失败
        System.out.println(jedis.expire("foo", 10));
        
    }
}
