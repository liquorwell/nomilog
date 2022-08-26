package bean;

import java.util.Date;

public class Sakelog {
	
	private int sakelogId;
	private String sakelogName;
	private int rating;
	private String sakelogComment;
	private Date insDate;
	private Date updDate;
	private Category category;
	private User user;
	
	
	public int getSakelogId() {
		return sakelogId;
	}
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
	}
	public void setSakelogId(String sakelogId) {
		this.sakelogId = Integer.parseInt(sakelogId);
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
		this.rating = Integer.parseInt(rating);
	}
	public String getSakelogComment() {
		return sakelogComment;
	}
	public void setSakelogComment(String sakelogComment) {
		this.sakelogComment = sakelogComment;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
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
