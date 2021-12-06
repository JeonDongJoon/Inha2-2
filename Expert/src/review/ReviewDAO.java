package review;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;



public class ReviewDAO {



	private Connection conn;

	private ResultSet rs;



	public ReviewDAO() {

		try {

			String dbURL = "jdbc:mysql://localhost:3306/expertChat";

			String dbID = "root";

			String dbPassword = "wjs0912";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	

	public int write(ReviewDTO reviewDTO) {

		PreparedStatement pstmt = null;

		try {

			String SQL = "INSERT INTO REVIEW VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0);";

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, reviewDTO.getUserID());

			pstmt.setString(2, reviewDTO.getExpertName());

			pstmt.setString(3, reviewDTO.getTimeDivide());

			pstmt.setString(4, reviewDTO.getReviewDivide());

			pstmt.setString(5, reviewDTO.getReviewTitle());

			pstmt.setString(6, reviewDTO.getReviewContent());

			pstmt.setString(7, reviewDTO.getTotalScore());

			pstmt.setString(8, reviewDTO.getTimeScore());

			pstmt.setString(9, reviewDTO.getComfortableScore());

			pstmt.setString(10, reviewDTO.getReviewScore());

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return -1;

	}
	
	public ArrayList<ReviewDTO> getList(String reviewDivide, String searchType, String search, int pageNumber) {

		if(reviewDivide.equals("전체")) {

			reviewDivide = "";

		}

		ArrayList<ReviewDTO> reviewList = null;

		PreparedStatement pstmt = null;

		String SQL = "";

		try {

			if(searchType.equals("최신순")) {

				SQL = "SELECT * FROM REVIEW WHERE reviewDivide LIKE ? AND CONCAT(expertName, reviewTitle, reviewContent) LIKE ? ORDER BY reviewID DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;

			} else if(searchType.equals("추천순")) {

				SQL = "SELECT * FROM REVIEW WHERE reviewDivide LIKE ? AND CONCAT(expertName, reviewTitle, reviewContent) LIKE ? ORDER BY likeCount DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;

			}

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, "%" + reviewDivide + "%");

			pstmt.setString(2, "%" + search + "%");

			rs = pstmt.executeQuery();

			reviewList = new ArrayList<ReviewDTO>();

			while(rs.next()) {

				ReviewDTO review = new ReviewDTO(

					rs.getInt(1),

					rs.getString(2),

					rs.getString(3),

					rs.getString(4),

					rs.getString(5),

					rs.getString(6),

					rs.getString(7),

					rs.getString(8),

					rs.getString(9),

					rs.getString(10),

					rs.getString(11),

					rs.getInt(12)

				);

				reviewList.add(review);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(rs != null) rs.close();

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return reviewList;

	}
	public int like(String reviewID) {

		PreparedStatement pstmt = null;

		try {

			String SQL = "UPDATE REVIEW SET likeCount = likeCount + 1 WHERE reviewID = ?";

			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, Integer.parseInt(reviewID));

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return -1;

	}

	

	public int delete(String reviewID) {

		PreparedStatement pstmt = null;

		try {

			String SQL = "DELETE FROM REVIEW WHERE reviewID = ?";

			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, Integer.parseInt(reviewID));

			return pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return -1;

	}

	

	public String getUserID(String reviewID) {

		PreparedStatement pstmt = null;

		try {

			String SQL = "SELECT userID FROM REVIEW WHERE reviewID = ?";

			pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, Integer.parseInt(reviewID));

			rs = pstmt.executeQuery();

			while(rs.next()) {

				return rs.getString(1);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if(pstmt != null) pstmt.close();

				if(conn != null) conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return null;

	}
}