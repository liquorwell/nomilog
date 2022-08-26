package sakememo;

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
import bean.Sakememo;
import bean.User;
import dao.CategoryDao;
import dao.SakememoDao;

/**
 * Servlet implementation class FilterSakememoServlet
 */
@WebServlet("/sakememo_filter")
public class FilterSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterSakememoServlet() {
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
		
		List<Sakememo> sakememoList =  new ArrayList<Sakememo>();
		switch (filterType) {
			case "name":
				String sakememoName = request.getParameter("sakememo_name");
				sakememoList = SakememoDao.findBySakememoName(sakememoName);
				break;
			case "category":
				String categoryId = request.getParameter("category_id");
				sakememoList = SakememoDao.findByCategoryId(categoryId);
				break;
			case "ins_date":
				String insDateOld = request.getParameter("ins_date_old");
				String insDateNew = request.getParameter("ins_date_new");
				sakememoList = SakememoDao.findByInsDate(insDateOld, insDateNew);
		}
		request.setAttribute("sakememoList", sakememoList);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		request.getRequestDispatcher("/jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}

}
