package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exampleServlet")
public class exampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public exampleServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");//한글설정
		response.setContentType("text/html charset=utf-8");//한글설정2
		EmpDAO dao=new EmpDAO();
		List<EmployeeVO> list = dao.getSelectList();
		String json="[";
		int cnt = 1; 
		for(EmployeeVO emp: list) {
			
		json+="{\"empId\":"+emp.getEmployeeId()+"";
		json+=" ,\"firstName\":\""+emp.getFirstName()+"\"";
		json+=",\"lastName\":\""+emp.getLastName()+"\"";
		json+=",\"email\":\""+emp.getEmail()+"\"";
		json+=",\"hireDate\":\""+emp.getHireDate()+"\"";
		json+=",\"salary\":\""+emp.getSalary()+"\"}";
		if(list.size()!=cnt++) {
			json+=",";
		}
		}		
		json+="]";
		response.getWriter().append(json);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
