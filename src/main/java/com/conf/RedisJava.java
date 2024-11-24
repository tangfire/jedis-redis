package com.conf;//引入Redis驱动程序
import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        // 如果设置 Redis 服务的密码，需要进行验证，若没有则可以省去
         jedis.auth("8888.216");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        //设置 redis 字符串数据
        jedis.set("webkey", "swawdwa");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("webkey"));
    }
}
