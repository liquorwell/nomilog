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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/user_delete")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userPass = request.getParameter("user_pass");		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		String checkPass = user.getUserPass();
		
		if(userPass.equals(checkPass)) {
			UserDao.delete(userId);
			response.sendRedirect("/nomilog");
		} else {
			String errorMessage = "パスワードが違います。";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/jsp/user/user_delete_check.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
