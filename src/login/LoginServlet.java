package login;

import java.io.IOException;
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
import dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		HttpSession session = request.getSession(false);
		session.removeAttribute("user");
		
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		User user = UserDao.findByNamePass(userName, userPass);
		session.setAttribute("user", user);
		
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		List<Sakelog> sakelogList = SakelogDao.findByUserIdInsDateDesc(user.getUserId());
		request.setAttribute("sakelogList", sakelogList);
		
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}

}
