package cn.et.��̬SQL.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import cn.et.��̬SQL.Student;

public class Tests {
	//��ȡһ��session��  session������������ ָ��һ��sql����Ψһ�ı�ʶ��
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/��̬SQL/annotation/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	//�Զ���logs
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void queryStuByMessage() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = new Student();
		stu.setStuName("��5");
		stu.setSex(0);
		List<Student> stuList = sm.queryStuByMessage(stu);
		//logger.debug(stuList);   ͨ����־����
		for (Student s:stuList) {
			System.out.println(s.getStuId() + "--" + s.getStuName() + "--" + s.getSex() + "--" + s.getAge());
		}
	}
	@Test
	public void queryStuByMessage1() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = new Student();
		stu.setStuName("��5");
		stu.setSex(0);
		List<Student> stuList = sm.queryStuByMessage1(stu);
		//logger.debug(stuList);   ͨ����־����
		for (Student s:stuList) {
			System.out.println(s.getStuId() + "--" + s.getStuName() + "--" + s.getSex() + "--" + s.getAge());
		}
	}
	@Test
	public void queryStuByMessage2() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = new Student();
		stu.setSex(0);
		List<Student> stuList = sm.queryStuByMessage2(stu);
		//logger.debug(stuList);   ͨ����־����
		for (Student s:stuList) {
			System.out.println(s.getStuId() + "--" + s.getStuName() + "--" + s.getSex() + "--" + s.getAge());
		}
	}
	@Test
	public void updateStudent() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = new Student();
		stu.setStuId(1701003);
		stu.setStuName("��5");
		stu.setAge(20);
		sm.updateStudent(stu);
		session.commit();
	}
	@Test
	public void queryStuByMultiId() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		List<Integer> list = new ArrayList<Integer>();
		list.add(1701001);
		list.add(1702001);
		list.add(1703001);
		List<Student> stuList = sm.queryStuByMultiId(list);
		for (Student s:stuList) {
			System.out.println(s.getStuId() + "--" + s.getStuName() + "--" + s.getSex() + "--" + s.getAge());
		}
	}
}
