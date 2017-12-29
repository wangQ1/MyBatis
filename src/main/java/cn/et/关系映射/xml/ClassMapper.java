package cn.et.关系映射.xml;

import java.util.List;

import cn.et.关系映射.Classes;

public interface ClassMapper {
	/**
	 * 查询所有班级
	 * @return
	 */
	public List<Classes> queryAllClass();
	/**
	 * 通过id查询所有的班级
	 * @param gid
	 * @return
	 */
	public Classes queryClaById(int claId);
	
	
	public Classes queryClassById(int claId);
}
