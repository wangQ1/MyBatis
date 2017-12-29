package cn.et.缓存.xml;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import cn.et.缓存.Student;

public class Tests {
	//获取一个session，  session真正操作的是 指向一条sql语句的唯一的标识符
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/缓存/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//加载获取一个文件流
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//构建一个工厂类
		SqlSession session = ssf.openSession(); //通过工厂类产生一个sqlSession
		return session;
	}
	public static SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "cn/et/缓存/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//加载获取一个文件流
		return new SqlSessionFactoryBuilder().build(is);//构建一个工厂类
	}
	//自定义logs
	Logger logger = Logger.getLogger(Tests.class);
	/**
	 * 一级缓存: 同一个session对象针对同一份数据的查询产生的缓存, 内嵌的缓存, 默认使用, 不允许更改。
	 * 			第一次查询时调用数据库， 获取数据之后通过session设置到一级缓存中。
	 * 			第二次查询时通过session一级缓存盘存判断是否存在相同主键的数据值， 如果存在 直接返回引用 否则查询数据库。
	 * @throws IOException
	 */
	@Test
	public void level1CacheTest() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//获取实现类
		Student stu = sm.queryStuById(1701001);
		Student stu1 = sm.queryStuById(1701001);
		System.out.println(stu == stu1);
	}
	/**
	 * 二级缓存: 默认开启, 可插拔, 可通过Cache自定义。 同一个SqlSessionFactory中的session可以共享数据。
	 * 			一个数据被session第一次访问后,需要关闭session之后才会将数据写入缓存中。
	 * @throws IOException
	 */
	@Test
	public void level2CacheTest() throws IOException{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session1 = ssf.openSession();
		SqlSession session2 = ssf.openSession();
		StudentMapper sm1 = session1.getMapper(StudentMapper.class);//获取实现类
		StudentMapper sm2 = session2.getMapper(StudentMapper.class);//获取实现类
		
		Student stu1 = sm1.queryStuById(1701002);
		session1.close();
		Student stu2 = sm2.queryStuById(1701002);
		System.out.println(stu1.getStuName() + "=====" + stu2.getStuName());
	}
}
