package cn.sherven.doraemon.hibernate;

public class MessageTableH {
	private String id_;
	private String type;
	private String json;
	/**
	 * @return the id_
	 */
	public String getId_() {
		return id_;
	}
	/**
	 * @param id_ the id_ to set
	 */
	public void setId_(String id_) {
		this.id_ = id_;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the json
	 */
	public String getJson() {
		return json;
	}
	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}
}
