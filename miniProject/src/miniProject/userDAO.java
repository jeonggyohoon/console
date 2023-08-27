package miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class userDAO {
	// 회원가입시 중복아이디 체크하는 Dao
	public int idCheck(String id) {
		int result = 0;
		String sql = "SELECT userId FROM usertable WHERE userId = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, pstmt, rs);
		}
		return result;
	}
	// 회원 등록 dao
	public int addUser(userDTO uDto) {
		int result = 0;
		String sql = "INSERT INTO usertable(userId,userPwd,userEmail,userName,userGender)VALUES(?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uDto.getUserId());
			pstmt.setString(2, uDto.getUserPwd());
			pstmt.setString(3, uDto.getUserEmail());
			pstmt.setString(4, uDto.getUserName());
			pstmt.setString(5, uDto.getUserGender());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(con, pstmt);
		}
		return result;
	}
	//로그인 Dao
	public int loginUser(String inputId,String inputPwd) {
		int result=-1;
		
		String sql = "SELECT userId, userPwd FROM usertable WHERE userId = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userPwd= rs.getString("userPwd");
				if(userPwd.equals(inputPwd)) {
					result=1;
				}else {
					result=0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, pstmt, rs);
			
		}
		return result;
	}
	//회원 탈퇴 dao
	public int delUser(String inputId, String inputPwd) {
		int result =0;
		String sql = "DELETE FROM usertable WHERE userId =(SELECT userId FROM usertable WHERE userId= ? AND userPwd= ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, pstmt);
		}
		return result;
	}
	//회원 정보 수정 update 문
	public int updateUser(userDTO uDto) {
		int result =0;
		String sql="UPDATE usertable SET userPwd =?, userName = ? userEmail=? WHERE userid=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uDto.getUserPwd());
			pstmt.setString(2, uDto.getUserName());
			pstmt.setString(3, uDto.getUserEmail());
			pstmt.setString(4, uDto.getUserId());
			
			result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("회원 정보 수정 성공");
			}else {
				System.out.println("회원 정보 수정 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, pstmt);
		}
		return result;
	}
	//회원 정보 수정 update 문에서 쓸 데이터를 select로 가져와서 dto에 저장후 어레이 리스트에 저장
	public ArrayList<userDTO> userData(String inputId) {
		ArrayList<userDTO> userList = new ArrayList<>();
		String sql="SELECT userId,userPwd,userName,userEmail FROM usertable WHERE userId=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DbManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userDTO uDto = new userDTO();
				uDto.setUserId(rs.getString("userId"));
				uDto.setUserPwd(rs.getString("userPwd"));
				uDto.setUserName(rs.getString("userName"));
				uDto.setUserEmail(rs.getString("userEmail"));
				
				userList.add(uDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(con, pstmt, rs);
		}
		return userList;
	}
}
