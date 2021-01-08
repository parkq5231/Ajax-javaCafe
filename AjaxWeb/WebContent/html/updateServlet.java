package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateServlet() {
		super();
	}
	// append를 해서 내용을 document에 추가하는거
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String jobId = request.getParameter("jobId");
		String empId = request.getParameter("empId");

		EmployeeVO vo = new EmployeeVO();
		vo.setEmployeeId(Integer.parseInt(empId));
		vo.setFirstName(fName);
		vo.setLastName(lName);
		vo.setEmail(email);
		vo.setJobId(jobId);
		
		EmpDAO dao = new EmpDAO();
		EmployeeVO vo1 = dao.updateEmp(vo);
		String result = "<result>";
		result += "<empId>" + vo1.getEmployeeId() + "</empId>";
		result += "<fName>" + vo1.getFirstName() + "</fName>";
		result += "<lName>" + vo1.getLastName() + "</lName>";
		result += "<email>" + vo1.getEmail() + "</email>";
		result += "<hDate>" + vo1.getHireDate() + "</hDate>";
		result += "<jobId>" + vo1.getJobId() + "</jobId>";
		result += "<salary>" + vo1.getSalary() + "</salary>";
		result += "</result>";
		response.getWriter().append(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
