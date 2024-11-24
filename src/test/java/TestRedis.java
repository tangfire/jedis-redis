import com.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class TestRedis {
    public static void main(String[] args) {

    }

    /**
     * Jedis操作key
     * example:获取所有的key (keys *)
     */
    @Test
    public void testKeys(){
        Jedis jedis = JedisUtils.getJedis();   //创建Redis连接

        //添加值
        jedis.set("name","luccy");

        //获取值
        String name = jedis.get("name");
        System.out.println(name);   //返回name对应的value值：luccy

        long ttl = jedis.ttl("name");
        System.out.println(ttl);    //没有设置过期时间，返回-1则说明永不过期



        Set<String> keys = jedis.keys("*");  //得到Redis中所有的key。返回set集合
        //遍历显示Set集合
        for(String key:keys){
            System.out.println(key);   //此时有一个key（即name）
        }
        jedis.close();

    }


    /**
     * Jedis操作字符串（string）
     * example:mset同时设置一个或多个键值对，mget同时获取一个或多个value
     */
    @Test
    public void testString(){
        Jedis jedis = JedisUtils.getJedis();   //创建Redis连接
        jedis.mset("k1","v1","k2","v2");  //此处同时设置了两对key-value

        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);   //输出此集合
        jedis.close();

    }

    /**
     * Jedis操作列表（list）
     * 注：list单键多值
     * example:rpush从右边添加
     */
    @Test
    public void testList(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.rpush("key1","lucy","marry","jack");  //从右添加
        List<String> values = jedis.lrange("key1", 0, -1);//0到-1表示把所有值都取出来。返回list集合
        System.out.println(values);
        jedis.close();
    }

    /**
     * 注：set集合中元素不可以重复
     *
     * example:set集合的添加数据（sadd），查看数据（smembers），删除数据（srem）
     */
    @Test
    public void testSet(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.sadd("class","jack","Tom","Green");  //添加

        jedis.srem("class","Green");   //删除

        Set<String> aClass = jedis.smembers("class");  //查询

        System.out.println(aClass);

        jedis.close();
    }


    /**
     * Jedis操作哈希（hash）
     * example:hash的添加（hset）和查询（hget）
     */
    @Test
    public void testHash(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.hset("users","age","20");  //添加
        String hget = jedis.hget("users", "age"); //查询
        System.out.println(hget);

        jedis.close();
    }


    /**
     * Jedis操作zset
     * example:zset的添加元素操作（zadd）
     */
    @Test
    public void testZset(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.zadd("language",100d,"java");  //此处的第二个参数100为double类型。java的score为100.0

        List<String> language = jedis.zrange("language", 0, -1);
        System.out.println(language);

        jedis.close();

    }



}
