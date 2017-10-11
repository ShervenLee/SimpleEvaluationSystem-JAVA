package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import cn.sherven.doraemon.hibernate.TeacherGroupH;
import cn.sherven.doraemon.hibernate.UserStudentH;
import cn.sherven.doraemon.hibernateController.TeacherGroupHC;
import cn.sherven.doraemon.hibernateController.UserStudent_HC;

public class TeacherGroup_DB {
	private static TeacherGroupHC modelHC = new TeacherGroupHC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<TeacherGroupH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<TeacherGroupH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				TeacherGroupH model = InitModel(line);
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

	private static boolean saveModel(TeacherGroupH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<TeacherGroupH> model) {
		modelHC.insert(model);
		return true;
	}

	private static TeacherGroupH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 2) {
				// 2是数据表的列数目
				return null;
			}
		} else {
			return null;
		}

		TeacherGroupH model = new TeacherGroupH();
		model.setId_(arr[0]);
		model.setName(arr[1]);
		return model;
	}
}