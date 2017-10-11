package cn.sherven.doraemon.dataModel;

import java.util.List;
import java.util.Map;

/**
 * 教师所有所教班级列表
 * 
 * @author ShervenLee
 *
 */
public class TeacherClassEvaluateListDataModel {

	String isok = "OK";
	String errinfo = "NO";
	List<Map<String, String>> list;

	/**
	 * isok[OK|NO] errinfo 
	 * map->class_name、 course_name、sourse_id、class_id
	 */
	/**
	 * @return the isok
	 */
	public String getIsok() {
		return isok;
	}

	/**
	 * @param isok
	 *            the isok to set
	 */
	public void setIsok(String isok) {
		this.isok = isok;
	}

	/**
	 * @return the errinfo
	 */
	public String getErrinfo() {
		return errinfo;
	}

	/**
	 * @param errinfo
	 *            the errinfo to set
	 */
	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}

	/**
	 * @return the list
	 */
	public List<Map<String, String>> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
}
