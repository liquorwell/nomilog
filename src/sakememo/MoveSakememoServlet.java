package sakememo;

import java.io.IOException;

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
import dao.SakememoDao;
import validation.SakelogError;
import validation.SakelogValidation;

/**
 * Servlet implementation class MoveSakememoServlet
 */
@WebServlet("/sakememo_move")
public class MoveSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoveSakememoServlet() {
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
		
		String sakelogName = request.getParameter("sakelog_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String rating = request.getParameter("rating");
		String sakelogComment = request.getParameter("sakelog_comment");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Sakelog sakelog = new Sakelog(null, sakelogName, rating, sakelogComment, category, user);
		
		SakelogError sakelogError = SakelogValidation.insertValidation(sakelogName, categoryId, rating, sakelogComment);
		if (sakelogError != null) {
			request.setAttribute("sakelog", sakelog);
			request.setAttribute("sakememoId", sakememoId);
			request.setAttribute("sakelogError", sakelogError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_move.jsp").forward(request, response);
			return;
		}
		
		SakelogDao.insert(sakelog);
		SakememoDao.move(sakememoId);

		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

}
