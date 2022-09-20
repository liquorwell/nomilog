package bean;

public class Category {

	private int categoryId;
	private String categoryName;
	private User user;
	
	
	public Category() {
		
	}
	
	public Category(String categoryId, String categoryName, User user) {
		this.setCategoryId(categoryId);
		this.setCategoryName(categoryName);
		this.setUser(user);
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryId(String strCategoryId) {
		if (strCategoryId == null) {
			this.categoryId = 0; 
		} else {
			this.categoryId = Integer.parseInt(strCategoryId);
		}
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
