package com.fire.example01;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Jedis的基本操作
 */
public class Demo1 {
    public static void main(String[] args) {
        //创建Jedis连接对象
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("8888.216");
        //添加string类型
        jedis.set("person", "张三");
        //添加list类型
        jedis.lpush("cities", "广州","上海","东莞");
        //读取string类型
        String person = jedis.get("person");
        //读取list类型
        List<String> cities = jedis.lrange("cities", 0, -1);
        //输出到控制器上
        System.out.println("person:" + person);
        System.out.println("cities:" + cities);
        //关闭连接对象
        jedis.close();


    }
}
