package cn.sherven.doraemon.admin.db;

import java.util.ArrayList;
import java.util.List;

import cn.sherven.doraemon.hibernate.MessageTableH;
import cn.sherven.doraemon.hibernateController.MessageTable_HC;

public class MessageTable_DB {

	private static MessageTable_HC modelHC = new MessageTable_HC();

	public static boolean Insert(DBTxtFileTool dbt) {
		List<MessageTableH> list;
		boolean iswhile = true;
		String line;
		while (iswhile) {
			list = new ArrayList<MessageTableH>();
			for (int i = 0; i < 100; i++) {// 每次处理100条记录
				line = dbt.getNextLine();
				MessageTableH model = InitModel(line);
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

	private static boolean saveModel(MessageTableH model) {
		modelHC.insert(model);
		return true;
	}

	private static boolean saveModel(List<MessageTableH> model) {
		modelHC.insert(model);
		return true;
	}

	private static MessageTableH InitModel(String line) {
		String[] arr;
		if (line != null) {
			arr = line.split("\t");
			if (arr.length != 3) {
				// 2是数据表的列数目
				return null;
			}
		} else {
			return null;
		}

		MessageTableH model = new MessageTableH();
		model.setId_(arr[0]);
		model.setType(arr[1]);
		model.setJson(arr[2]);
		return model;
	}
}