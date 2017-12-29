package cn.et.缓存.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import cn.et.缓存.JavaRedis;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	private Jedis jedis = new Jedis("localhost", 6379);
	/**
	 * 缓存的id, 就是命名空间
	 */
	private String cacheId;
	public RedisCache(String cacheId){
		this.cacheId = cacheId;
	}
	@Override
	public String getId() {
		return cacheId;
	}
	/**
	 * mybatis读取数据时将读取的数据自动调用该方法放到缓存中
	 */
	@Override
	public void putObject(Object key, Object value) {
		//写入redis
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * mybatis读取数据前自动调用该方法，检测缓存中是否已经存在
	 */
	@Override
	public Object getObject(Object key) {
		try {
			byte[] b = jedis.get(JavaRedis.objectToByteArray(key));
			if(b == null){
				return null;
			}
			return JavaRedis.byteArrayToObject(b);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据mybatis的缓存策略删除某些过期的数据
	 */
	@Override
	public Object removeObject(Object key) {
		Object obj = getObject(key);
		try {
			jedis.del(JavaRedis.objectToByteArray(obj));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void clear() {
		//jedis.flushDB();
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

}
