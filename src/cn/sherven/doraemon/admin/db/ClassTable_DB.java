package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import cn.sherven.doraemon.hibernate.ClassTableH;
import cn.sherven.doraemon.hibernateController.ClassTableHC;

public class ClassTable_DB {
	private static ClassTableHC modelHC = new ClassTableHC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<ClassTableH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<ClassTableH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				ClassTableH model = InitModel(line);
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

	private static boolean saveModel(ClassTableH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<ClassTableH> model) {
		modelHC.insert(model);
		return true;
	}

	private static ClassTableH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 8) {
				// 8是数据表的列数目
				return null;
			}
		} else {
			return null;
		}

		ClassTableH model = new ClassTableH();
		model.setId_(arr[0]);
		model.setClass_name(arr[1]);
		model.setStudent_id_list(arr[2]);
		model.setShudent_name_list(arr[3]);
		model.setTeacher_id_list(arr[4]);
		model.setTeacher_name_list(arr[5]);
		model.setCourse_id_list(arr[6]);
		model.setCourse_name_list(arr[7]);
		return model;
	}
}
