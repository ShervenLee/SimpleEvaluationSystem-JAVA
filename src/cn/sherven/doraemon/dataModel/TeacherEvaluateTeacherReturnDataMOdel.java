package cn.sherven.doraemon.dataModel;

import java.util.Map;

public class TeacherEvaluateTeacherReturnDataMOdel {
	private Map<String, String> returnServerJson;
	private String message;
	private String teacher_id_target;
	/**
	 * @return the returnServerJson
	 */
	public Map<String, String> getReturnServerJson() {
		return returnServerJson;
	}
	/**
	 * @param returnServerJson the returnServerJson to set
	 */
	public void setReturnServerJson(Map<String, String> returnServerJson) {
		this.returnServerJson = returnServerJson;
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
	 * @return the teacher_id_target
	 */
	public String getTeacher_id_target() {
		return teacher_id_target;
	}
	/**
	 * @param teacher_id_target the teacher_id_target to set
	 */
	public void setTeacher_id_target(String teacher_id_target) {
		this.teacher_id_target = teacher_id_target;
	}
}
