package miniProject;

public class userDTO {
	private String userId;
	private String userPwd;
	private String userEmail;
	private String userName;
	private String userGender;
	
	public userDTO() {
		
	}

	public userDTO(String userId, String userPwd, String userEmail, String userName, String userGender) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userGender = userGender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Override
	public String toString() {
		return "userDTO [userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail + ", userName="
				+ userName + ", userGender=" + userGender + "]";
	}
	
	
}
