package bean;

public class User {
	
	private int userId;
	private String userName;
	private String userPass;
	
	
	public User() {
		
	}
	
	public User(String userId, String userName, String userPass) {
		this.setUserId(userId);
		this.setUserName(userName);
		this.setUserPass(userPass);
	}
	
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUserId(String strUserId) {
		if(strUserId == null) {
			this.userId = 0;
		} else {
			this.userId = Integer.parseInt(strUserId);
		}
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
}
