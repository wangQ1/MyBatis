package cn.et.ӳ���ļ�.annotation;

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
import cn.et.ӳ���ļ�.annotation.UserMapper;
public class Tests {
	//��ȡһ��session��  session������������ ָ��һ��sql����Ψһ�ı�ʶ��
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/ӳ���ļ�/annotation/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	//�Զ���log
	Logger logger = Logger.getLogger(Tests.class);
	@SuppressWarnings("rawtypes")
	@Test
	public void testQueryUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		List list = um.queryUser();
		logger.debug(list);
	}
	@Test
	public void testDeleteUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.deleteUser("4");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void testSaveUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.saveUser("�����", "��˫ȫ");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void testUpdateUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.updateUser("6", "����", "�����");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void testSelectKeyAnnotation() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		User user = new User();
		user.setUserName("С��");
		user.setAdress("����ȥ");
		um.saveUsers(user);
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testProcInterface() throws IOException{//�洢����
		SqlSession session =getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
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
	public void testFunInterface() throws IOException{//�洢����
		SqlSession session =getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		Map map=new HashMap<String, Integer>();
		map.put("p1", 1111);
		map.put("p2", 1212);
		map.put("result", 0);
		um.call_fun_add(map);
		session.commit();
		System.out.println(map.get("result"));
	}
}
