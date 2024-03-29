package sakelog;

import java.io.IOException;
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
 * Servlet implementation class TransitionToSakelogInfoServlet <br>
 * 酒ログ画面遷移処理
 */
@WebServlet("/sakelog")
public class TransitionToSakelogInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToSakelogInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see SakelogDao#findByUserIdInsDateDesc(int userId)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしているユーザーから酒ログを検索し、結果をリクエストに格納
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Sakelog> sakelogList = SakelogDao.findByUserIdInsDateDesc(user.getUserId());
		request.setAttribute("sakelogList", sakelogList);
		
		//酒ログ画面にフォワード
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
