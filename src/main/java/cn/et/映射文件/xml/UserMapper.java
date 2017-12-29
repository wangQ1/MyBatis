package cn.et.ӳ���ļ�.xml;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.et.User;

public interface UserMapper {
	/**
	 * �����û�����סַ��ѯס��ͬһ��������������ͬ���û���Ϣ
	 * @param userName
 	 * @param adress
	 * @return
	 */
	public List<User> queryUser(String userName, String adress);//��UserMapper.xml�е�sql������
	/**
	 * ������û�	
	 * @param userName  �û���
	 * @param adress  ��ס��ַ
	 */
	public void saveUser(String userName, String adress);
	/**
	 * �����û�idɾ���û�
	 * @param userId
	 */
	public void deleteUser(String userId);
	/**
	 * �����û�id�����û���Ϣ
	 * @param userId
	 * @param userName
	 * @param adress
	 */
	public void updateUser(String userId, String userName, @Param("adress")String adress);
	
	@SuppressWarnings("rawtypes")
	public void call_prg_add(Map map);
	@SuppressWarnings("rawtypes")
	public void call_fun_add(Map map);
	
	public List<User> queryUserByUserName(String userName);
}
