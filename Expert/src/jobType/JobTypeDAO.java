package jobType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JobTypeDAO {
	
	
	private Connection conn;

	private ResultSet rs;



	public JobTypeDAO() {

		try {

			String dbURL = "jdbc:mysql://localhost:3306/expertchat";

			String dbID = "root";

			String dbPassword = "wjs0912";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	public ArrayList<JobTypeDTO> getList(){
		String sql = "select * from expertjob";
		ArrayList<JobTypeDTO> list = new ArrayList<JobTypeDTO>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JobTypeDTO jobTypeDTO = new JobTypeDTO();
				jobTypeDTO.setJobType(rs.getString(1));
				list.add(jobTypeDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
