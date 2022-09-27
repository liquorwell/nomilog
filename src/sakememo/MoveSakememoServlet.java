package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Sakelog;
import bean.User;
import dao.CategoryDao;
import dao.SakelogDao;
import dao.SakememoDao;
import validation.SakelogError;
import validation.SakelogValidation;

/**
 * Servlet implementation class MoveSakememoServlet <br>
 * 酒メモ→酒ログ移動処理
 */
@WebServlet("/sakememo_move")
public class MoveSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveSakememoServlet() {
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
	 * @see SakelogValidation#validateInsertValue(String sakelogName, String categoryId, String rating, String sakelogComment)
	 * @see SakelogDao#insert(Sakelog sakelog)
	 * @see SakememoDao#move(String strSakememoId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//移動前の酒メモIDを取得
		String sakememoId = request.getParameter("sakememo_id");
		
		//フォームから受け取った値とログイン中のユーザーから、酒ログbeanを作成
		String sakelogName = request.getParameter("sakelog_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String rating = request.getParameter("rating");
		String sakelogComment = request.getParameter("sakelog_comment");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Sakelog sakelog = new Sakelog(null, sakelogName, rating, sakelogComment, category, user);
		
		//バリデーション
		//不備がある場合、入力情報とエラー情報をリクエストに格納して登録画面に戻る
		SakelogError sakelogError = SakelogValidation.validateInsertValue(sakelogName, categoryId, rating, sakelogComment);
		if (!sakelogError.isAllFieldNull()) {
			request.setAttribute("sakelog", sakelog);
			request.setAttribute("sakememoId", sakememoId);
			request.setAttribute("sakelogError", sakelogError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_move.jsp").forward(request, response);
			return;
		}
		
		//酒ログテーブルに登録
		//酒メモテーブルで移動処理
		SakelogDao.insert(sakelog);
		SakememoDao.move(sakememoId);
		
		//酒ログ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

}
