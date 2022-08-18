package bean;

import java.util.Date;

public class Sakememo {
	
	private int sakememoId;
	private String sakememoName;
	private String sakememoComment;
	private int sakelogId;
	private boolean isDeleted;
	private Date insDate;
	private Date updDate;
	private Category category;
	private User user;
	
	
	public int getSakememoId() {
		return sakememoId;
	}
	public void setSakememoId(int sakememoId) {
		this.sakememoId = sakememoId;
	}
	public void setSakememoId(String strSakememoId) {
		Integer sakememoId = Integer.parseInt(strSakememoId);
		setSakememoId(sakememoId);
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
