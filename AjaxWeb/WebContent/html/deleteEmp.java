package common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteEmp")
public class deleteEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteEmp() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ex) localhost/AjaxWeb/deleteEmp?empId=210
		String id = request.getParameter("empId");// parameter 이름
		id = id == null ? "0" : id;
		int employeeId = Integer.parseInt(id);

		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId(employeeId);

		EmpDAO dao = new EmpDAO();
		dao.deleteEmp(vo);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
