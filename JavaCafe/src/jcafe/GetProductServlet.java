package jcafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetProductServlet")
public class GetProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한건 조회 후 처리하는 기능을 넣을 예정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		String itemNo = request.getParameter("item_no");
		ProductVO vo = new ProductVO();
		vo.setItemNo("bean_001");

		ProductDAO dao = new ProductDAO();
		ProductVO result = dao.getProduct(vo);// 23번째줄 파라미터값을 던져줌

		String json = "{";
		json += "\"itemNo\":\"" + result.getItemNo() + "\"";
		json += ",\"item\":\"" + result.getItem() + "\"";
		json += ",\"category\":\"" + result.getCategory() + "\"";
		json += ",\"price\":\"" + result.getPrice() + "\"";
		json += ",\"link\":\"" + result.getLink() + "\"";
		json += ",\"content\":\"" + result.getContent() + "\"";
		json += ",\"likeIt\":\"" + result.getLikeIt() + "\"";
		json += ",\"alt\":\"" + result.getAlt()+ "\"";
		json += ",\"image\":\"" + result.getImage() + "\"";
		
		json += "}";
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
