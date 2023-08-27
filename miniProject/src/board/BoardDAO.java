package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import miniProject.DbManager;


public class BoardDAO {

	// list 객체 선언
	ArrayList<BoardDTO> boardlist = null;

	// board table 등록 메서드
	public int insert(BoardDTO bDto) {
		int result = 0;

		String sql = "INSERT INTO board(boardNo, boardTitle, boardContent, userId, creatDate) "
				   + "VALUES(boardno_seq.NEXTVAL, ?, ?, ?, sysdate)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDto.getBoardTitle());
			pstmt.setString(2, bDto.getBoardContent());
			pstmt.setString(3, bDto.getUserId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt);

		}
		return result;
	}

	// board table 수정 메서드
	public int correct(BoardDTO bDto) {

		int result = 0;

		String sql = "UPDATE board " 
		           + "SET boardTitle = ?, boardContent = ? " 
				   + "WHERE boardNo = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDto.getBoardTitle());
			pstmt.setString(2, bDto.getBoardContent());
			pstmt.setString(3, bDto.getBoardNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// board table 삭제 메서드
	public int delete(BoardDTO bDto) {
		int result = 0;

		String sql = "DELETE FROM board " 
		           + "WHERE boardNo = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDto.getBoardNo());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	// board, user table match 메서드
		public int correctMatch(String correctNo, String userId) {
			int result = -1;

			String sql = "SELECT userId " 
			           + "FROM board " 
					   + "WHERE boardNo = ?";

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				conn = DbManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, correctNo);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					result = 0;
					String dbUserId = rs.getString("userId");

					if (dbUserId.equals(userId)) {
						result = 1;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbManager.close(conn, pstmt, rs);
			}
			return result;
		}
		// board, user table match 메서드
		public int deleteMatch(String deleteNo, String userId) {
			int result = -1;
			
			String sql = "SELECT userId " 
					+ "FROM board " 
					+ "WHERE boardNo = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DbManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, deleteNo);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = 0;
					String dbUserId = rs.getString("userId");
					
					if (dbUserId.equals(userId)) {
						result = 1;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbManager.close(conn, pstmt, rs);
			}
			return result;
		}

	// board table 조회 메서드
	public ArrayList<BoardDTO> search(String keyWord) {

		boardlist = new ArrayList<BoardDTO>();

		String sql = "SELECT boardNo, " 
		           + "SUBSTR(boardTitle, 1, 17) boardTitle, "
				   + "SUBSTR(boardContent, 1, 10) boardContent, " 
		           + "userId, " 
				   + "SUBSTR(creatDate, 1, 10) creatDate "
				   + "FROM board " 
				   + "WHERE boardTitle like ? " 
				   + "ORDER BY boardNo";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyWord + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO bDto = new BoardDTO();
				bDto.setBoardNo(rs.getString("boardNo"));
				bDto.setBoardTitle(rs.getString("boardTitle"));
				bDto.setBoardContent(rs.getString("boardContent"));
				bDto.setUserId(rs.getString("userId"));
				bDto.setCreatDate(rs.getString("creatDate"));

				boardlist.add(bDto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return boardlist;
	}
	// board table 조회 메서드
	public ArrayList<BoardDTO> search1(String userId) {

		boardlist = new ArrayList<BoardDTO>();

		String sql = "SELECT boardNo, " 
		           + "SUBSTR(boardTitle, 1, 17) boardTitle, "
				   + "SUBSTR(boardContent, 1, 10) boardContent, " 
		           + "userId, " 
				   + "SUBSTR(creatDate, 1, 10) creatDate "
				   + "FROM board " 
				   + "WHERE userId = ? " 
				   + "ORDER BY boardNo";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO bDto = new BoardDTO();
				bDto.setBoardNo(rs.getString("boardNo"));
				bDto.setBoardTitle(rs.getString("boardTitle"));
				bDto.setBoardContent(rs.getString("boardContent"));
				bDto.setUserId(rs.getString("userId"));
				bDto.setCreatDate(rs.getString("creatDate"));

				boardlist.add(bDto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return boardlist;
	}
	// board table 상세 조회 메서드
	public ArrayList<BoardDTO> detail(String inputNo) {

		boardlist = new ArrayList<>();

		String sql = "SELECT * " 
		           + "FROM board " 
				   + "WHERE boardno = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO bDto = new BoardDTO();
				bDto.setBoardNo(rs.getString("boardNo"));
				bDto.setBoardTitle(rs.getString("boardTitle"));
				bDto.setBoardContent(rs.getString("boardContent"));
				bDto.setUserId(rs.getString("userId"));
				bDto.setCreatDate(rs.getString("creatDate"));
				boardlist.add(bDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return boardlist;

	}

}
