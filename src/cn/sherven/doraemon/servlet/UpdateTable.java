package cn.sherven.doraemon.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sherven.doraemon.admin.db.ClassTable_DB;
import cn.sherven.doraemon.admin.db.Course_DB;
import cn.sherven.doraemon.admin.db.DBTxtFileTool;
import cn.sherven.doraemon.admin.db.MessageTable_DB;
import cn.sherven.doraemon.admin.db.TeacherGroup_DB;
import cn.sherven.doraemon.admin.db.User_student_DB;
import cn.sherven.doraemon.admin.db.User_teacher_DB;

/**
 * 获取上传的数据并且处理传输到mysql里面去
 *
 * Servlet implementation class UpdateTable
 */
public class UpdateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpServletRequest request;
	private static HttpServletResponse response;

	private static File tempFile;
	private static File savefile;

	private static String tempFilePath = "C:/Users/ShervenLee/Desktop/upload/tempFileName.txt";
	// private static String saveFilePath =
	// "C:/Users/ShervenLee/Desktop/upload/savefilename.txt";

	private static String uploadPath = "C:/Users/ShervenLee/Desktop/upload";
	private static Integer start = -1;
	private static Integer end = -1;

	private static String savefilename = "savefilename.txt";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get方式不做任何处理
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		inputTempFile();
		inputUploadFile();
		DBTxtFileTool dbTxtFileTool = new DBTxtFileTool(new File("C:/Users/ShervenLee/Desktop/upLoad", savefilename));
		switchDB(dbTxtFileTool);

	}

	/**
	 * 根据具有的文件选择相应的处理程序
	 * 
	 * @param dbTxtFileTool
	 */
	private static void switchDB(DBTxtFileTool dbTxtFileTool) {
		switch (dbTxtFileTool.getTableName()) {
		case "user_student":
			writestat(User_student_DB.Insert(dbTxtFileTool));
			break;
		case "user_teacher":
			writestat(User_teacher_DB.Insert(dbTxtFileTool));
			break;
		case "course":
			writestat(Course_DB.Insert(dbTxtFileTool));
			break;
		case "classtable":
			writestat(ClassTable_DB.Insert(dbTxtFileTool));
			break;
		case "teachergroup":
			writestat(TeacherGroup_DB.Insert(dbTxtFileTool));
			break;
		case "messagetable":
			writestat(MessageTable_DB.Insert(dbTxtFileTool));
			break;
		default:
			writestat(false);
			System.out.println("文件错误");
			break;
		}
	}

	private static void writestat(boolean b) {
		if (b) {
			String stat = "上传成功,三秒后跳转到主页";
			response.setHeader("refresh", "3,url=./admin/index.jsp");
			try {
				response.getOutputStream().write(stat.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String stat = "上传失败,请检查文件正确性！然后重试";
			response.setHeader("refresh", "3,url=upload.jsp");
			try {
				response.getOutputStream().write(stat.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void inputUploadFile() throws IOException {
		// randomFile对象指向临时文件
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		findStartAndEndPosByname(randomFile, "upload");
		randomFile.close();

		savefile = new File(uploadPath + "/" + savefilename);

		FileInputStream fis = new FileInputStream(tempFile);
		InputStreamReader isr = new InputStreamReader(fis, "gbk");
		BufferedReader br = new BufferedReader(isr);

		FileOutputStream fos = new FileOutputStream(savefile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);

		System.out.println(start + ":" + end);

		for (int i = 0; i <= start; i++) {// 读取之前没用的数据，即定位
			br.readLine();
		}

		for (int i = start; i < end; i++) {
			String buf = br.readLine();
			bw.write(buf + "\r\n");
		}
		br.close();
		isr.close();
		fis.close();

		bw.close();
		osw.close();
		fos.close();

		savefile = null;
		tempFile.delete();
	}

	/**
	 * 获取制定名字开始行之前的空白行索引，并定位到改索引位置
	 * 
	 * @param randomFile
	 * @param name
	 * @return
	 * @throws IOException
	 */
	private static void findStartAndEndPosByname(RandomAccessFile randomFile, String name) throws IOException {
		Integer index = -1;
		do {
			String line = randomFile.readLine();
			index++;
			if (line == null) {
				return;
			}
			if (line.indexOf("name=\"" + name + "\"") != -1) {
				while (true) {
					line = randomFile.readLine();
					index++;
					if (line.equals("")) {
						start = index;

						// 获取结束行

						while (true) {
							line = randomFile.readLine();
							if (line == null) {
								return;
							}
							if (line.indexOf("---") != -1) {
								end = --index;
								randomFile.seek(start);
								return;
							}
							index++;
						}
					}
				}
			}
		} while (true);
	}

	private static void inputTempFile() throws IOException {

		// tempfile 对象指向临时文件
		tempFile = new File(tempFilePath);
		// outputfile 文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		// 得客户端提交的所有数据
		InputStream fileSourcel = request.getInputStream();
		// 将得到的客服端数据写入临时文件
		byte b[] = new byte[1000];
		int n;
		while ((n = fileSourcel.read(b)) != -1) {
			outputStream.write(b, 0, n);
		}
		// 关闭输出流和输入流
		outputStream.close();
		fileSourcel.close();

	}
}
