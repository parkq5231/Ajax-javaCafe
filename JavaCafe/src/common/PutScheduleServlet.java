package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insertSchedule")
public class PutScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PutScheduleServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// response가 화면에 보이는거고 request가 DB에 넘겨주는거
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");	//오른쪽에 있는게 parameter이름(servlet혹은 html참조)
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String url = request.getParameter("url");
		// parameter로 넘겨줄거임
		Schedule sch = new Schedule();//인스턴스변수에 담아 넣어주는게 더 편할듯
		sch.setTitle(title);
		sch.setStartDate(start);
		sch.setEndDate(end);
		sch.setUrl(url);
		//밑에 dao의 인스턴스 할당 후 insertschedule()메소드에  sch 값을 매개변수로 넣어준다
		EmpDAO dao = new EmpDAO();
		dao.insertschedule(sch);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
