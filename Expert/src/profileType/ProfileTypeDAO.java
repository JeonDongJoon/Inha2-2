package profileType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfileTypeDAO {

	private Connection conn;
	private ResultSet rs;
	
	//�⺻ ������
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

	//�ۼ����� �޼ҵ�
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
		return ""; //�����ͺ��̽� ����
	}
	
	//�Խñ� ��ȣ �ο� �޼ҵ�
	public int getNext() {
		//���� �Խñ��� ������������ ��ȸ�Ͽ� ���� ������ ���� ��ȣ�� ���Ѵ�
		String sql = "select profileID from profileType order by profileID desc";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; //ù ��° �Խù��� ���
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ����
	}
	//�۾��� �޼ҵ�
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
	
	//�Խñ� ����Ʈ �޼ҵ�
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
	
	//����¡ ó�� �޼ҵ�
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
	
	//�ϳ��� �Խñ��� ���� �޼ҵ�
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
	
	//�Խñ� ���� �޼ҵ�
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
		return -1; //�����ͺ��̽� ����
	}
	
	//�Խñ� ���� �޼ҵ�
	public int delete(int profileID) {
		//���� �����͸� �����ϴ� ���� �ƴ϶� �Խñ� ��ȿ���ڸ� '0'���� �����Ѵ�
		String sql = "update profileType set profileAvailable = 0 where profileID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, profileID);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //�����ͺ��̽� ���� 
	}
	
}