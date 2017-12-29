package cn.et.关系映射.xml;

import java.util.List;

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
	public Student queryStuById(int stuId);
	
	public List<Student> queryStuByClaId(int claId);
}
