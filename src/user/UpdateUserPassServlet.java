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

/**
 * Servlet implementation class UpdateUserPassServlet
 */
@WebServlet("/user_pass_update")
public class UpdateUserPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserPassServlet() {
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
		String userPass = user.getUserPass();
		
		String currPass = request.getParameter("curr_pass");
		String newPass = request.getParameter("new_pass");
		String checkPass = request.getParameter("check_pass");
		
		String errorMessage = null;
		if (!currPass.equals(userPass)) {
			errorMessage = "現在のパスワードが正しくありません。";
		}
		if (!newPass.equals(checkPass)) {
			errorMessage = "新しいパスワードと確認用パスワードが違います。";
		}
		
		if (errorMessage == null) {
			UserDao.updateUserPass(userId, newPass);
			request.getRequestDispatcher("/jsp/user/user_info.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/jsp/user/user_pass_update.jsp").forward(request, response);
		}
	}

}
