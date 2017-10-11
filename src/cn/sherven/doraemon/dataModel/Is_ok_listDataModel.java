package cn.sherven.doraemon.dataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Is_ok_listDataModel {
	private String isok = "OK";
	private String errinfo = "null";
	private List<Map<String, String>> list;

	public void addMap(Map<String, String> map) {
		if (list == null) {
			list = new ArrayList<Map<String, String>>();
		}
		list.add(map);

	}

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
