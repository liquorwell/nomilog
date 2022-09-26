package sakelog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SakelogDao;

/**
 * Servlet implementation class DeleteSakelogServlet <br>
 * 酒ログ削除処理
 */
@WebServlet("/sakelog_delete")
public class DeleteSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSakelogServlet() {
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
	 * @see SakelogDao#delete(String strSakelogId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//酒ログを削除
		String sakelogId = request.getParameter("sakelog_id");
		SakelogDao.delete(sakelogId);
		
		//酒ログ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

}
