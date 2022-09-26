package category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.User;
import dao.CategoryDao;
import validation.CategoryError;
import validation.CategoryValidation;

/**
 * Servlet implementation class InsertCategoryServlet <br>
 * カテゴリ登録処理
 */
@WebServlet("/category_insert")
public class InsertCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/category");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see validation.CategoryValidation#validateCategoryName(String categoryName)
	 * @see dao.CategoryDao#insert(category)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから受け取ったカテゴリ名とログイン中のユーザーを取り出し、カテゴリbeanを生成
		String categoryName = request.getParameter("category_name");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Category category = new Category(null, categoryName, user);
		
		//バリデーション処理
		//不備がある場合、入力情報とエラー情報をリクエストに格納してカテゴリ登録画面に戻る
		CategoryError categoryError = CategoryValidation.validateCategoryName(categoryName);
		if (categoryError != null) {
			request.setAttribute("categoryError", categoryError);
			request.setAttribute("category", category);
			request.getRequestDispatcher("jsp/category/category_insert.jsp").forward(request, response);
			return;
		}
		
		//カテゴリテーブルに登録
		CategoryDao.insert(category);
		
		//カテゴリ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/category");
	}
	
}
