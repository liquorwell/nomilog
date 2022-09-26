package sakelog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sakelog;
import bean.User;
import dao.SakelogDao;

/**
 * Servlet implementation class SortSakelogServlet <br>
 * 酒ログ並べ替え処理
 */
@WebServlet("/sakelog_sort")
public class SortSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortSakelogServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see SakelogDao#findByUserId
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索用にユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//検索結果格納用
		List<Sakelog> sakelogList =  new ArrayList<Sakelog>();
		
		//並べ替え種別ごとに検索処理
		String sortType = request.getParameter("sort_type");
		switch (sortType) {
			case "ins_date_desc":
				sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
				break;
			case "ins_date_asc":
				sakelogList = SakelogDao.findByUserIdInsDateAsc(userId);
				break;
			case "rating_desc":
				sakelogList = SakelogDao.findByUserIdRatingDesc(userId);
				break;
			case "rating_asc":
				sakelogList = SakelogDao.findByUserIdRatingAsc(userId);
				break;
			case "category":
				sakelogList = SakelogDao.findByUserIdCategoryIdAsc(userId);
				break;
		}
		
		//結果と並べ替え種別をリクエストに格納
		//並べ替え種別を格納するのは、フォームの値保持のため
		request.setAttribute("sakelogList", sakelogList);
		request.setAttribute("sortType", sortType);
		
		//酒ログ画面にフォワード
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}