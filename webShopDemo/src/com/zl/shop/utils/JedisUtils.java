package com.zl.shop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	private static JedisPool pool = null;
	private static Jedis jedis = null;

	public static void main(String[] args) {
		System.out.println(getJedis().get("addr"));
	}

	public static Jedis getJedis() {
		jedis = pool.getResource();
		return jedis;
	}

	static {
		InputStream inputStream = JedisUtils.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));// 最大闲置个数
		config.setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));// 最小闲置个数
		config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));// 最大连接数
		pool = new JedisPool(config, properties.getProperty("redis.url"),
				Integer.parseInt(properties.getProperty("redis.port")));
	}

	public static void jedisClose() {
		if (jedis != null) {
			jedis.close();
		}
	}

	public static void poolClose() {
		if (pool != null) {
			pool.close();
		}
	}
}
