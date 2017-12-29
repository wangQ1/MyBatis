package cn.et.动态SQL.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import cn.et.动态SQL.Student;

public interface StudentMapper {
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	@Select("<script>select * from Student where 1=1" +
			"<if test=\"stuName != null\">" +
				"and stuName like '%${stuName}%'" +
			"</if>" +
			"<if test=\"sex != null\">" +
				"and sex = #{sex}" +
			"</if></script>")
	public List<Student> queryStuByMessage2(Student stu);
	
	static class Stu{
		/**
		 * 提供sql语句的方法
		 * @param map
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		public String queryStuByMessage1(Map map){
			Student stu = (Student) map.get("stu");
			String sql = "select * from Student where 1=1";
			if(stu.getStuName() != null && !stu.getStuName().equals("")){
				sql+=" and stuName like #{stu.stuName}";//#{}直接获取参数
			}
			if(stu.getSex() != null && !stu.getSex().equals("")){
				sql+=" and sex = #{stu.sex}";
			}
			return sql;
		}
		@SuppressWarnings("rawtypes")
		public String queryStuByMessage(Map map){
			Student stu = (Student) map.get("stu");
			SQL sql = new SQL();
			sql.SELECT("*").FROM("Student");//"select * from Student"
			if(stu.getStuName() != null && !stu.getStuName().equals("")){
				sql.WHERE(" stuName like #{stu.stuName}");
				//sql+=" and stuName like #{stu.stuName}";//#{}直接获取参数
			}
			if(stu.getSex() != null && !stu.getSex().equals("")){
				sql.AND().WHERE(" sex = #{stu.sex}");
				//sql+=" and sex = #{stu.sex}";
			}
			return sql.toString();
		}
	}
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	@SelectProvider(type=Stu.class, method="queryStuByMessage1")//引用一个方法，方法返回一个查询的sql语句
	public List<Student> queryStuByMessage1(@Param("stu") Student stu);//取别名， 该参数会直接以map键值对的形式传递到引用的方法中
	/**
	 * 通过学生信息查询学生
	 * @param stu 学生对象
	 * @return
	 */
	@SelectProvider(type=Stu.class, method="queryStuByMessage")//引用一个方法，方法返回一个查询的sql语句
	public List<Student> queryStuByMessage(@Param("stu") Student stu);//取别名， 该参数会直接以map键值对的形式传递到引用的方法中
	/**
	 * 修改学生信息
	 * @param stu
	 */
	public void updateStudent(Student stu);
	/**
	 * 查询多个Id的学生
	 * @param list 
	 * @param stu
	 * @return
	 */
	public List<Student> queryStuByMultiId(List<Integer> list);
}
