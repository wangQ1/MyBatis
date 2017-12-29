package cn.et.关系映射.annotation;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.关系映射.Student;

public interface StudentMapper {
	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> queryAllStudent();
	/**
	 * 通过id查询学生
	 * @param gid
	 * @return
	 */
	@Results({
		@Result(property="cla",column="claId",one=@One(select="cn.et.关系映射.annotation.ClassMapper.queryClaById"))
	})//多对一关系映射
	@Select("select * from student where stuId = #{0}")
	public Student queryStuById(int stuId);
	
	@Select("select * from Student where claId = #{0}")
	public List<Student> queryStuByClaId(int claId);
}
