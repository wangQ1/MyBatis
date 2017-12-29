package cn.et.»º´æ;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Student implements Serializable{
	private int stuId = 0;
	private String stuName = null;
	private Integer sex = null;
	private Integer age = null;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
