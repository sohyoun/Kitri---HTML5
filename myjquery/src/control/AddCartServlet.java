package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;


@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String quantity = request.getParameter("quantity");
		HttpSession session = request.getSession(); //장바구니 담아놓음
		Map<Product, Integer> c = (Map)session.getAttribute("cart");//기존에 카트에 담은게 있느냐
		if(c == null) {//카트에 담은게 없다면
			c= new HashMap<>();
			session.setAttribute("cart", c);//새로생성해서 담아라
		}
		
		Product p = new Product();
		p.setProd_no(no);
		
		int intQuantity = Integer.parseInt(quantity);//예전수량
		
		Integer inte = c.get(p); //장바구니에 해당상품이 존재하는가 확인
		if(inte != null){//존재하면 수량을 합산.
			intQuantity += inte.intValue();
			
		}
		c.put(p, intQuantity);//장바구니에 상품,수량 추가
		
		String path = "/addcartresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
//		System.out.println("-----장바구니내용-----");
//		Set<Product> keys = c.keySet();
//		for(Product key:keys) {
//			int q = c.get(key);
//			System.out.println("상품번호:"+key.getProd_no()+", 수량 : "+q);
//		}
	}

	

}
