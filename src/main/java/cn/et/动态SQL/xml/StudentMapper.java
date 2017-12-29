package cn.et.��̬SQL.xml;

import java.util.List;

import cn.et.��̬SQL.Student;

public interface StudentMapper {
	/**
	 * �޸�ѧ����Ϣ
	 * @param stu
	 */
	public void updateStudent(Student stu);
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
	 * @return
	 */
	public List<Student> queryStuByMessage2(Student stu);
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
	 * @return
	 */
	public List<Student> queryStuByMessage1(Student stu);
	/**
	 * ͨ��ѧ����Ϣ��ѯѧ��
	 * @param stu ѧ������
	 * @return
	 */
	public List<Student> queryStuByMessage(Student stu);
	/**
	 * ��ѯ���Id��ѧ��
	 * @param list 
	 * @param stu
	 * @return
	 */
	public List<Student> queryStuByMultiId(List<Integer> list);
}
