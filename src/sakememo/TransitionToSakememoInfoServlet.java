package sakememo;

import java.io.IOException;
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
 * Servlet implementation class TransitionToSakememoInfoServlet <br>
 * 酒メモ画面遷移処理
 */
@WebServlet("/sakememo")
public class TransitionToSakememoInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransitionToSakememoInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see SakememoDao#findByUserIdInsDateDesc(int userId)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインしているユーザーから酒メモを検索し、結果をリクエストに格納
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Sakememo> sakememoList = SakememoDao.findByUserIdInsDateDesc(user.getUserId());
		request.setAttribute("sakememoList", sakememoList);
		
		//酒メモ画面にフォワード
		request.getRequestDispatcher("/jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
