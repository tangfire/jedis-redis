package com.fire.example01;
 

import com.utils.JedisUtils;
import redis.clients.jedis.Jedis;
 
import java.util.Map;
 
/**
 * 使用工具类
 */
public class Demo4 {
 
    public static void main(String[] args) {
        //从工具类中得到Jedis对象
        Jedis jedis = JedisUtils.getJedis();
        //创建hash对象：键employee，添加字段名：name，值：NewBoy；字段名: salary，值：3000
        jedis.hset("employee", "name","NewBoy");
        jedis.hset("employee", "salary","3000");
        //使用hgetall读取hash对象输出
        Map<String, String> employee = jedis.hgetAll("employee");
        System.out.println(employee);
        //关闭jedis对象
        jedis.close();
    }
 
}