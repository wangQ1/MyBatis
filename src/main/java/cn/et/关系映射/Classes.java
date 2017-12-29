package cn.et.πÿœµ”≥…‰;

import java.util.ArrayList;
import java.util.List;

public class Classes {
	private int claId;
	private String claName;
	private List<Student> stuList = new ArrayList<Student>();
	public int getClaId() {
		return claId;
	}
	public void setClaId(int claId) {
		this.claId = claId;
	}
	public String getClaName() {
		return claName;
	}
	public void setClaName(String claName) {
		this.claName = claName;
	}
	public List<Student> getStuList() {
		return stuList;
	}
	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}
}
