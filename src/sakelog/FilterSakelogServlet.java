package sakelog;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class FilterSakelogServlet
 */
@WebServlet("/sakelog_filter")
public class FilterSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterSakelogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filterType = request.getParameter("filter_type");
		
		List<Sakelog> sakelogList =  new ArrayList<Sakelog>();
		switch (filterType) {
			case "name":
				String sakelogName = request.getParameter("sakelog_name");
				sakelogList = SakelogDao.findBySakelogName(sakelogName);
				break;
			case "category":
				String categoryId = request.getParameter("category_id");
				sakelogList = SakelogDao.findByCategoryId(categoryId);
				break;
			case "rating":
				String rating = request.getParameter("rating");
				sakelogList = SakelogDao.findByRating(rating);
				break;
			case "ins_date":
				String insDateOld = request.getParameter("ins_date_old");
				String insDateNew = request.getParameter("ins_date_new");
				sakelogList = SakelogDao.findByInsDate(insDateOld, insDateNew);
		}
		request.setAttribute("sakelogList", sakelogList);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}
