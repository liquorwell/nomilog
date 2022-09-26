package sakelog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Sakelog;
import dao.SakelogDao;

/**
 * Servlet implementation class TransitionToSakelogUpdateServlet <br>
 * 酒ログ編集画面遷移処理
 */
@WebServlet("/sakelog_edit")
public class TransitionToSakelogUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToSakelogUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see SakelogDao#findBySakelogId(String strSakelogId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//酒ログIDから酒ログを検索し、結果をリクエストに格納
		String sakelogId = request.getParameter("sakelog_id");
		Sakelog sakelog = SakelogDao.findBySakelogId(sakelogId);
		request.setAttribute("sakelog", sakelog);
		
		//酒ログ編集画面にフォワード
		request.getRequestDispatcher("jsp/sakelog/sakelog_update.jsp").forward(request, response);
	}

}
