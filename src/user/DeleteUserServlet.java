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
 * Servlet implementation class DeleteUserServlet <br>
 * ユーザー削除処理
 */
@WebServlet("/user_delete")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see UserValidation#validateDeleteUserValue(String userPass, int userId)
	 * @see UserDao#delete(int userId)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームの値からパスワードを入手
		String userPass = request.getParameter("user_pass");		
		
		//ログイン中のユーザーのユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//バリデーション
		//不備がある場合、エラー情報をリクエストに格納して削除画面に戻る
		UserError userError = UserValidation.validateDeleteUserValue(userPass, userId);
		if (userError != null) {
			request.setAttribute("userError", userError);
			request.getRequestDispatcher("/jsp/user/user_delete_check.jsp").forward(request, response);
			return;
		}
		
		//ユーザーテーブルから削除
		UserDao.delete(userId);
		
		//トップページにリダイレクト
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
