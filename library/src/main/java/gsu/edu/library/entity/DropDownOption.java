package gsu.edu.library.entity;

public class DropDownOption {

	private String value;
	private String displayMessage;
	
	public DropDownOption() {
	}
	public DropDownOption(String value, String displayMessage) {
		super();
		this.value = value;
		this.displayMessage = displayMessage;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}
		
}
