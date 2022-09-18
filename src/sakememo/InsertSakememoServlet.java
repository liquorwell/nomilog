package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Category;
import bean.Sakememo;
import bean.User;
import dao.CategoryDao;
import dao.SakememoDao;
import validation.SakememoError;
import validation.SakememoValidation;

/**
 * Servlet implementation class InsertSakememoServlet
 */
@WebServlet("/sakememo_insert")
public class InsertSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSakememoServlet() {
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
		String sakememoName = request.getParameter("sakememo_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String sakememoComment = request.getParameter("sakememo_comment");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Sakememo sakememo = new Sakememo(null, sakememoName, sakememoComment, null, category, user);
		
		SakememoError sakememoError = SakememoValidation.insertValidation(sakememoName, categoryId, sakememoComment);
		if (sakememoError != null) {
			request.setAttribute("sakememo", sakememo);
			request.setAttribute("sakememoError", sakememoError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_insert.jsp").forward(request, response);
			return;
		}
		
		SakememoDao.insert(sakememo);
		
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

}
