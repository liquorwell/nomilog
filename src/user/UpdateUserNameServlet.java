package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import validation.UserError;
import validation.UserValidation;

/**
 * Servlet implementation class UpdateUserNameServlet <br>
 * ユーザー名更新処理
 */
@WebServlet("/user_name_update")
public class UpdateUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserNameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/user");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see UserValidation#validateUpdateUserNameValue(String userName, int userId)
	 * @see UserDao#updateUserName(int userId, String userName)
	 * @see UserDao#findByUserId(int userId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン中のユーザーのユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//フォームの値からユーザー名を入手
		String userName = request.getParameter("user_name");
		
		//バリデーション
		//不備がある場合、エラー情報とユーザー名をリクエストに格納して編集画面に戻る
		UserError userError = UserValidation.validateUpdateUserNameValue(userName, userId);
		if (userError != null) {
			request.setAttribute("userError", userError);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("jsp/user/user_name_update.jsp").forward(request, response);
			return;
		}
		
		//ユーザーテーブルを更新
		UserDao.updateUserName(userId, userName);
		
		//更新したユーザーをセッションに格納
		user = UserDao.findByUserId(userId);
		session.setAttribute("user", user);
		
		//ユーザー画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/user");
	}

}
