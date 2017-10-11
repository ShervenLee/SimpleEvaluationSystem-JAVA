package cn.sherven.doraemon.hibernate;

public class CommentH {
	private String comment_id;
	private String class_id;
	private String message;
	private String score;
	private String teacher_id;
	private String courses_id;
	private String student_id;
	/**
	 * @return the class_id
	 */
	public String getClass_id() {
		return class_id;
	}
	/**
	 * @param class_id the class_id to set
	 */
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	/**
	 * @return the student_id
	 */
	public String getStudent_id() {
		return student_id;
	}
	/**
	 * @param student_id the student_id to set
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	/**
	 * @return the comment_id
	 */
	public String getComment_id() {
		return comment_id;
	}
	/**
	 * @param comment_id the comment_id to set
	 */
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
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
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}
	/**
	 * @return the teacher_id
	 */
	public String getTeacher_id() {
		return teacher_id;
	}
	/**
	 * @param teacher_id the teacher_id to set
	 */
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	/**
	 * @return the courses_id
	 */
	public String getCourses_id() {
		return courses_id;
	}
	/**
	 * @param courses_id the courses_id to set
	 */
	public void setCourses_id(String courses_id) {
		this.courses_id = courses_id;
	}
}
