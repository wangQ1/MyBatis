package cn.et.映射文件.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.et.User;

public class Tests {
	//获取一个session，  session真正操作的是 指向一条sql语句的唯一的标识符
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/映射文件/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//加载获取一个文件流
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//构建一个工厂类
		SqlSession session = ssf.openSession(); //通过工厂类产生一个sqlSession
		return session;
	}
	//自定义log
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void testQueryUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		List<User> list = um.queryUser("牛逼", "普拉思");
		logger.debug(list.get(0).getUserId());
	}
	@Test
	public void testQueryUserByName() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		List<User> list = um.queryUserByUserName("小");
		logger.debug(list);
	}
	@Test
	public void testDeleteUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		um.deleteUser("2");
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
	@Test
	public void testSaveUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		um.saveUser("牛逼", "普拉思");
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
	@Test
	public void testUpdateUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		um.updateUser("3", "嘿嘿", "脸上笑嘻嘻，心里妈卖批.");
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testProcInterface() throws IOException{//存储过程
		SqlSession session =getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		Map map=new HashMap<String, Integer>();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		um.call_prg_add(map);
		session.commit();
		System.out.println(map.get("result"));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testFunInterface() throws IOException{//存储函数
		SqlSession session =getSession();
		UserMapper um = session.getMapper(UserMapper.class);//获取实现类
		Map map=new HashMap<String, Integer>();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		um.call_fun_add(map);
		session.commit();
		System.out.println(map.get("result"));
	}
}
