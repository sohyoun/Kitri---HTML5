package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.exception.NotFoundException;
import com.kitri.service.ProductService;


@WebServlet("/viewcart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ProductService service;
	
	public ViewCartServlet() {
		service = new ProductService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1)세선얻기
		HttpSession session = request.getSession();
		//2)세션속성중 "cart"속성얻기
		Map<Product, Integer> c = (Map)session.getAttribute("cart");
		Map<Product, Integer> rc = new HashMap<Product, Integer>();
		//3)장바구니에 상품이 있을때만
		if(c!=null) {
			Set<Product> keys = c.keySet();
			for(Product p : keys) {
				String no = p.getProd_no();
				try {
					Product p1 = service.findByNo(no);
					int quantity =  c.get(p);
					rc.put(p1, quantity);
					
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("rcart", rc);
			String path = "/viewcartresult.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
	
}
