package bean;

import java.util.Date;

public class Sakememo {
	
	private int sakememoId;
	private String sakememoName;
	private String sakememoComment;
	private int sakelogId;
	private boolean is_deleted;
	private Date ins_date;
	private Date upd_date;
	private Category category;
	private User user;
	
	
	public int getSakememoId() {
		return sakememoId;
	}
	public void setSakememoId(int sakememoId) {
		this.sakememoId = sakememoId;
	}
	public String getSakememoName() {
		return sakememoName;
	}
	public void setSakememoName(String sakememoName) {
		this.sakememoName = sakememoName;
	}
	public String getSakememoComment() {
		return sakememoComment;
	}
	public void setSakememoComment(String sakememoComment) {
		this.sakememoComment = sakememoComment;
	}
	public int getSakelogId() {
		return sakelogId;
	}
	public void setSakelogId(int sakelogId) {
		this.sakelogId = sakelogId;
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
