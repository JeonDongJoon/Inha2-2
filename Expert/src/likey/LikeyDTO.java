package likey;



public class LikeyDTO {



	String userID;

	int reviewID;

	String userIP;

	

	public String getUserID() {

		return userID;

	}

	public void setUserID(String userID) {

		this.userID = userID;

	}

	public int getReviewID() {

		return reviewID;

	}

	public void setReviewID(int reviewID) {

		this.reviewID = reviewID;

	}

	public String getUserIP() {

		return userIP;

	}

	public void setUserIP(String userIP) {

		this.userIP = userIP;

	}

	

	public LikeyDTO(String userID, int reviewID, String userIP) {

		this.userID = userID;

		this.reviewID = reviewID;

		this.userIP = userIP;

	}



}