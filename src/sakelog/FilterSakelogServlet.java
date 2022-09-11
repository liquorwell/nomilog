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

import bean.Sakelog;
import bean.User;
import dao.SakelogDao;
import validation.SakelogError;
import validation.SakelogValidation;

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
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		List<Sakelog> sakelogList =  new ArrayList<Sakelog>();
		if (filterType == null) {
			sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
		} else {
			SakelogError sakelogError = new SakelogError();
			switch (filterType) {
			case "name":
				String sakelogName = request.getParameter("sakelog_name");
				sakelogList = SakelogDao.findBySakelogName(userId, sakelogName);
				request.setAttribute("nameFilterValue", sakelogName);
				break;
			case "category":
				String categoryId = request.getParameter("category_id");
				sakelogError = SakelogValidation.filterValueValidation(categoryId);
				if (sakelogError.isAllFieldNull()) {
					sakelogList = SakelogDao.findByCategoryId(userId, categoryId);
				} else {
					sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
				}
				request.setAttribute("categoryFilterValue", categoryId);
				break;
			case "rating":
				String rating = request.getParameter("rating");
				sakelogError = SakelogValidation.filterValueValidation(rating);
				if (sakelogError.isAllFieldNull()) {
					sakelogList = SakelogDao.findByRating(userId, rating);
				} else {
					sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
				}
				request.setAttribute("ratingFilterValue", rating);
				break;
			case "ins_date":
				String insDateOld = request.getParameter("ins_date_old");
				String insDateNew = request.getParameter("ins_date_new");
				sakelogError = SakelogValidation.filterInsDateValidation(insDateOld, insDateNew);
				if (sakelogError.isAllFieldNull()) {
					sakelogList = SakelogDao.findByInsDate(userId, insDateOld, insDateNew);
				} else {
					sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
				}
				request.setAttribute("insDateOldFilterValue", insDateOld);
				request.setAttribute("insDateNewFilterValue", insDateNew);
				break;
			}
			request.setAttribute("sakelogError", sakelogError);
		}
		
		request.setAttribute("sakelogList", sakelogList);
		
		request.setAttribute("filterType", filterType);
		
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}
