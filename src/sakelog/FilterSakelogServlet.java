package sakelog;

import java.io.IOException;
import java.util.ArrayList;
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
import validation.SakelogError;
import validation.SakelogValidation;

/**
 * Servlet implementation class FilterSakelogServlet <br>
 * 酒ログ絞り込み処理
 */
@WebServlet("/sakelog_filter")
public class FilterSakelogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterSakelogServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/sakelog");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @see SakelogDao
	 * @see SakelogValidation
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//検索用にユーザーIDを入手
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		//結果格納用
		List<Sakelog> sakelogList =  new ArrayList<Sakelog>();
		SakelogError sakelogError = new SakelogError();
		
		//フォームで指定した絞り込み種別をもとに絞り込み
		String filterType = request.getParameter("filter_type");
		switch (filterType) {
			case "name":
				//フォームから検索条件を受け取り、バリデーション
				//結果をsakelogListに格納し、検索条件をリクエストに格納(フォームの値保持のため)
				//他の分岐も同様の処理
				String sakelogName = request.getParameter("sakelog_name");
				sakelogList = SakelogDao.findBySakelogName(userId, sakelogName);
				request.setAttribute("nameFilterValue", sakelogName);
				break;
			case "category":
				String categoryId = request.getParameter("category_id");
				sakelogError = SakelogValidation.validateFilterValue(categoryId);
				sakelogList = findByCategoryIdDependingOnError(userId, categoryId, sakelogError);
				request.setAttribute("categoryFilterValue", categoryId);
				break;
			case "rating":
				String rating = request.getParameter("rating");
				sakelogError = SakelogValidation.validateFilterValue(rating);
				sakelogList = findByRatingDependingOnError(userId, rating, sakelogError);
				request.setAttribute("ratingFilterValue", rating);
				break;
			case "ins_date":
				String insDateOld = request.getParameter("ins_date_old");
				String insDateNew = request.getParameter("ins_date_new");
				sakelogError = SakelogValidation.validateFilterInsDate(insDateOld, insDateNew);
				sakelogList = findByInsDateDependingOnError(userId, insDateOld, insDateNew, sakelogError);
				request.setAttribute("insDateOldFilterValue", insDateOld);
				request.setAttribute("insDateNewFilterValue", insDateNew);
				break;
		}
		
		//絞り込み結果、エラー、絞り込み種別をリクエストに格納
		//絞り込み種別を格納するのは、フォームの値保持のため
		request.setAttribute("sakelogList", sakelogList);
		request.setAttribute("sakelogError", sakelogError);
		request.setAttribute("filterType", filterType);
		
		//酒ログ画面にフォワード
		request.getRequestDispatcher("/jsp/sakelog/sakelog_info.jsp").forward(request, response);
	}
	
	
	//エラーがない場合はカテゴリIDから検索し、エラーがある場合は全件検索
	private List<Sakelog> findByCategoryIdDependingOnError(int userId, String categoryId, SakelogError sakelogError){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		if (sakelogError == null) {
			sakelogList = SakelogDao.findByCategoryId(userId, categoryId);
		} else {
			sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
		}
		return sakelogList;
	}
	
	//エラーがない場合は評価から検索し、エラーがある場合は全件検索
	private List<Sakelog> findByRatingDependingOnError(int userId, String rating, SakelogError sakelogError){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		if (sakelogError == null) {
			sakelogList = SakelogDao.findByRating(userId, rating);
		} else {
			sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
		}
		return sakelogList;
	}
	
	//エラーがない場合は登録日から検索し、エラーがある場合は全件検索
	private List<Sakelog> findByInsDateDependingOnError(int userId, String insDateOld, String insDateNew, SakelogError sakelogError){
		List<Sakelog> sakelogList = new ArrayList<Sakelog>();
		if (sakelogError == null) {
			sakelogList = SakelogDao.findByInsDate(userId, insDateOld, insDateNew);
		} else {
			sakelogList = SakelogDao.findByUserIdInsDateDesc(userId);
		}
		return sakelogList;
	}

}
