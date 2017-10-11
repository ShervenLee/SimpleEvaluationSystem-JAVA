package cn.sherven.doraemon.dataModel;

public class IsOKAndErrInfoDataModel {
private String isok;
private String errinfo;

public IsOKAndErrInfoDataModel() {
	// TODO Auto-generated constructor stub
	isok="NO";
	errinfo="null";
}

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
}
