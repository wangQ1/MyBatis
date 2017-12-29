package cn.et.关系映射.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.et.关系映射.Classes;
import cn.et.关系映射.Student;

public class Tests {
	//获取一个session，  session真正操作的是 指向一条sql语句的唯一的标识符
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/关系映射/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//加载获取一个文件流
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//构建一个工厂类
		SqlSession session = ssf.openSession(); //通过工厂类产生一个sqlSession
		return session;
	}
	//自定义logs
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void queryClass() throws IOException{
		SqlSession session = getSession();
		ClassMapper cm = session.getMapper(ClassMapper.class);//获取实现类
		List<Classes> list = cm.queryAllClass();
		System.out.println(list.size());
	}
	@Test
	public void queryStudent() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//获取实现类
		List<Student> list = sm.queryAllStudent();
		System.out.println(list.size());
	}
	@Test
	public void queryStudentById() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//获取实现类
		Student stu = sm.queryStuById(1701001);//查询学号1701001的学生所在的班级的班级名
		System.out.println(stu.getStuName());
		//System.out.println(stu.getCla().getClaName());
	}
	@Test
	public void queryClassById() throws IOException{
		SqlSession session = getSession();
		ClassMapper cm = session.getMapper(ClassMapper.class);//获取实现类
		Classes cla = cm.queryClassById(1);//获取1号班的所有学生信息
		List<Student> stuList = cla.getStuList();
		System.out.println(cla.getClaName());
		for (int i = 0; i < stuList.size(); i++) {
			System.out.println(stuList.get(i).getStuName());
		}
	}
}
