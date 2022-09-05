package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import validation.UserError;
import validation.UserValidation;

/**
 * Servlet implementation class UpdateUserNameServlet
 */
@WebServlet("/user_name_update")
public class UpdateUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserNameServlet() {
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
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		String userName = request.getParameter("user_name");
		UserError userError = UserValidation.updateUserNameValidation(userName, userId);
		if (userError != null) {
			request.setAttribute("userError", userError);
			request.getRequestDispatcher("jsp/user/user_name_update.jsp").forward(request, response);
			return;
		}
		
		UserDao.updateUserName(userId, userName);
		
		user = UserDao.findByUserId(userId);
		session.setAttribute("user", user);
		
		request.getRequestDispatcher("/jsp/user/user_info.jsp").forward(request, response);
	}

}
