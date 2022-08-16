package sakelog;

import java.io.IOException;
import java.util.List;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		String sakelogId = request.getParameter("sakelog_id");
		Sakelog sakelog = SakelogDao.findById(sakelogId);
		request.setAttribute("sakelog", sakelog);
		request.getRequestDispatcher("jsp/sakelog/sakelog_update.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
