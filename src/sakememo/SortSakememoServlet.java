package sakememo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sakememo;
import bean.User;
import dao.SakememoDao;

/**
 * Servlet implementation class SortSakememoServlet <br>
 * 酒メモ並べ替え処理
 */
@WebServlet("/sakememo_sort")
public class SortSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortSakememoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see SakememoDao.findByUserId
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索用にユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//検索結果格納用
		List<Sakememo> sakememoList =  new ArrayList<Sakememo>();
		
		//並べ替え種別ごとに検索処理
		String sortType = request.getParameter("sort_type");
		switch (sortType) {
			case "ins_date_desc":
				sakememoList = SakememoDao.findByUserIdInsDateDesc(userId);
				break;
			case "ins_date_asc":
				sakememoList = SakememoDao.findByUserIdInsDateAsc(userId);
				break;
			case "category":
				sakememoList = SakememoDao.findByUserIdCategoryIdAsc(userId);
				break;
		}
		
		//結果と並べ替え種別をリクエストに格納
		//並べ替え種別を格納するのは、フォームの値保持のため
		request.setAttribute("sakememoList", sakememoList);
		request.setAttribute("sortType", sortType);
		
		//酒メモ画面にフォワード
		request.getRequestDispatcher("/jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}
}