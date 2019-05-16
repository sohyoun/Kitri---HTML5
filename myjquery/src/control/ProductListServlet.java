package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("서블릿호출");
		ProductService service = new ProductService();
		//System.out.println("이건됬니?");
		//System.out.println(service.findAll());
		List<Product> list = new ArrayList<Product>();
		list = service.findAll();
		//System.out.println("이거는..?");
		//System.out.println(list);
		
		request.setAttribute("listresult", list);
		String path = "productresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		//System.out.println("가라가라");
		
	}

	
}
