package cn.et.关系映射.annotation;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.关系映射.Classes;

public interface ClassMapper {
	/**
	 * 查询所有班级
	 * @return
	 */
	public List<Classes> queryAllClass();
	/**
	 * 通过id查询所有的班级
	 * @param gid
	 * @return
	 */
	@Select("select * from Classes where claId = #{0}")
	public Classes queryClaById(int claId);
	
	@Results({
		//当数据库的列名和实体类的属性名 不一致时  建立 列名和属性名的映射关系 
		//@Result(column="claName", property="claName1"),
		@Result(property="stuList", column="claId", javaType=ArrayList.class, many=@Many(select="cn.et.关系映射.annotation.StudentMapper.queryStuByClaId"))
	})//一对多关系映射
	@Select("select * from Classes where claId = #{0}")
	public Classes queryClassById(int claId);
}
