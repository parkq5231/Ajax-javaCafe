package common;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");	
		// TODO Auto-generated method stub
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO> list =dao.getEmplist();
		String xml = "<dataset>";
		for (EmployeeVO emp : list) {			
			xml +="<record>";
			xml +="<empId>"+emp.getEmployeeId()+"</empId>"+
				  "<firstName>"+emp.getFirstName()+"</firstName>"+
				  "<lastName>"+emp.getLastName()+"</lastName>"+
				  "<email>"+emp.getEmail()+"</email>";
			xml +="</record>";
									}
			xml +="</dataset>";
		response.getWriter().append(xml);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
