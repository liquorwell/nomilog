package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Sakememo;
import bean.User;
import dao.CategoryDao;
import dao.SakememoDao;
import validation.SakememoError;
import validation.SakememoValidation;

/**
 * Servlet implementation class InsertSakememoServlet <br>
 * 酒メモ登録処理
 */
@WebServlet("/sakememo_insert")
public class InsertSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSakememoServlet() {
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
	 * @see SakememoValidation#validateInsertValue(String sakememoName, String categoryId, String sakememoComment)
	 * @see SakememoDao#insert(Sakememo sakememo)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから受け取った値とログイン中のユーザーから、酒ログbeanを作成
		String sakememoName = request.getParameter("sakememo_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String sakememoComment = request.getParameter("sakememo_comment");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Sakememo sakememo = new Sakememo(null, sakememoName, sakememoComment, null, category, user);
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納して登録画面に戻る
		SakememoError sakememoError = SakememoValidation.validateInsertValue(sakememoName, categoryId, sakememoComment);
		if (!sakememoError.isAllFieldNull()) {
			request.setAttribute("sakememo", sakememo);
			request.setAttribute("sakememoError", sakememoError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_insert.jsp").forward(request, response);
			return;
		}
		
		//酒メモテーブルに登録
		SakememoDao.insert(sakememo);
		
		//酒メモ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

}
