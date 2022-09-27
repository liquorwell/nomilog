package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Sakememo;
import dao.CategoryDao;
import dao.SakememoDao;
import validation.SakememoError;
import validation.SakememoValidation;

/**
 * Servlet implementation class UpdateSakememoServlet <br>
 * 酒メモ更新処理
 */
@WebServlet("/sakememo_update")
public class UpdateSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSakememoServlet() {
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
	 * @see CategoryDao#findByCategoryId(String strCategoryId)
	 * @see SakememoValidation#validateUpdateValue(String sakememoName, String sakememoComment)
	 * @see SakememoDao#update(Sakememo sakememo)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームの値から酒メモbeanを作成
		String sakememoId = request.getParameter("sakememo_id");
		String sakememoName = request.getParameter("sakememo_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String sakememoComment = request.getParameter("sakememo_comment");
		Sakememo sakememo = new Sakememo(sakememoId, sakememoName, sakememoComment, null, category, null);
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納して登録画面に戻る
		SakememoError sakememoError = SakememoValidation.validateUpdateValue(sakememoName, sakememoComment);
		if (!sakememoError.isAllFieldNull()) {
			request.setAttribute("sakememo", sakememo);
			request.setAttribute("sakememoError", sakememoError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_insert.jsp").forward(request, response);
			return;
		}
		
		//酒メモテーブルを更新
		SakememoDao.update(sakememo);
		
		//酒メモ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

}
