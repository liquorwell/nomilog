package category;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.User;
import dao.CategoryDao;

/**
 * Servlet implementation class TransitionToCategoryServlet <br>
 * カテゴリ画面遷移処理
 */
@WebServlet("/category")
public class TransitionToCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToCategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see CategoryDao#findByUserId(int userId)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン中のユーザーのカテゴリを全件取得し、セッションに格納
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		//カテゴリ画面にフォワード
		request.getRequestDispatcher("jsp/category/category_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
