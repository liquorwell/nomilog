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
 * Servlet implementation class UpdateUserPassServlet <br>
 * パスワード更新処理
 */
@WebServlet("/user_pass_update")
public class UpdateUserPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserPassServlet() {
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
	 * @see UserValidation#validateUpdateUserPassValue(String currPass, String newPass, String checkPass, int userId)
	 * @see UserDao#updateUserPass(int userId, String newPass)
	 * @see UserDao#findByUserId(int userId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン中のユーザーのユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//フォームの値から各種パスワードを入手
		String currPass = request.getParameter("curr_pass");
		String newPass = request.getParameter("new_pass");
		String checkPass = request.getParameter("check_pass");
		
		//バリデーション
		//不備がある場合、エラー情報をリクエストに格納して登録画面に戻る
		UserError userError = UserValidation.validateUpdateUserPassValue(currPass, newPass, checkPass, userId);
		if (!userError.isAllFieldNull()) {
			request.setAttribute("userError", userError);
			request.getRequestDispatcher("/jsp/user/user_pass_update.jsp").forward(request, response);
			return;
		}
		
		//ユーザーテーブルを更新
		UserDao.updateUserPass(userId, newPass);
		
		//更新したユーザーをセッションに格納
		user = UserDao.findByUserId(userId);
		session.setAttribute("user", user);
		
		//ユーザー画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/user");
	}

}
