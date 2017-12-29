package cn.et.����.xml;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import cn.et.����.Student;

public class Tests {
	//��ȡһ��session��  session������������ ָ��һ��sql����Ψһ�ı�ʶ��
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/����/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	public static SqlSessionFactory getSqlSessionFactory() throws IOException{
		String resource = "cn/et/����/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		return new SqlSessionFactoryBuilder().build(is);//����һ��������
	}
	//�Զ���logs
	Logger logger = Logger.getLogger(Tests.class);
	/**
	 * һ������: ͬһ��session�������ͬһ�����ݵĲ�ѯ�����Ļ���, ��Ƕ�Ļ���, Ĭ��ʹ��, ��������ġ�
	 * 			��һ�β�ѯʱ�������ݿ⣬ ��ȡ����֮��ͨ��session���õ�һ�������С�
	 * 			�ڶ��β�ѯʱͨ��sessionһ�������̴��ж��Ƿ������ͬ����������ֵ�� ������� ֱ�ӷ������� �����ѯ���ݿ⡣
	 * @throws IOException
	 */
	@Test
	public void level1CacheTest() throws IOException{
		SqlSession session = getSession();
		StudentMapper sm = session.getMapper(StudentMapper.class);//��ȡʵ����
		Student stu = sm.queryStuById(1701001);
		Student stu1 = sm.queryStuById(1701001);
		System.out.println(stu == stu1);
	}
	/**
	 * ��������: Ĭ�Ͽ���, �ɲ��, ��ͨ��Cache�Զ��塣 ͬһ��SqlSessionFactory�е�session���Թ������ݡ�
	 * 			һ�����ݱ�session��һ�η��ʺ�,��Ҫ�ر�session֮��ŻὫ����д�뻺���С�
	 * @throws IOException
	 */
	@Test
	public void level2CacheTest() throws IOException{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session1 = ssf.openSession();
		SqlSession session2 = ssf.openSession();
		StudentMapper sm1 = session1.getMapper(StudentMapper.class);//��ȡʵ����
		StudentMapper sm2 = session2.getMapper(StudentMapper.class);//��ȡʵ����
		
		Student stu1 = sm1.queryStuById(1701002);
		session1.close();
		Student stu2 = sm2.queryStuById(1701002);
		System.out.println(stu1.getStuName() + "=====" + stu2.getStuName());
	}
}
