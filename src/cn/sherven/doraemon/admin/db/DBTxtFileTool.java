package cn.sherven.doraemon.admin.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class DBTxtFileTool {
	private File file;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private String tableName;
	private String[] tableItem;
	private String line;

	public DBTxtFileTool(File file) {
		this.file = file;
		init();
	}

	/**
	 * 
	 * @return InputStreamReader
	 */
	private void init() {
		try {
			fis = new FileInputStream(this.file);
			try {
//				this.isr = new InputStreamReader(fis, "gbk");
				this.isr = new InputStreamReader(fis, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			this.br = new BufferedReader(isr);
			try {
				this.tableName = this.br.readLine();
				this.tableItem = this.br.readLine().split("\t");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String[] getTableItem() {
		return tableItem;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void close() {
		try {
			fis.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getNextLine() {
		try {
			line = this.br.readLine();
			if (line != null) {
				return line;
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
