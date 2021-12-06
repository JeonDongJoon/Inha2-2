package review;



public class ReviewDTO {



	int reviewID;

	String userID;

	String expertName;

	String timeDivide;

	String reviewDivide;

	String reviewTitle;

	String reviewContent;

	String totalScore;

	String timeScore;

	String comfortableScore;

	String reviewScore;

	int likeCount;

	

	public int getReviewID() {

		return reviewID;

	}

	public void setReviewID(int reviewID) {

		this.reviewID = reviewID;

	}

	public String getUserID() {

		return userID;

	}

	public void setUserID(String userID) {

		this.userID = userID;

	}

	

	public String getExpertName() {

		return expertName;

	}

	public void setExpertName(String expertName) {

		this.expertName = expertName;

	}

	
	public String getTimeDivide() {

		return timeDivide;

	}

	public void setTimeDivide(String timeDivide) {

		this.timeDivide = timeDivide;

	}

	public String getReviewDivide() {

		return reviewDivide;

	}

	public void setReviewDivide(String reviewDivide) {

		this.reviewDivide = reviewDivide;

	}

	public String getReviewTitle() {

		return reviewTitle;

	}

	public void setReviewTitle(String reviewTitle) {

		this.reviewTitle = reviewTitle;

	}

	public String getReviewContent() {

		return reviewContent;

	}

	public void setReviewContent(String reviewContent) {

		this.reviewContent = reviewContent;

	}

	public String getTotalScore() {

		return totalScore;

	}

	public void setTotalScore(String totalScore) {

		this.totalScore = totalScore;

	}

	public String getTimeScore() {

		return timeScore;

	}

	public void setTimeScore(String timeScore) {

		this.timeScore = timeScore;

	}

	public String getComfortableScore() {

		return comfortableScore;

	}

	public void setComfortableScore(String comfortableScore) {

		this.comfortableScore = comfortableScore;

	}

	public String getReviewScore() {

		return reviewScore;

	}

	public void setReviewScore(String reviewScore) {

		this.reviewScore = reviewScore;

	}

	public int getLikeCount() {

		return likeCount;

	}

	public void setLikeCount(int likeCount) {

		this.likeCount = likeCount;

	}

	

	public ReviewDTO(int reviewID, String userID, String expertName,

			String timeDivide, String reviewDivide, String reviewTitle, String reviewContent,

			String totalScore, String timeScore, String comfortableScore, String reviewScore, int likeCount) {

		super();

		this.reviewID = reviewID;

		this.userID = userID;

		this.expertName = expertName;

		this.timeDivide = timeDivide;

		this.reviewDivide = reviewDivide;

		this.reviewTitle = reviewTitle;

		this.reviewContent = reviewContent;

		this.totalScore = totalScore;

		this.timeScore = timeScore;

		this.comfortableScore = comfortableScore;

		this.reviewScore = reviewScore;

		this.likeCount = likeCount;

	}

	

}