package cn.et.��ϵӳ��.annotation;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.et.��ϵӳ��.Student;

public interface StudentMapper {
	/**
	 * ��ѯ����ѧ��
	 * @return
	 */
	public List<Student> queryAllStudent();
	/**
	 * ͨ��id��ѯѧ��
	 * @param gid
	 * @return
	 */
	@Results({
		@Result(property="cla",column="claId",one=@One(select="cn.et.��ϵӳ��.annotation.ClassMapper.queryClaById"))
	})//���һ��ϵӳ��
	@Select("select * from student where stuId = #{0}")
	public Student queryStuById(int stuId);
	
	@Select("select * from Student where claId = #{0}")
	public List<Student> queryStuByClaId(int claId);
}
