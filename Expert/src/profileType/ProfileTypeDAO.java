package profileType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfileTypeDAO {

	private Connection conn;
	private ResultSet rs;
	
	//기본 생성자
	public ProfileTypeDAO() {
		try {

				String dbURL = "jdbc:mysql://localhost:3306/expertChat";

				String dbID = "root";

				String dbPassword = "wjs0912";

				Class.forName("com.mysql.cj.jdbc.Driver");

				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

			} catch (Exception e) {

				e.printStackTrace();

			}
	}

	//작성일자 메소드
	public String getDate() {
		String sql = "select now()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류
	}
	
	//게시글 번호 부여 메소드
	public int getNext() {
		//현재 게시글을 내림차순으로 조회하여 가장 마지막 글의 번호를 구한다
		String sql = "select profileID from profileType order by profileID desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; //첫 번째 게시물인 경우
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	//글쓰기 메소드
	public int write(String profileTitle, String userID, String profileContent) {
		String SQL = "INSERT INTO profileType VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, profileTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, profileContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
	//게시글 리스트 메소드
	public ArrayList<ProfileTypeDTO> getList(int pageNumber){
		String sql = "select * from profileType where profileID < ? and profileAvailable = 1 order by profileID desc limit 10";
		ArrayList<ProfileTypeDTO> list = new ArrayList<ProfileTypeDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProfileTypeDTO profile = new ProfileTypeDTO();
				profile.setProfileID(rs.getInt(1));
				profile.setProfileTitle(rs.getString(2));
				profile.setUserID(rs.getString(3));
				profile.setProfileDate(rs.getString(4));
				profile.setProfileContent(rs.getString(5));
				profile.setProfileAvailable(rs.getInt(6));
				list.add(profile);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//페이징 처리 메소드
	public boolean nextPage(int pageNumber) {
		String sql = "select * from profileType where profileID < ? and profileAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//하나의 게시글을 보는 메소드
	public ProfileTypeDTO getProfileDTO(int profileID) {
		String sql = "select * from profileType where profileID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ProfileTypeDTO profile = new ProfileTypeDTO();
				profile.setProfileID(rs.getInt(1));
				profile.setProfileTitle(rs.getString(2));
				profile.setUserID(rs.getString(3));
				profile.setProfileDate(rs.getString(4));
				profile.setProfileContent(rs.getString(5));
				profile.setProfileAvailable(rs.getInt(6));
				return profile;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//게시글 수정 메소드
	public int update(int profileID, String profileTitle, String profileContent) {
		String sql = "update profile set profileTitle = ?, profileContent = ? where profileID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profileTitle);
			pstmt.setString(2, profileContent);
			pstmt.setInt(3, profileID);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	//게시글 삭제 메소드
	public int delete(int profileID) {
		//실제 데이터를 삭제하는 것이 아니라 게시글 유효숫자를 '0'으로 수정한다
		String sql = "update profileType set profileAvailable = 0 where profileID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileID);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류 
	}
	
}