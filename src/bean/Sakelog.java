package bean;

import java.util.Date;

public class Sakelog {
	
	private int sakelogId;
	private String sakelogName;
	private int rating;
	private String sakelogComment;
	private boolean is_deleted;
	private Date ins_date;
	private Date upd_date;
	private Category category;
	private User user;
	
	
	public int getSakelogId() {
		return sakelogId;
	}
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
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
	public String getSakelogComment() {
		return sakelogComment;
	}
	public void setSakelogComment(String sakelogComment) {
		this.sakelogComment = sakelogComment;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public Date getIns_date() {
		return ins_date;
	}
	public void setIns_date(Date ins_date) {
		this.ins_date = ins_date;
	}
	public Date getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(Date upd_date) {
		this.upd_date = upd_date;
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
