package bean;

public class Sakememo {
	
	private int sakememoId;
	private String sakememoName;
	private String sakememoComment;
	private int sakelogId;
	private Category category;
	private User user;
	
	
	public Sakememo() {
		
	}
	
	public Sakememo(String sakememoId, String sakememoName, String sakememoComment, String sakelogId, Category category, User user) {
		this.setSakememoId(sakememoId);
		this.setSakememoName(sakememoName);
		this.setSakememoComment(sakememoComment);
		this.setSakelogId(sakelogId);
		this.setCategory(category);
		this.setUser(user);
	}
	
	
	public int getSakememoId() {
		return sakememoId;
	}
	
	public void setSakememoId(int sakememoId) {
		this.sakememoId = sakememoId;
	}
	public void setSakememoId(String strSakememoId) {
		if (strSakememoId == null) {
			this.sakememoId = 0;
		} else {
			this.sakememoId = Integer.parseInt(strSakememoId);
		}
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
	public void setSakelogId(String strSakelogId) {
		if(strSakelogId == null) {
			this.sakelogId = 0;
		} else {
			this.sakelogId = Integer.parseInt(strSakelogId);
		}
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
