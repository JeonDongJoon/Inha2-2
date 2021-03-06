package profileType;

public class ProfileTypeDTO {
	private int profileID;
	private String profileTitle;
	private String userID;
	private String profileDate;
	private String profileContent;
	private int profileAvailable;
	public int getProfileID() {
		return profileID;
	}
	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}
	public String getProfileTitle() {
		return profileTitle;
	}
	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProfileDate() {
		return profileDate;
	}
	public void setProfileDate(String profileDate) {
		this.profileDate = profileDate;
	}
	public String getProfileContent() {
		return profileContent;
	}
	public void setProfileContent(String profileContent) {
		this.profileContent = profileContent;
	}
	public int getProfileAvailable() {
		return profileAvailable;
	}
	public void setProfileAvailable(int profileAvailable) {
		this.profileAvailable = profileAvailable;
	}
}