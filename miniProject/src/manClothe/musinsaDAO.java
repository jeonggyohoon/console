package manClothe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import miniProject.DbManager;


public class musinsaDAO {
	musinsaDTO mDto = new musinsaDTO();

	// --------------------------------------카테고리
	// 조회-------------------------------------------
	// 메인에서 숫자입력 - > 그 숫자에 맞는 카테고리 문자열 변수에 저장하는 조건문 걸어서 매개변수로 받음
	public ArrayList<musinsaDTO> aList(String inputClothType) {
		ArrayList<musinsaDTO> clothList = new ArrayList<>();

		// 카테고리 1만 불러오는 sql문
		String sql = "SELECT * " + "FROM clothData " + "WHERE clothtype = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputClothType);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				musinsaDTO mDto = new musinsaDTO();
				mDto.setClothNum(rs.getString("clothNum"));
				mDto.setClothName(rs.getString("clothName"));
				mDto.setPrice(rs.getInt("price"));
				mDto.setClothSize(rs.getString("clothSize"));
				mDto.setClothType(rs.getString("clothType"));
				mDto.setClothType2(rs.getString("clothType2"));

				clothList.add(mDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return clothList;

	}

	// -------------------------------------상품 등록
	// 메소드---------------------------------------------
	public int insertProduct(musinsaDTO mDto) {
		int result = 0;
		String sql = "INSERT INTO clothData " + "VALUES (?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mDto.getClothNum());
			pstmt.setString(2, mDto.getClothName());
			pstmt.setInt(3, mDto.getPrice());
			pstmt.setString(4, mDto.getClothSize());
			pstmt.setString(5, mDto.getClothType());
			pstmt.setString(6, mDto.getClothType2());
			result = pstmt.executeUpdate();

			if (result > 0) {

				result = 1;// 메인에서 메소드 불러와서 if문 돌려서 1이면 회원등록 성공, (상품목록 출력? 은 선택)
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt);
		}

		return result; // 등록 성공하면 1 아니면 0 을 돌려준다
	}

	// 관리자 상품 수정 / 삭제할 때 이름, 번호 조회할 메서드
	public ArrayList<musinsaDTO> productNumName(String inputProNum, String inputProName) {
		musinsaDTO mDto = new musinsaDTO();
		ArrayList<musinsaDTO> clothInfoList = new ArrayList<>();

		String sql = "SELECT * FROM clothdata WHERE clothnum=? AND clothname = ?";

//				"SELECT *"
//						   + "FROM clothdata "
//						   + "WHERE clothnum = ? "
//						   + "AND   clothname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputProNum);
			pstmt.setString(2, inputProName);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				mDto.setClothNum(rs.getString("clothnum"));
				mDto.setClothName(rs.getString("clothname"));
				mDto.setPrice(rs.getInt("price"));
				mDto.setClothSize(rs.getString("clothsize"));
				mDto.setClothType(rs.getString("clothtype"));
				mDto.setClothType2(rs.getString("clothtype2"));
				clothInfoList.add(mDto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return clothInfoList;

	}

	// 상품번호 조회하기 전 목록 보여주기
	public ArrayList<musinsaDTO> productNumName2() {
		ArrayList<musinsaDTO> clothInfoList = new ArrayList<>();

		String sql = "SELECT clothnum, clothname FROM clothdata";

//				"SELECT *"3
//						   + "FROM clothdata "
//						   + "WHERE clothnum = ? "
//						   + "AND   clothname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				musinsaDTO mDto = new musinsaDTO();
				mDto.setClothNum(rs.getString("clothnum"));
				mDto.setClothName(rs.getString("clothname"));

				clothInfoList.add(mDto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbManager.close(conn, pstmt, rs);
		}

		return clothInfoList;

	}

	// 상품 수정 메소드
	public void productUpdate(musinsaDTO mDto, String inputNum, String inputName) {
		int result = 0;
		String sql = "UPDATE clothdata SET clothnum = ?, clothname = ?, price =?, clothsize = ?, clothtype = ?, clothtype2 = ? WHERE clothnum = ? AND clothname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			// from절
			pstmt.setString(1, mDto.getClothNum());
			pstmt.setString(2, mDto.getClothName());
			pstmt.setInt(3, mDto.getPrice());
			pstmt.setString(4, mDto.getClothSize());
			pstmt.setString(5, mDto.getClothType());
			pstmt.setString(6, mDto.getClothType2());
			// where 절
			pstmt.setString(7, inputNum);
			pstmt.setString(8, inputName);

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("들어옴");
				System.out.println("상품 수정 성공");// 성공하면 1 돌려줌
			} else {
				// 실패하면 0 돌려줌
				System.out.println("들어옴2");
				System.out.println("상품 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt);
		}

	}

	// 상품 정보 삭제 메소드 한번더 확인할 때 매개변수 - 1. 이면 삭제 0. 이면 삭제 ㄴㄴ
	public int productInfoDel(String inputCloNum, String inputCloName) {

		int result = 0;
		String sql = "DELETE FROM clothdata " + "WHERE clothnum = ? " + "AND clothname = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DbManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, inputCloNum);
			pstmt.setString(2, inputCloName);

			result = pstmt.executeUpdate();

			if (result > 1) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, pstmt);
		}

		return result;
	}

}
