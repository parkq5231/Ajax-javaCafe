package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		testDAO dao = new testDAO();
		List<testVO> list = dao.getAllList();
		String allList = "<result>";

		for (testVO all : list) {
			allList += "<record>";
			allList += "<번호>" + all.getNumber() +"</번호>" +
						"<성>" + all.getfName() +"</성>" +
						"<이름>" + all.getlName() +"</이름>" +
						"<직업>" + all.getJobId() + "</직업>";
			allList += "</record>";
		}
		allList += "</result>";
		response.getWriter().append(allList);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
