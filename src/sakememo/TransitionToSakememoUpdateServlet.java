package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Sakememo;
import dao.SakememoDao;

/**
 * Servlet implementation class TransitionToSakememoUpdateServlet <br>
 * 酒ログ編集画面遷移処理
 */
@WebServlet("/sakememo_edit")
public class TransitionToSakememoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToSakememoUpdateServlet() {
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
	 * @see SakememoDao#findBySakememoId(String strSakememoId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//酒メモIDから酒メモを検索し、結果をリクエストに格納
		String sakememoId = request.getParameter("sakememo_id");
		Sakememo sakememo = SakememoDao.findBySakememoId(sakememoId);
		request.setAttribute("sakememo", sakememo);
		
		//酒メモ編集画面にフォワード
		request.getRequestDispatcher("jsp/sakememo/sakememo_update.jsp").forward(request, response);
	}

}
