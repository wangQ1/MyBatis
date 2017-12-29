package cn.et.动态SQL.xml;

import java.util.List;

import cn.et.动态SQL.Student;

public interface StudentMapper {
	/**
	 * 修改学生信息
	 * @param stu
	 */
	public void updateStudent(Student stu);
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	public List<Student> queryStuByMessage2(Student stu);
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	public List<Student> queryStuByMessage1(Student stu);
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	public List<Student> queryStuByMessage(Student stu);
	/**
	 * 查询多个Id的学生
	 * @param list 
	 * @param stu
	 * @return
	 */
	public List<Student> queryStuByMultiId(List<Integer> list);
}
