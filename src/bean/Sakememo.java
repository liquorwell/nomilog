package bean;

public class Sakememo {
	
	private int sakememoId;
	private String sakememoName;
	private String sakememoComment;
	private int sakelogId;
	private Category category;
	private User user;
	
	
	public int getSakememoId() {
		return sakememoId;
	}
	public void setSakememoId(int sakememoId) {
		this.sakememoId = sakememoId;
	}
	public void setSakememoId(String strSakememoId) {
		this.sakememoId = Integer.parseInt(strSakememoId);
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
