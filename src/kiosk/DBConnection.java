package kiosk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver ������� -->"+e.getMessage());
		}
	}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId= "scott";
	String password="tiger";
	
	public DBConnection() {
	}
	//DB����
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(url,userId,password);
		} catch (SQLException e) {
			System.out.println("DB���� ����-->"+e.getMessage());
		}
	}
	public void getClose() {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println("DB�ݱ� ���� �߻�-->"+e.getMessage());
			}
	}
	
	
	//DB�ݱ�
	
	
}
