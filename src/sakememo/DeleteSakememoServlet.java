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
 * Servlet implementation class DeleteSakememoServlet
 */
@WebServlet("/sakememo_delete")
public class DeleteSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSakememoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sakememoId = request.getParameter("sakememo_id");
		SakememoDao.delete(sakememoId);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		List<Sakememo> sakememoList = SakememoDao.findByUserIdInsDateDesc(user.getUserId());
		request.setAttribute("sakememoList", sakememoList);
		
		request.getRequestDispatcher("jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
