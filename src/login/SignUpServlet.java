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
import bean.User;
import dao.CategoryDao;
import dao.UserDao;
import validation.UserError;
import validation.UserValidation;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup_sakelog")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/signup");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		String checkPass = request.getParameter("check_pass");
		
		UserError userError = UserValidation.signUpValidation(userName, userPass, checkPass);		
		if (userError != null) {
			request.setAttribute("userError", userError);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("jsp/user/signup.jsp").forward(request, response);
			return;
		}
		
		User user = new User(null, userName, userPass);
		UserDao.insert(user);
		
		HttpSession session = request.getSession();
		user = UserDao.findByNamePass(userName, userPass);
		session.setAttribute("user", user);
		
		insertPresetCategory(user);
		List<Category> categoryList = CategoryDao.findByUserId(user.getUserId());
		session.setAttribute("categoryList", categoryList);
		
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}
	
	
	private static void insertPresetCategory(User user) {
		CategoryDao.insert(new Category(null, "ビール", user));
		CategoryDao.insert(new Category(null, "日本酒", user));
		CategoryDao.insert(new Category(null, "その他", user));
	}

}
