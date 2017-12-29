package cn.et.连接数据库;

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
		String resource = "cn/et/连接数据库/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//加载获取一个文件流
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//构建一个工厂类
		SqlSession session = ssf.openSession(); //通过工厂类产生一个sqlSession
		return session;
	}
	public static void main(String[] args) throws IOException {
	}
	//自定义log
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void queryUser() throws IOException{
		SqlSession session = getSession();
		@SuppressWarnings("rawtypes")
		List list = session.selectList("a.selectUser");
		logger.debug(list);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void queryUserById() throws IOException{
		SqlSession session = getSession();
		Map map = new HashMap();
		map.put("userId", 1);
		Map maps = session.selectOne("a.selectUserById", map);
		logger.debug(maps);
	}
	@Test
	public void updateUser() throws IOException{
		SqlSession session = getSession();
//		Map map = new HashMap(); 	//传值的两种方式， 键和sql语句中的字段名相同便会自动填充
//		map.put("userName", "李四");
//		map.put("adress", "深圳罗湖");
//		map.put("userId", 2);
		User user = new User();
		user.setUserId(1);
		user.setAdress("湖南衡阳");
		user.setUserName("王麻子");
		session.update("a.updateUser", user);
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void saveUser() throws IOException{
		SqlSession session = getSession();
		Map map = new HashMap(); 	//传值的两种方式， 键和sql语句中的字段名相同便会自动填充
		map.put("userName", "赵六");
		map.put("adress", "深圳坪山");
//		User user = new User();
//		user.setUserId(1);
//		user.setAdress("湖南衡阳");
//		user.setUserName("王麻子");
		session.insert("a.saveUser", map);
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
	@Test
	public void deleteUser() throws IOException{
		SqlSession session = getSession();
		session.delete("a.deleteUser");
		session.commit();   //日志自动将提交方式设置为不自动提交，调用时必须手动提交
	}
}
