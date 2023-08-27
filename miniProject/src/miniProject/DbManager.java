package miniProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Singleton 패턴 : 인스턴스를 하나만 만들어서 사용하는 방법
public class DbManager {

	// 인스턴스 주소를 저장하기 위한 참조 변수
	private static Connection conn;
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@172.30.1.7:1521:xe";
	private final static String USER = "scott";
	private final static String PASS = "1234";

	// 생성자 singleton 반드시 private로 막아야 한다.
	private DbManager() {

	}

	public static Connection getConnection() {
		conn = null;
		if (conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	// select를 수행한 후 리소스 해제를 위한 메소드
	// 매개변수 자리에 값이 다르면 다른걸로 인식한다.
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				try {
					rs.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// insert, update, delete를 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				try {
					pstmt.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
