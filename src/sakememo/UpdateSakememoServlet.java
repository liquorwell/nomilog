package sakememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import bean.Sakememo;
import dao.CategoryDao;
import dao.SakememoDao;
import validation.SakememoError;
import validation.SakememoValidation;

/**
 * Servlet implementation class UpdateSakememoServlet
 */
@WebServlet("/sakememo_update")
public class UpdateSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSakememoServlet() {
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
		String sakememoName = request.getParameter("sakememo_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findByCategoryId(categoryId);
		String sakememoComment = request.getParameter("sakememo_comment");
		
		Sakememo sakememo = new Sakememo(sakememoId, sakememoName, sakememoComment, null, category, null);
		
		SakememoError sakememoError = SakememoValidation.updateValidation(sakememoName, sakememoComment);
		if (sakememoError != null) {
			request.setAttribute("sakememo", sakememo);
			request.setAttribute("sakememoError", sakememoError);
			request.getRequestDispatcher("jsp/sakememo/sakememo_insert.jsp").forward(request, response);
			return;
		}
		
		SakememoDao.update(sakememo);
		
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

}
