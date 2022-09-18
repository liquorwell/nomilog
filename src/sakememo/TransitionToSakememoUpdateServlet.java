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
 * Servlet implementation class TransitionToSakememoUpdateServlet
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sakememoId = request.getParameter("sakememo_id");
		Sakememo sakememo = SakememoDao.findBySakememoId(sakememoId);
		request.setAttribute("sakememo", sakememo);
		
		request.getRequestDispatcher("jsp/sakememo/sakememo_update.jsp").forward(request, response);
	}

}
