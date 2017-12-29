package cn.et.映射文件.annotation;

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
	 * 根据用户名与住址查询住在同一地区并且姓名相同的用户信息
	 * @param userName
 	 * @param adress
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Select("select * from USERMANAGE t")
	public List<Map> queryUser();//注解的sql语句绑定
	/**
	 * 添加新用户	
	 * @param userName  用户名
	 * @param adress  居住地址
	 */
	//获取参数的3种方式 
	@Insert("insert into USERMANAGE values(USER_SEQ.NEXTVAL, #{0}, #{1})")//!--第一种：通过索引获取参数的参数，索引从0开始
	public void saveUser(String userName, String adress);
	/**
	 * 根据用户id删除用户
	 * @param userId
	 */
	@Delete("delete from USERMANAGE where userId=#{param1}")//第二种：通过默认的键获取参数，param1 ，从1开始 
	public void deleteUser(String userId);
	/**
	 * 根据用户id更改用户信息
	 * @param userId
	 * @param userName
	 * @param adress
	 */
	@Update("update USERMANAGE set userName=#{userName}, adress=#{adress} where userId=#{userId}")//第三种：自定义参数名
	public void updateUser(@Param("userId") String userId, @Param("userName") String userName, @Param("adress") String adress);
	/**
	 * 调用存储过程
	 * @param map  参数集合
	 */
	@SuppressWarnings("rawtypes")
	@Select("call prg_add(#{p1},#{p2},#{result})")//未成功， 没有返回值
	public int call_prg_add(Map map);
	/**
	 * 调用存储函数
	 * @param map  参数集合
	 */
	@SuppressWarnings("rawtypes")
	@Select("call fun_add(#{p1},#{p2},#{result})")//未成功， 没有返回值
	public void call_fun_add(Map map);
}
