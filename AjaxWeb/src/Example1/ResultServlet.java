package Example1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boarder")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResultServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		exDAO dao = new exDAO();
		List<exVO> list = dao.getList();
		String xml = "<dataset>";
		
		for(exVO emp : list) {
			xml +="<record>";
			xml +="<no>"+emp.getNum()+"</no>"+
				  "<title>"+emp.getTitle()+"</title>"+
				  "<content>"+emp.getContent()+"</content>"+
				  "<creationdate>"+emp.getCreationDate()+"</creationdate>";
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
