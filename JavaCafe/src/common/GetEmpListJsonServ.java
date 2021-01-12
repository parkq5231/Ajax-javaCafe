package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getEmpList")//java지만 html처럼 웹 페이지임
//DB에서 가져온 값을 Json파일로 바꿔주면 HTML에서 ajax호출시 사용가능
public class GetEmpListJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetEmpListJsonServ() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		EmpDAO dao = new EmpDAO();
		List<EmployeeVO>list = dao.getEmplist();//List타입에 EmployeeVO값 담음
		PrintWriter out = response.getWriter();
		String json = "[";//String을 담기위한 변수 선언
		int cnt=1;
		for(EmployeeVO emp : list ) {
			json+="{";
			json+="\"empId\":\""+emp.getEmployeeId()+"\"";
			json+=",\"firstName\":\""+emp.getFirstName()+"\"";
			json+=",\"lastName\":\""+emp.getLastName()+"\"";
			json+="}";
			if(list.size()!=cnt++) {
				json+=",";
			}
		}
		json += "]";//+=는 누적의 의미
		out.print(json);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
