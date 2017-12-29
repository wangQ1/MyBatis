package cn.et.ӳ���ļ�.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import cn.et.User;

public interface UserMapper {
	@SelectKey(before=true, keyProperty="userId", resultType=int.class, statement="select USER_SEQ.NEXTVAL from dual")
	@Insert("insert into USERMANAGE values(#{userId}, #{userName}, #{adress})")
	public void saveUsers(User user);
	/**
	 * �����û�����סַ��ѯס��ͬһ��������������ͬ���û���Ϣ
	 * @param userName
 	 * @param adress
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Select("select * from USERMANAGE t")
	public List<Map> queryUser();//ע���sql����
	/**
	 * ������û�	
	 * @param userName  �û���
	 * @param adress  ��ס��ַ
	 */
	//��ȡ������3�ַ�ʽ 
	@Insert("insert into USERMANAGE values(USER_SEQ.NEXTVAL, #{0}, #{1})")//!--��һ�֣�ͨ��������ȡ�����Ĳ�����������0��ʼ
	public void saveUser(String userName, String adress);
	/**
	 * �����û�idɾ���û�
	 * @param userId
	 */
	@Delete("delete from USERMANAGE where userId=#{param1}")//�ڶ��֣�ͨ��Ĭ�ϵļ���ȡ������param1 ����1��ʼ 
	public void deleteUser(String userId);
	/**
	 * �����û�id�����û���Ϣ
	 * @param userId
	 * @param userName
	 * @param adress
	 */
	@Update("update USERMANAGE set userName=#{userName}, adress=#{adress} where userId=#{userId}")//�����֣��Զ��������
	public void updateUser(@Param("userId") String userId, @Param("userName") String userName, @Param("adress") String adress);
	/**
	 * ���ô洢����
	 * @param map  ��������
	 */
	@SuppressWarnings("rawtypes")
	@Select("call prg_add(#{p1},#{p2},#{result})")//δ�ɹ��� û�з���ֵ
	public int call_prg_add(Map map);
	/**
	 * ���ô洢����
	 * @param map  ��������
	 */
	@SuppressWarnings("rawtypes")
	@Select("call fun_add(#{p1},#{p2},#{result})")//δ�ɹ��� û�з���ֵ
	public void call_fun_add(Map map);
}
