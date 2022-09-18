package category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Category;
import dao.CategoryDao;
import validation.CategoryError;
import validation.CategoryValidation;

/**
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/category_update")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/category");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("category_id");
		String categoryName = request.getParameter("category_name");
		
		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		CategoryError categoryError = CategoryValidation.categoryNameValidation(categoryName);
		if (categoryError != null) {
			request.setAttribute("categoryError", categoryError);
			request.setAttribute("category", category);
			request.getRequestDispatcher("jsp/category/category_update.jsp").forward(request, response);
			return;
		}
		
		CategoryDao.update(category);
		
		response.sendRedirect(request.getContextPath() + "/category");
	}

}
