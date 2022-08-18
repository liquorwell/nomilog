package sakememo;

import java.io.IOException;
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
		String sakememoId = request.getParameter("sakememo_id");
		String sakememoName = request.getParameter("sakememo_name");
		String categoryId = request.getParameter("category_id");
		Category category = CategoryDao.findById(categoryId);
		String sakememoComment = request.getParameter("sakememo_comment");
		
		Sakememo sakememo = new Sakememo();
		sakememo.setSakememoId(sakememoId);
		sakememo.setSakememoName(sakememoName);
		sakememo.setCategory(category);
		sakememo.setSakememoComment(sakememoComment);
		SakememoDao.update(sakememo);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Sakememo> sakememoList = SakememoDao.findByUserId(user.getUserId());
		request.setAttribute("sakememoList", sakememoList);
		request.getRequestDispatcher("jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}

}
