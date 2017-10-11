package cn.sherven.doraemon.hibernate;

public class UserTeacherH {
	private String teacher_id;
	private String name;
	private String pwd;
	private String tel;
	private String type;
	private String class_id_list;
	private String teacher_group;


	/**
	 * @return the teacher_group
	 */
	public String getTeacher_group() {
		return teacher_group;
	}

	/**
	 * @param teacher_group the teacher_group to set
	 */
	public void setTeacher_group(String teacher_group) {
		this.teacher_group = teacher_group;
	}

	/**
	 * @return the class_id_list
	 */
	public String getClass_id_list() {
		return class_id_list;
	}

	/**
	 * @param class_id_list
	 *            the class_id_list to set
	 */
	public void setClass_id_list(String class_id_list) {
		this.class_id_list = class_id_list;
	}

	/**
	 * @return the teacher_id
	 */
	public String getTeacher_id() {
		return teacher_id;
	}

	/**
	 * @param teacher_id
	 *            the teacher_id to set
	 */
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd
	 *            the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
}
