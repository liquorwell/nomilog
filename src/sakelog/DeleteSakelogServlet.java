package sakelog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sakelog;
import bean.User;
import dao.SakelogDao;

/**
 * Servlet implementation class DeleteSakelogServlet
 */
@WebServlet("/sakelog_delete")
public class DeleteSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSakelogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sakelogId = request.getParameter("sakelog_id");
		SakelogDao.delete(sakelogId);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Sakelog> sakelogList = SakelogDao.findByUserId(user.getUserId());
		request.setAttribute("sakelogList", sakelogList);
		request.getRequestDispatcher("jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
