package cn.et.��ϵӳ��.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.et.��ϵӳ��.Classes;
import cn.et.��ϵӳ��.Student;

public class Tests {
	//��ȡһ��session��  session������������ ָ��һ��sql����Ψһ�ı�ʶ��
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/��ϵӳ��/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	//�Զ���logs
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void queryClass() throws IOException{
		SqlSession session = getSession();
		ClassMapper cm = session.getMapper(ClassMapper.class);//��ȡʵ����
		List<Classes> list = cm.queryAllClass();
		System.out.println(list.size());
	}
	@Test
	public void queryStudent() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		List<Student> list = sm.queryAllStudent();
		System.out.println(list.size());
	}
	@Test
	public void queryStudentById() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = sm.queryStuById(1701001);//��ѯѧ��1701001��ѧ�����ڵİ༶�İ༶��
		System.out.println(stu.getStuName());
		//System.out.println(stu.getCla().getClaName());
	}
	@Test
	public void queryClassById() throws IOException{
		SqlSession session = getSession();
		ClassMapper cm = session.getMapper(ClassMapper.class);//��ȡʵ����
		Classes cla = cm.queryClassById(1);//��ȡ1�Ű������ѧ����Ϣ
		List<Student> stuList = cla.getStuList();
		System.out.println(cla.getClaName());
		for (int i = 0; i < stuList.size(); i++) {
			System.out.println(stuList.get(i).getStuName());
		}
	}
}
