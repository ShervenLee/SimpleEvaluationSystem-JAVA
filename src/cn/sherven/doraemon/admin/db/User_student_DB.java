package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import org.geolatte.geom.crs.LinearCoordinateReferenceSystem;

import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

/**
 * Servlet implementation class StudentDB
 */
public class User_student_DB {
	private static UserStudent_HC modelHC = new UserStudent_HC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<UserStudentH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<UserStudentH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				UserStudentH model = InitModel(line);
				if (model != null) {
					list.add(model);
				} else {
					iswhile = false;
					break;
				}
			}
			saveModel(list);
		}
		return true;

	}

	private static boolean saveModel(UserStudentH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<UserStudentH> model) {
		modelHC.insert(model);
		return true;
	}

	private static UserStudentH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 9) {
				// 9是数据表的列数目
				return null;
			}
		} else {
			return null;
		}

		UserStudentH model = new UserStudentH();
		model.setStudent_id(arr[0]);
		model.setName(arr[1]);
		model.setPwd(arr[2]);
		model.setTel(arr[3]);
		model.setType(arr[4]);
		model.setDepartment(arr[5]);
		model.setMajor(arr[6]);
		model.setClass_id(arr[7]);
		model.setClass_name(arr[8]);
		return model;
	}
}
