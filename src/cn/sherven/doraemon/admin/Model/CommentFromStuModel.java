package cn.sherven.doraemon.admin.Model;

import java.util.List;

import cn.sherven.doraemon.hibernate.CommentH;
import cn.sherven.doraemon.hibernateController.CommentHC;

public class CommentFromStuModel {
	public void get(String id) {
		List<CommentH> queryBy_TeaID = CommentHC.queryBy_TeaID("");
	}
}
