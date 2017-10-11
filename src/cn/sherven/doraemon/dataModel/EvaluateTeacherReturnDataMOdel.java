package cn.sherven.doraemon.dataModel;

import java.util.Map;

public class EvaluateTeacherReturnDataMOdel {
	private Map<String, String> returnServerJson;
	private String message;
	private String courses_id;
	private String teacher_id;
	private String student_id;

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
	 * @return the courses_id
	 */
	public String getCourses_id() {
		return courses_id;
	}

	/**
	 * @param courses_id
	 *            the courses_id to set
	 */
	public void setCourses_id(String courses_id) {
		this.courses_id = courses_id;
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
	 * @return the returnServer
	 */
	public Map<String, String> getReturnServerJson() {
		return returnServerJson;
	}

	/**
	 * @param returnServer
	 *            the returnServer to set
	 */
	public void setReturnServerJson(Map<String, String> returnServerJson) {
		this.returnServerJson = returnServerJson;
	}
}
