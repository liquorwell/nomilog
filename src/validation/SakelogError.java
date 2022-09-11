package validation;

public class SakelogError {

	private String filterErrorMessage;

	public String getFilterErrorMessage() {
		return filterErrorMessage;
	}

	public void setFilterErrorMessage(String filterErrorMessage) {
		this.filterErrorMessage = filterErrorMessage;
	}
	
	public boolean isAllFieldNull() {
		return (this.getFilterErrorMessage() == null);
	}
	
}
