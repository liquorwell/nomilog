package login;

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
import dao.UserDao;
import validation.UserError;
import validation.UserValidation;

/**
 * Servlet implementation class LoginServlet <br>
 * ログイン処理
 */
@WebServlet("/login_sakelog")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see UserValidation#validateLoginValue(String userName, String userPass)
	 * @see UserDao#findByNamePass(String userName, String userPass)
	 * @see CategoryDao#findByUserId(int userId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインフォームからユーザー名とパスワードを受け取る
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納してログイン画面に戻る
		UserError userError = UserValidation.validateLoginValue(userName, userPass);
		if (userError != null) {
			request.setAttribute("userError", userError);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
		}
		
		//ユーザーを検索し、結果をセッションに格納
		HttpSession session = request.getSession();
		User user = UserDao.findByNamePass(userName, userPass);
		session.setAttribute("user", user);
		
		//ユーザーの持つカテゴリをセッションに格納
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		//酒ログ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

}
