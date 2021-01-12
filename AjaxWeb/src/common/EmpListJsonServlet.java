package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empListJson")
public class EmpListJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmpListJsonServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list = dao.getEmplist();

		String jsonFile = "[";
		int i = 1;
		for (EmployeeVO emp : list) {
			jsonFile += "{";
			jsonFile += "\"id\":" + emp.getEmployeeId() + "";
			jsonFile += ",\"firstName\":\"" + emp.getFirstName() + "\"";
			jsonFile += ",\"lastName\":\"" + emp.getLastName() + "\"";
			jsonFile += ",\"email\":\"" + emp.getEmail() + "\"";
			jsonFile += ",\"hireDate\":\"" + emp.getHireDate() + "\"";
			jsonFile += "}"; // 마지막 데이터만 ,없이 나오게
			if (list.size() != i++) { // list.size와 i가 다르면 , 찍는다는 소리
				jsonFile += ",";
			}
		}
		jsonFile += "]";
		response.getWriter().append(jsonFile);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
