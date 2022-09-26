package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Sakelog;
import bean.Sakememo;
import dao.SakememoDao;

/**
 * Servlet implementation class TransitionToSakememoMoveServlet <br>
 * 酒メモ移動画面遷移処理
 */
@WebServlet("/sakememo_tranmove")
public class TransitionToSakememoMoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToSakememoMoveServlet() {
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
		//酒メモIDから酒メモを検索
		String sakememoId = request.getParameter("sakememo_id");
		Sakememo sakememo = SakememoDao.findBySakememoId(sakememoId);
		
		//酒メモから酒ログを生成
		Sakelog sakelog = new Sakelog(sakememo);
		
		//酒メモIDと酒ログをリクエストに格納
		request.setAttribute("sakememoId", sakememoId);
		request.setAttribute("sakelog", sakelog);
		
		//酒メモ移動画面にフォワード
		request.getRequestDispatcher("jsp/sakememo/sakememo_move.jsp").forward(request, response);
	}

}
