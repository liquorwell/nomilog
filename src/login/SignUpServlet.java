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
 * Servlet implementation class SignUpServlet <br>
 * サインアップ処理
 */
@WebServlet("/signup_sakelog")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/signup");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see UserValidation.validateSignUpValue(String userName, String userPass, String checkPass)
	 * @see UserDao#insert(User user)
	 * @see CategoryDao#findByUserId(int userId)
	 * @see CategoryDao#insert(Category category)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームからユーザー名、パスワード、確認用パスワードを受け取る
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		String checkPass = request.getParameter("check_pass");
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納してサインアップ画面に戻る
		UserError userError = UserValidation.validateSignUpValue(userName, userPass, checkPass);		
		if (!userError.isAllFieldNull()) {
			request.setAttribute("userError", userError);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("jsp/user/signup.jsp").forward(request, response);
			return;
		}
		
		//ユーザーbeanに格納し、ユーザーテーブルに登録
		User user = new User(null, userName, userPass);
		UserDao.insert(user);
		
		//登録したユーザーをテーブルから取り出しなおしてセッションに格納
		//※ユーザーIDを入手するため、わざわざテーブルから取り出して上書きしている
		HttpSession session = request.getSession();
		user = UserDao.findByNamePass(userName, userPass);
		session.setAttribute("user", user);
		
		//プリセットのカテゴリをカテゴリテーブルに登録し、セッションに格納
		insertPresetCategory(user);
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		//酒ログ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}
	
	//プリセットのカテゴリを登録
	//最初にカテゴリ登録がされていないと、酒ログ登録前にカテゴリ登録を行う必要があり、使用感が悪いため
	private static void insertPresetCategory(User user) {
		CategoryDao.insert(new Category(null, "ビール", user));
		CategoryDao.insert(new Category(null, "日本酒", user));
		CategoryDao.insert(new Category(null, "その他", user));
	}

}
