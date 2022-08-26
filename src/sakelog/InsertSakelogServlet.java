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
 * Servlet implementation class InsertSakelogServlet
 */
@WebServlet("/sakelog_insert")
public class InsertSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSakelogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sakelogName = request.getParameter("sakelog_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findById(categoryId);
		String rating = request.getParameter("rating");
		String sakelogComment = request.getParameter("sakelog_comment");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Sakelog sakelog = new Sakelog();
		sakelog.setSakelogName(sakelogName);
		sakelog.setCategory(category);
		sakelog.setRating(rating);
		sakelog.setSakelogComment(sakelogComment);
		sakelog.setUser(user);
		SakelogDao.insert(sakelog);
		
		List<Sakelog> sakelogList = SakelogDao.findByUserIdInsDateDesc(user.getUserId());
		request.setAttribute("sakelogList", sakelogList);
		request.getRequestDispatcher("jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}
