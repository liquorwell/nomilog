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

/**
 * Servlet implementation class SortSakelogServlet
 */
@WebServlet("/sakelog_sort")
public class SortSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortSakelogServlet() {
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
		String sortType = request.getParameter("sort_type");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		List<Sakelog> sakelogList =  new ArrayList<Sakelog>();
		switch (sortType) {
			case "ins_date_desc":
				sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
				break;
			case "ins_date_asc":
				sakelogList = SakelogDao.findByUserIdInsDateAsc(userId);
				break;
			case "rating_desc":
				sakelogList = SakelogDao.findByUserIdRatingDesc(userId);
				break;
			case "rating_asc":
				sakelogList = SakelogDao.findByUserIdRatingAsc(userId);
				break;
			case "category":
				sakelogList = SakelogDao.findByUserIdCategoryIdAsc(userId);
				break;
		}
		request.setAttribute("sakelogList", sakelogList);
		
		request.setAttribute("sortType", sortType);
		
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}
