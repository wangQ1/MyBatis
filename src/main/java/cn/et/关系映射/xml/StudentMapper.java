package cn.et.��ϵӳ��.xml;

import java.util.List;

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
	public Student queryStuById(int stuId);
	
	public List<Student> queryStuByClaId(int claId);
}
