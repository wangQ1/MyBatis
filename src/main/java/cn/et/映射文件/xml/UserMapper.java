package cn.et.映射文件.xml;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.et.User;

public interface UserMapper {
	/**
	 * 根据用户名与住址查询住在同一地区并且姓名相同的用户信息
	 * @param userName
 	 * @param adress
	 * @return
	 */
	public List<User> queryUser(String userName, String adress);//与UserMapper.xml中的sql声明绑定
	/**
	 * 添加新用户	
	 * @param userName  用户名
	 * @param adress  居住地址
	 */
	public void saveUser(String userName, String adress);
	/**
	 * 根据用户id删除用户
	 * @param userId
	 */
	public void deleteUser(String userId);
	/**
	 * 根据用户id更改用户信息
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
