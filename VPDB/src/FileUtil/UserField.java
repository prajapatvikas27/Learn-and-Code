package FileUtil;

/*
 * This class is used for creating field object while performing crud operations
 * using fields as arguments*/
public class UserField {
	
	public String userFieldName;
	public String userFieldValue;
	
	
	
	public UserField() {
	}

	public UserField(String userFieldName, String userFieldValue) {
		super();
		this.userFieldName = userFieldName;
		this.userFieldValue = userFieldValue;
	}

	public String getUserFieldName() {
		return userFieldName;
	}
	
	public void setUserFieldName(String userFieldName) {
		this.userFieldName = userFieldName;
	}
	
	public String getUserFieldValue() {
		return userFieldValue;
	}
	
	public void setUserFieldValue(String userFieldValue) {
		this.userFieldValue = userFieldValue;
	}
	
	

}
