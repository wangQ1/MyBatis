package cn.et.�������ݿ�;

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
		String resource = "cn/et/�������ݿ�/myBatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);//���ػ�ȡһ���ļ���
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);//����һ��������
		SqlSession session = ssf.openSession(); //ͨ�����������һ��sqlSession
		return session;
	}
	public static void main(String[] args) throws IOException {
	}
	//�Զ���log
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
//		Map map = new HashMap(); 	//��ֵ�����ַ�ʽ�� ����sql����е��ֶ�����ͬ����Զ����
//		map.put("userName", "����");
//		map.put("adress", "�����޺�");
//		map.put("userId", 2);
		User user = new User();
		user.setUserId(1);
		user.setAdress("���Ϻ���");
		user.setUserName("������");
		session.update("a.updateUser", user);
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void saveUser() throws IOException{
		SqlSession session = getSession();
		Map map = new HashMap(); 	//��ֵ�����ַ�ʽ�� ����sql����е��ֶ�����ͬ����Զ����
		map.put("userName", "����");
		map.put("adress", "����ƺɽ");
//		User user = new User();
//		user.setUserId(1);
//		user.setAdress("���Ϻ���");
//		user.setUserName("������");
		session.insert("a.saveUser", map);
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
	@Test
	public void deleteUser() throws IOException{
		SqlSession session = getSession();
		session.delete("a.deleteUser");
		session.commit();   //��־�Զ����ύ��ʽ����Ϊ���Զ��ύ������ʱ�����ֶ��ύ
	}
}
