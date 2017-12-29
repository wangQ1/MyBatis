package cn.et.ӳ���ļ�.xml;

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
	//��ȡһ��session��  session������������ ָ��һ��sql����Ψһ�ı�ʶ��
	public static SqlSession getSession() throws IOException{
		String resource = "cn/et/ӳ���ļ�/xml/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	//�Զ���log
	Logger logger = Logger.getLogger(Tests.class);
	@Test
	public void testQueryUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		List<User> list = um.queryUser("ţ��", "����˼");
		logger.debug(list.get(0).getUserId());
	}
	@Test
	public void testQueryUserByName() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		List<User> list = um.queryUserByUserName("С");
		logger.debug(list);
	}
	@Test
	public void testDeleteUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.deleteUser("2");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void testSaveUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.saveUser("ţ��", "����˼");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void testUpdateUser() throws IOException{
		SqlSession session = getSession();
		UserMapper um = session.getMapper(UserMapper.class);//��ȡʵ����
		um.updateUser("3", "�ٺ�", "����Ц����������������.");
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
