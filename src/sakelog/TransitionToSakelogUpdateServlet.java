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
 * Servlet implementation class TransitionToSakelogUpdateServlet
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sakelogId = request.getParameter("sakelog_id");
		Sakelog sakelog = SakelogDao.findBySakelogId(sakelogId);
		request.setAttribute("sakelog", sakelog);
		
		request.getRequestDispatcher("jsp/sakelog/sakelog_update.jsp").forward(request, response);
	}

}
