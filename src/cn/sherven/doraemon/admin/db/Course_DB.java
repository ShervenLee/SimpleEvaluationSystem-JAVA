package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import cn.sherven.doraemon.hibernate.CourseH;
import cn.sherven.doraemon.hibernateController.CourseHC;

public class Course_DB {
	private static CourseHC modelHC = new CourseHC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<CourseH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<CourseH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				CourseH model = InitModel(line);
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

	private static boolean saveModel(CourseH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<CourseH> model) {
		modelHC.insert(model);
		return true;
	}

	private static CourseH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 4) {
				// 4是数据表的列数目
				return null;
			}
		} else {
			return null;
		}

		CourseH model = new CourseH();
		model.setId_(arr[0]);
		model.setName(arr[1]);
		model.setTeacher_id(arr[2]);
		model.setTeacher_name(arr[3]);
		return model;
	}
}
