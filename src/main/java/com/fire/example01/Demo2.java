package com.fire.example01;
 
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;


/**
 * 创建Jedis连接池
 */
public class Demo2 {
 
    public static void main(String[] args) {
        //1)	创建连接池配置对象，设置最大连接数10，设置用户最大等待时间2000毫秒
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxWaitMillis(2000);

        //2)	通过配置对象做为参数，创建连接池对象
        JedisPool pool = new JedisPool(config, "localhost", 6379);

        //3)	从连接池里面获取jedis连接对象，执行redis命令。
        Jedis jedis = pool.getResource();
        jedis.auth("8888.216");
        //4)	执行redis命令sadd写入set集合类型的数据：students=白骨精,孙悟空,猪八戒
        jedis.sadd("students", "白骨精", "孙悟空", "猪八戒");
        //5)	执行redis命令smembers读取集合中的数据
        Set<String> students = jedis.smembers("students");
        //6)	输出读取的数据
        System.out.println(students);
        //7)	关闭连接对象(通常连接池不关闭)
        jedis.close();
        pool.close();
    }
}