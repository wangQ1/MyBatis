package cn.et.��ϵӳ��.xml;

import java.util.List;

import cn.et.��ϵӳ��.Classes;

public interface ClassMapper {
	/**
	 * ��ѯ���а༶
	 * @return
	 */
	public List<Classes> queryAllClass();
	/**
	 * ͨ��id��ѯ���еİ༶
	 * @param gid
	 * @return
	 */
	public Classes queryClaById(int claId);
	
	
	public Classes queryClassById(int claId);
}
