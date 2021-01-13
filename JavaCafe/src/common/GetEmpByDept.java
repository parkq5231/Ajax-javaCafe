package common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getMembersByDept")
public class GetEmpByDept extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEmpByDept() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		EmpDAO dao = new EmpDAO();
		Map<String, Integer> map = dao.getMemberByDept();
		Set<String> ketSet = map.keySet();				//return type = set collection (Set <String>을 말함)
		String json="[";
		int cnt = 1;
		for (String key : ketSet) {
			json += "{";
			json += "\""+key+"\":"+map.get(key);//map이 가지고 있는 get 메소드에 key값을 던져주면 value가 됨
			json += "}";
			if(map.size()!=cnt++) {//map은 처음부터 사이즈값을 가지고 있어서 증가되는게 아니라 고정값(7)이 들어가 있는거 
				json+=",";
			}
			//System.out.println(key+", "+map.get(key));	//key , value
		}
		json+="]";
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
