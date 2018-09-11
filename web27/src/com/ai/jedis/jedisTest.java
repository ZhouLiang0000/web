package com.ai.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class jedisTest {
	// 通过java程序访问redis数据库
	@Test
	public void test1() {
		// 1、获得连接对象
		Jedis jedis = new Jedis("192.168.75.129", 6379);
		// 2、获得数据
		String username = jedis.get("username");
//		System.out.println(username);
		// 3、存储
		jedis.set("addr", "北京");
		// 4、取值
		String addr = jedis.get("addr");
//		System.out.println(addr);
	}

	// 通过jedis的pool获得jedis的连接对象
	@Test
	public void test2() {
		// 1、创建一个redis的链接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(30);// 最大闲置个数
		config.setMinIdle(10);// 最小闲置个数
		config.setMaxTotal(50);// 最大连接数

		// 2、创建一个redis的链接池
		JedisPool pool = new JedisPool(config, "192.168.75.129", 6379);
		Jedis jedis = pool.getResource();
		String addr = jedis.get("addr");
		System.out.println(addr);
		// 3、关闭资源
		jedis.close();
		pool.close();
	}

}
