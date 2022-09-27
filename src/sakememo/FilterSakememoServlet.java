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
import validation.SakememoError;
import validation.SakememoValidation;

/**
 * Servlet implementation class FilterSakememoServlet <br>
 * 酒メモ絞り込み処理
 */
@WebServlet("/sakememo_filter")
public class FilterSakememoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterSakememoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/sakememo");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see SakememoDao
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索用にユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//結果格納用
		List<Sakememo> sakememoList =  new ArrayList<Sakememo>();
		SakememoError sakememoError = new SakememoError();
		
		//フォームで指定した絞り込み種別をもとに絞り込み
		String filterType = request.getParameter("filter_type");
		switch (filterType) {
			case "name":
				//フォームから検索条件を受け取り、バリデーション
				//結果をsakememoListに格納し、検索条件をリクエストに格納(フォームの値保持のため)
				//他の分岐も同様の処理
				String sakememoName = request.getParameter("sakememo_name");
				sakememoList = SakememoDao.findBySakememoName(userId, sakememoName);
				request.setAttribute("nameFilterValue", sakememoName);
				break;
			case "category":
				String categoryId = request.getParameter("category_id");
				sakememoError = SakememoValidation.validateFilterValue(categoryId);
				sakememoList = findByCategoryIdDependingOnError(userId, categoryId, sakememoError);
				request.setAttribute("categoryFilterValue", categoryId);
				break;
			case "ins_date":
				String insDateOld = request.getParameter("ins_date_old");
				String insDateNew = request.getParameter("ins_date_new");
				sakememoError = SakememoValidation.validateFilterInsDate(insDateOld, insDateNew);
				sakememoList = findByInsDateDependingOnError(userId, insDateOld, insDateNew, sakememoError);
				request.setAttribute("insDateOldFilterValue", insDateOld);
				request.setAttribute("insDateNewFilterValue", insDateNew);
		}
		
		//絞り込み結果、エラー、絞り込み種別をリクエストに格納
		//絞り込み種別を格納するのは、フォームの値保持のため
		request.setAttribute("sakememoList", sakememoList);
		request.setAttribute("sakememoError", sakememoError);
		request.setAttribute("filterType", filterType);
		
		//酒メモ画面にフォワード
		request.getRequestDispatcher("/jsp/sakememo/sakememo_info.jsp").forward(request, response);
	}
	
	
	//エラーがない場合はカテゴリIDから検索し、エラーがある場合は全件検索
	private List<Sakememo> findByCategoryIdDependingOnError(int userId, String categoryId, SakememoError sakememoError){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		if (sakememoError.isAllFieldNull()) {
			sakememoList = SakememoDao.findByCategoryId(userId, categoryId);
		} else {
			sakememoList = SakememoDao.findByUserIdInsDateDesc(userId);
		}
		return sakememoList;
	}
	
	//エラーがない場合は登録日から検索し、エラーがある場合は全件検索
	private List<Sakememo> findByInsDateDependingOnError(int userId, String insDateOld, String insDateNew, SakememoError sakememoError){
		List<Sakememo> sakememoList = new ArrayList<Sakememo>();
		if (sakememoError.isAllFieldNull()) {
			sakememoList = SakememoDao.findByInsDate(userId, insDateOld, insDateNew);
		} else {
			sakememoList = SakememoDao.findByUserIdInsDateDesc(userId);
		}
		return sakememoList;
	}
	
}
