package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;
import com.kitri.service.RepBoardService;


@WebServlet("/writeboard")
public class WriteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepBoardService service;
	public WriteBoardServlet() {
		service = new RepBoardService();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service들어갔닝?");
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String password = request.getParameter("password");
		
		RepBoard repBoard = new RepBoard();
		repBoard.setBoard_subject(subject);
		repBoard.setBoard_writer(writer);
		repBoard.setBoard_contents(contents);
		repBoard.setBoare_password(password);
		
		try {
			service.write(repBoard);
			request.setAttribute("result", "글쓰기 성공");
		} catch (AddException e) {
			e.printStackTrace();
			request.setAttribute("result", "글쓰기 실패");
		}
		String path = "/writeboardresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

	

}
