package category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import dao.CategoryDao;
import validation.CategoryError;
import validation.CategoryValidation;

/**
 * Servlet implementation class UpdateCategoryServlet <br>
 * カテゴリ編集処理
 */
@WebServlet("/category_update")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
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
	 * @see CategoryValidation#validateCategoryName(String categoryName)
	 * @see CategoryDao#update(category)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから受け取った値を取り出し、カテゴリbeanを生成
		String categoryId = request.getParameter("category_id");
		String categoryName = request.getParameter("category_name");
		Category category = new Category(categoryId, categoryName, null);
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納して編集画面に戻る
		CategoryError categoryError = CategoryValidation.validateCategoryName(categoryName);
		if (!categoryError.isAllFieldNull()) {
			request.setAttribute("categoryError", categoryError);
			request.setAttribute("category", category);
			request.getRequestDispatcher("jsp/category/category_update.jsp").forward(request, response);
			return;
		}
		
		//カテゴリテーブルを更新
		CategoryDao.update(category);
		
		//カテゴリ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/category");
	}

}
