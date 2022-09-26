package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SakememoDao;

/**
 * Servlet implementation class DeleteSakememoServlet <br>
 * 酒メモ削除処理
 */
@WebServlet("/sakememo_delete")
public class DeleteSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSakememoServlet() {
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
	 * @see SakememoDao#delete(String strSakememoId)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//酒メモテーブルから削除
		String sakememoId = request.getParameter("sakememo_id");
		SakememoDao.delete(sakememoId);
		
		//酒メモ画面にリダイレクト
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

}
