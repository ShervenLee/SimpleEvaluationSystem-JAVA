package cn.sherven.doraemon.test;

import java.util.List;


import com.google.gson.Gson;

import cn.sherven.doraemon.admin.Model.MessageTableModel;
import cn.sherven.doraemon.admin.jsonclass.JsonCommentTable;
import cn.sherven.doraemon.hibernate.MessageTableH;
import cn.sherven.doraemon.hibernateController.MessageTable_HC;

public class Test {

	public static void main(String[] args) {
		try {
			System.out.println(MessageTableModel.del("3"));
		} finally {
			System.exit(0);
		}
	}

}
