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
 * Servlet implementation class TransitionToSakememoMoveServlet
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sakememoId = request.getParameter("sakememo_id");
		Sakememo sakememo = SakememoDao.findBySakememoId(sakememoId);
		
		Sakelog sakelog = new Sakelog(sakememo);
		
		request.setAttribute("sakememoId", sakememoId);
		request.setAttribute("sakelog", sakelog);
		
		request.getRequestDispatcher("jsp/sakememo/sakememo_move.jsp").forward(request, response);
	}

}
