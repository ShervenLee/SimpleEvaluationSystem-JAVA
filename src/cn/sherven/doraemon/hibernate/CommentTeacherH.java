package cn.sherven.doraemon.hibernate;

public class CommentTeacherH {
	private String id_;
	private String message;
	private String score;
	private String teacher_id_src;
	private String teacher_id_target;

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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the teacher_id_src
	 */
	public String getTeacher_id_src() {
		return teacher_id_src;
	}

	/**
	 * @param teacher_id_src
	 *            the teacher_id_src to set
	 */
	public void setTeacher_id_src(String teacher_id_src) {
		this.teacher_id_src = teacher_id_src;
	}

	/**
	 * @return the teacher_id_target
	 */
	public String getTeacher_id_target() {
		return teacher_id_target;
	}

	/**
	 * @param teacher_id_target
	 *            the teacher_id_target to set
	 */
	public void setTeacher_id_target(String teacher_id_target) {
		this.teacher_id_target = teacher_id_target;
	}
}
