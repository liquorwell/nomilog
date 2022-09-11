package validation;

public class UserError {

	private String userNameErrorMessage;
	private String userPassErrorMessage;
	private String userNewPassErrorMessage;
	private String userExistErrorMessage;
	
	public String getUserNameErrorMessage() {
		return userNameErrorMessage;
	}
	public void setUserNameErrorMessage(String userNameErrorMessage) {
		this.userNameErrorMessage = userNameErrorMessage;
	}
	public String getUserPassErrorMessage() {
		return userPassErrorMessage;
	}
	public void setUserPassErrorMessage(String userPassErrorMessage) {
		this.userPassErrorMessage = userPassErrorMessage;
	}
	public String getUserNewPassErrorMessage() {
		return userNewPassErrorMessage;
	}
	public void setUserNewPassErrorMessage(String userNewPassErrorMessage) {
		this.userNewPassErrorMessage = userNewPassErrorMessage;
	}
	public String getUserExistErrorMessage() {
		return userExistErrorMessage;
	}
	public void setUserExistErrorMessage(String userExistErrorMessage) {
		this.userExistErrorMessage = userExistErrorMessage;
	}
	
	public boolean isAllFieldNull() {
		return (this.getUserNameErrorMessage() == null && this.getUserPassErrorMessage() == null && 
				this.getUserNewPassErrorMessage() == null && this.getUserExistErrorMessage() == null);
	}
	
	public boolean isNameAndPassErrorNull() {
		return (this.getUserNameErrorMessage() == null && this.getUserPassErrorMessage() == null);
	}
	
}
