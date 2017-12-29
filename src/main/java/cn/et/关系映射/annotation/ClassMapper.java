package cn.et.��ϵӳ��.annotation;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
	@Select("select * from Classes where claId = #{0}")
	public Classes queryClaById(int claId);
	
	@Results({
		//�����ݿ��������ʵ����������� ��һ��ʱ  ���� ��������������ӳ���ϵ 
		//@Result(column="claName", property="claName1"),
		@Result(property="stuList", column="claId", javaType=ArrayList.class, many=@Many(select="cn.et.��ϵӳ��.annotation.StudentMapper.queryStuByClaId"))
	})//һ�Զ��ϵӳ��
	@Select("select * from Classes where claId = #{0}")
	public Classes queryClassById(int claId);
}
