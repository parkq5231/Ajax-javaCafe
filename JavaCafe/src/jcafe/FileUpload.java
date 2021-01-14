package jcafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUpload")
@MultipartConfig(location="c:/tmp",maxFileSize=1024000L,maxRequestSize=-1,fileSizeThreshold=1024)	//파일 최대 사이즈 지정, C에tmp파일생성
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUpload() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("utf-8");//request는 값을 넘길 때
		response.setCharacterEncoding("utf-8");//response는 값을 받을 때
		response.setContentType("text/html; charset=UTF-8");
		
		Collection<Part> parts = request.getParts();
		String filename = "";
		String filepath = request.getServletContext().getRealPath("/images");//파일올리는 경로가 images라는 의미
		for(Part part : parts) {
			filename = part.getSubmittedFileName();
			part.write(filepath+"/"+part.getSubmittedFileName());
		}
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("opener.frm.image.value='"+filename+"';");
		out.println("window.close();");
		out.println("</script>");
		
		
		
	}//end of Post
}//end of class
