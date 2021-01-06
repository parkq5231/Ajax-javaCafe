package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");	
		
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list =dao.getEmplist();
		String xml = "<dataset>";
		
		for (EmployeeVO emp : list) {			
			xml +="<record>";
			xml +="<empId>"+emp.getEmployeeId()+"</empId>"+
				  "<firstName>"+emp.getFirstName()+"</firstName>"+
				  "<lastName>"+emp.getLastName()+"</lastName>"+
				  "<email>"+emp.getEmail()+"</email>"+
				  "<jobId>"+emp.getJobId()+"</jobId>";
			xml +="</record>";
		}
			xml +="</dataset>";
		response.getWriter().append(xml);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
