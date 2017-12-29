package cn.et.��̬SQL.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import cn.et.��̬SQL.Student;

public interface StudentMapper {
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
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
		 * �ṩsql���ķ���
		 * @param map
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		public String queryStuByMessage1(Map map){
			Student stu = (Student) map.get("stu");
			String sql = "select * from Student where 1=1";
			if(stu.getStuName() != null && !stu.getStuName().equals("")){
				sql+=" and stuName like #{stu.stuName}";//#{}ֱ�ӻ�ȡ����
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
				//sql+=" and stuName like #{stu.stuName}";//#{}ֱ�ӻ�ȡ����
			}
			if(stu.getSex() != null && !stu.getSex().equals("")){
				sql.AND().WHERE(" sex = #{stu.sex}");
				//sql+=" and sex = #{stu.sex}";
			}
			return sql.toString();
		}
	}
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
	 * @return
	 */
	@SelectProvider(type=Stu.class, method="queryStuByMessage1")//����һ����������������һ����ѯ��sql���
	public List<Student> queryStuByMessage1(@Param("stu") Student stu);//ȡ������ �ò�����ֱ����map��ֵ�Ե���ʽ���ݵ����õķ�����
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
	 * @return
	 */
	@SelectProvider(type=Stu.class, method="queryStuByMessage")//����һ����������������һ����ѯ��sql���
	public List<Student> queryStuByMessage(@Param("stu") Student stu);//ȡ������ �ò�����ֱ����map��ֵ�Ե���ʽ���ݵ����õķ�����
	/**
	 * �޸�ѧ����Ϣ
	 * @param stu
	 */
	public void updateStudent(Student stu);
	/**
	 * ��ѯ���Id��ѧ��
	 * @param list 
	 * @param stu
	 * @return
	 */
	public List<Student> queryStuByMultiId(List<Integer> list);
}
