package com.utils;
 
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
import java.util.ResourceBundle;
 
/**
 * 连接池工具类
 */
public class JedisUtils {
 
    //创建一个连接对象
    private static JedisPool pool;
 
    static {
        //创建连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数和最长等待时间
        ResourceBundle bundle = ResourceBundle.getBundle("jedis");
        //得到配置文件中的属性值
        String host = bundle.getString("host");
        int port = Integer.parseInt(bundle.getString("port"));
        int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
        int maxWaitMillis = Integer.parseInt(bundle.getString("maxWaitMillis"));
        String password = bundle.getString("password");
        //设置配置对象的参数
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        //创建连接池对象
        // 创建连接池对象，如果存在密码，传入密码
        if (password != null && !password.isEmpty()) {
            pool = new JedisPool(config, host, port, 2000, password); // 密码参数
        } else {
            pool = new JedisPool(config, host, port);  // 无密码的情况
        }



    }
 
    /**
     * 得到redis连接对象
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }
 
}