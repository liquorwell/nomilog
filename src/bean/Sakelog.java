package bean;

public class Sakelog {
	
	private int sakelogId;
	private String sakelogName;
	private int rating;
	private String sakelogComment;
	private Category category;
	private User user;
	
	
	public Sakelog() {
		
	}
	
	public Sakelog(String sakelogId, String sakelogName, String rating, String sakelogComment, Category category, User user) {
		this.setSakelogId(sakelogId);
		this.setSakelogName(sakelogName);
		this.setRating(rating);
		this.setSakelogComment(sakelogComment);
		this.setCategory(category);
		this.setUser(user);
	}
	
	public Sakelog(Sakememo sakememo) {
		this.setSakelogName(sakememo.getSakememoName());
		this.setSakelogComment(sakememo.getSakememoComment());
		this.setCategory(sakememo.getCategory());
		this.setUser(sakememo.getUser());
	}
	
	
	public int getSakelogId() {
		return sakelogId;
	}
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
	}
	public void setSakelogId(String sakelogId) {
		if (sakelogId == null) {
			this.sakelogId = 0;
		} else {
			this.sakelogId = Integer.parseInt(sakelogId);
		}
	}
	public String getSakelogName() {
		return sakelogName;
	}
	public void setSakelogName(String sakelogName) {
		this.sakelogName = sakelogName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setRating(String rating) {
		if (rating == null) {
			this.rating = 0;
		} else {
			this.rating = Integer.parseInt(rating);
		}
	}
	public String getSakelogComment() {
		return sakelogComment;
	}
	public void setSakelogComment(String sakelogComment) {
		this.sakelogComment = sakelogComment;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
