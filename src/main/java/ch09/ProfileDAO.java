package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {
	Connection conn = null;
	PreparedStatement pstmt;

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";

	public void open(){
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"root","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Profile p) {
		open();
		String sql = "INSERT INTO profile(username, email, title, pwd, context) values(?,?,?,?,?)";
		
		 try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, p.getUsername());
	            pstmt.setString(2, p.getEmail());
	            pstmt.setString(3, p.getTitle());
	            pstmt.setString(4, p.getPwd());;
	            pstmt.setString(5, p.getContext());
	            pstmt.executeUpdate();
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            close();
	        }
	    }
	
    public List<Profile> getAll() {
        open();
        List<Profile> Profiles = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from profile");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Profile p = new Profile();
                p.setId(rs.getInt("id"));
                p.setUsername(rs.getString("username"));
                p.setEmail(rs.getString("email"));
                p.setDate(rs.getDate("date"));
                p.setTitle(rs.getString("title"));

                Profiles.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return Profiles;
    }
}
