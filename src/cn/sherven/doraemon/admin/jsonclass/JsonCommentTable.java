package cn.sherven.doraemon.admin.jsonclass;

import java.util.Map;

/**
 * 
 * @author ShervenLee
 *
 *需要评价的表格项目
 */
public class JsonCommentTable {
String isok,errinfo;
Map<String, String[]>[] list;
/**
 * @return the isok
 */
public String getIsok() {
	return isok;
}
/**
 * @param isok the isok to set
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
 * @param errinfo the errinfo to set
 */
public void setErrinfo(String errinfo) {
	this.errinfo = errinfo;
}
/**
 * @return the list
 */
public Map<String, String[]>[] getList() {
	return list;
}
/**
 * @param list the list to set
 */
public void setList(Map<String, String[]>[] list) {
	this.list = list;
}

//{"isok":"OK","errinfo":"NO","list":[{"0":["教学效果","条目1","条目2","条目3"]},{"1":["授课效果","条目1","条目2","条目3"]},{"2":["批改作业","条目1","条目2"]}]}
}
