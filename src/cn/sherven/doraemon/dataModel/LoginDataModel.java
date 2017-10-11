package cn.sherven.doraemon.dataModel;

public class LoginDataModel {
	// 表示数据包中是否返回有需要的值（每个JSON数据包都应该有这两个参数）-Start
	private String isok = "NO";// [OK|NO]
	private String errinfo;
	// 表示数据包中是否返回有需要的值（每个JSON数据包都应该有这两个参数）-End

	// 具体的值，每个XXXDataModel都自定义-Start
	private String userid;
	private String type;
	private String key;// 返回唯一标识符，传递给服务器是，都应该加上个参数，以此来确是那个用户。id参数也需要，使用id参数推导key，以此来验证用户的合法性
	// 具体的值，每个XXXDataModel都自定义-End

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
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the id to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
