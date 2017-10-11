package cn.sherven.doraemon.hibernate;

public class CourseH {
	private String id_;
	private String name;
	private String teacher_id;
	private String teacher_name;

	/**
	 * @return the id_
	 */
	public String getId_() {
		return id_;
	}

	/**
	 * @param id_
	 *            the id_ to set
	 */
	public void setId_(String id_) {
		this.id_ = id_;
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
	 * @return the teacher_name
	 */
	public String getTeacher_name() {
		return teacher_name;
	}

	/**
	 * @param teacher_name
	 *            the teacher_name to set
	 */
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
}
