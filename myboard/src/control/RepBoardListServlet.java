package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.PageBean;
import com.kitri.dto.RepBoard;
import com.kitri.service.RepBoardService;


@WebServlet("/boardlist")
public class RepBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RepBoardService service;
	
	public RepBoardListServlet() {
		service = new RepBoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cp = request.getParameter("currentPage");
		int currentPage = 1; //보여줄 현재 페이지
		if(cp != null) {
			currentPage = Integer.parseInt(cp);
		}
		int cntPerPage = 10; //페이지별 보여줄 목록 수
		int totalCnt = service.getTotalCnt();//총게시글 수
		int cntPerPageGroup = 3; //페이지그룹에 보여줄 페이지 수
		String url ="boardlist";
		PageBean pb = new PageBean(cntPerPage, totalCnt, cntPerPageGroup, url, currentPage);
		
		//현재 페이지, startRow, endRow
		// 1		1			10
		// 2		11			20
		// 5		41			50
		//int startRow = (currentPage-1)*10 + 1;
		//int endRow = currentPage*10;
		
		List<RepBoard> list = service.findByRows(pb.getStartRow(), pb.getEndRow());
		//request.setAttribute("boardlist", list); // request에 list값 집어넣음
		pb.setList(list);
		//--------------------------------------------------------------------------
		
		//총 페이지수 계산
		//int totalPage = 1;//총 페이지 수
		//totalPage = (int)Math.ceil((float)totalCnt/cntPerPage);//ceil : 무조건 올림하는 함수
		//request.setAttribute("totalPage", totalPage);
		//---------------------------------------------------------------------------
		
		//페이지 그룹핑
		//currentpage가 1~3까지는 시작페이지 1	끝페이지 3
		//			   4~6까지는 시작페이지 4	끝페이지 6
		//			   7~9까지는 시작페이지 7	끝페이지 9
		//int startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		//int endPage = startPage + cntPerPageGroup -1;
		//if(endPage > totalPage ) {
		//	endPage = totalPage;
		//}
		//request.setAttribute("strtPage", startPage);
		//request.setAttribute("endPage", endPage);
		
		request.setAttribute("pb", pb);
		String path="/boardlistresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
