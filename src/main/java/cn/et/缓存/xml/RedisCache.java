package cn.et.����.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import cn.et.����.JavaRedis;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	private Jedis jedis = new Jedis("localhost", 6379);
	/**
	 * �����id, ���������ռ�
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
	 * mybatis��ȡ����ʱ����ȡ�������Զ����ø÷����ŵ�������
	 */
	@Override
	public void putObject(Object key, Object value) {
		//д��redis
		try {
			jedis.set(JavaRedis.objectToByteArray(key), JavaRedis.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * mybatis��ȡ����ǰ�Զ����ø÷�������⻺�����Ƿ��Ѿ�����
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
	 * ����mybatis�Ļ������ɾ��ĳЩ���ڵ�����
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
