package jcafe;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.ContextName;

import common.EmpDAO;

@WebServlet("/PostProductServlet")
public class PostProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String itemNo = request.getParameter("itemNo"); // 여기에 들어가는게 name값들
		String item = request.getParameter("item");
		String category = request.getParameter("category");
		String price = request.getParameter("price");
		String content = request.getParameter("content");
		String likeIt = request.getParameter("likeIt");
		String image = request.getParameter("image");
		//parameter값을 받아와서 저장해줌
		ProductVO vo = new ProductVO();
		vo.setItemNo(itemNo);
		vo.setItem(item);
		vo.setCategory(category);
		vo.setPrice(Integer.parseInt(price));
		vo.setContent(content);
		vo.setLikeIt(Integer.parseInt(likeIt));
		vo.setImage(image);
		ProductDAO dao = new ProductDAO();
		dao.insertProduct(vo);
		
		String script = "<script>location.href='jcafe/cafeList.html'</script>";
		response.getWriter().append(script);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
