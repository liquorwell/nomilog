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

import bean.Sakememo;
import bean.User;
import dao.SakememoDao;

/**
 * Servlet implementation class SortSakememoServlet
 */
@WebServlet("/sakememo_sort")
public class SortSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortSakememoServlet() {
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
		
		List<Sakememo> sakememoList =  new ArrayList<Sakememo>();
		switch (sortType) {
			case "ins_date_desc":
				sakememoList = SakememoDao.findByUserIdInsDateDesc(userId);
				break;
			case "ins_date_asc":
				sakememoList = SakememoDao.findByUserIdInsDateAsc(userId);
				break;
			case "category":
				sakememoList = SakememoDao.findByUserIdCategoryIdAsc(userId);
				break;
		}
		request.setAttribute("sakememoList", sakememoList);
		
		request.getRequestDispatcher("/jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}
}