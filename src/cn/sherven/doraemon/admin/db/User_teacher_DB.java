package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import cn.sherven.doraemon.hibernate.UserTeacherH;
import cn.sherven.doraemon.hibernateController.UserTeacher_HC;

public class User_teacher_DB {
	private static UserTeacher_HC modelHC = new UserTeacher_HC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<UserTeacherH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<UserTeacherH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				UserTeacherH model = InitModel(line);
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

	private static boolean saveModel(UserTeacherH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<UserTeacherH> model) {
		modelHC.insert(model);
		return true;
	}

	private static UserTeacherH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 6) {
				// 6是数据表的列数目
				return null;
			}
		} else {
			return null;
		}
		UserTeacherH model = new UserTeacherH();
		model.setTeacher_id(arr[0]);
		model.setName(arr[1]);
		model.setPwd(arr[2]);
		model.setTel(arr[3]);
		model.setType(arr[4]);
		model.setClass_id_list(arr[5]);
		return model;
	}
}
