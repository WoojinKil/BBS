package user;

import java.sql.*;

public class UserDAO {

	 Connection conn ;
	PreparedStatement pstmt;
	 ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?characterEncoding=utf-8&serverTimezone=UTC&user=root&password=7729";

			Class.forName("com.mysql.jdbc.Driver"); //매개체 역할
			conn = DriverManager.getConnection(dbURL);
			//conn에 접속된 정보가 들어감
		System.out.println("연결성공");	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "Select userPassword from USER where userID =?";
	
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, userID);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)){
					return 1; //아이디 일치
				}else
					return 0; // 비밀번호 틀림
			}
			return -1; //아이디가 없으면
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //db 오류
	}

	public int join(User user) {
		String SQL = "insert into USER values(?,?,?,?,?);";
		
		try {
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
