package sakelog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Sakelog;
import dao.CategoryDao;
import dao.SakelogDao;
import validation.SakelogError;
import validation.SakelogValidation;

/**
 * Servlet implementation class UpdateSakelogServlet
 */
@WebServlet("/sakelog_update")
public class UpdateSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSakelogServlet() {
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
		String sakelogName = request.getParameter("sakelog_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String rating = request.getParameter("rating");
		String sakelogComment = request.getParameter("sakelog_comment");
		
		Sakelog sakelog = new Sakelog(sakelogId, sakelogName, rating, sakelogComment, category, null);
		
		SakelogError sakelogError = SakelogValidation.updateValidation(sakelogName, sakelogComment);
		if (sakelogError != null) {
			request.setAttribute("sakelog", sakelog);
			request.setAttribute("sakelogError", sakelogError);
			request.getRequestDispatcher("jsp/sakelog/sakelog_update.jsp").forward(request, response);
			return;
		}
		
		SakelogDao.update(sakelog);
		
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

}
