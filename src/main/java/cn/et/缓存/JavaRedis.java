package cn.et.缓存;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class JavaRedis {

	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.set("myName", "系ii基金");
		System.out.println(jedis.get("myName"));
		Map map = new HashMap();
		map.put("name", "wang");
		map.put("sex", "boy");
		jedis.hmset("hashMap", map);
	}
	/**
	 * 序列化
	 *   写入数据到redis时
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException{
		ByteOutputStream bos = new ByteOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		return bos.getBytes();
	}
	/**
	 * 反序列化
	 * 	 从redis取数据
	 * @param b
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object byteArrayToObject(byte[] b) throws IOException, ClassNotFoundException{
		ByteInputStream bis = new ByteInputStream(b, b.length);
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}
}
