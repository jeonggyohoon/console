package womanClothe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import miniProject.DbManager;

public class WomanDAO {
	//조회하기 : SELECT만 Connection, PreparedStatement, ResultSet 을 사용함. 나머지는 rs 사용안함

//---------------------------------------상품 조회하기---------------------------------------------
	public ArrayList<WomanDTO> selAllCloth(String clothType) {
		
		//ArrayList 생성 및 선언하기 
		ArrayList<WomanDTO> clothList = new ArrayList<>();
		
		String sql = "SELECT clothNum, clothName, price, clothSize, clothType2 FROM clothData WHERE clothType =?";
		
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null; 
		
		try {
			//DB랑 연결
			conn = DbManager.getConnection(); // 연결객체 선언
			pstmt = conn.prepareStatement(sql); //전송객체 선언
			pstmt.setString(1, clothType); //(물음표 갯수, 입력받고자하는 컬럼)
			// executeQuery(); => ResultSet 클래스 리턴 (쿼리를 실행한 뒤, 조회된 결과를 ResultSet에 담아 테이블 형태로 리턴)
			rs = pstmt.executeQuery(); //결과객체 선언
			
			while(rs.next()) {
				WomanDTO wDto = new WomanDTO();
				
				wDto.setClothNum(rs.getString("clothNum"));
				wDto.setClothName(rs.getString("clothName"));
				wDto.setPrice(rs.getInt("price"));
				wDto.setClothSize(rs.getString("clothSize"));
				wDto.setClothType2(rs.getString("clothType2"));
				
				clothList.add(wDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(conn, pstmt, rs);
		} return clothList;
	}
	//----------------------------------전체 데이터 가져오기----------------------------------------
	public ArrayList<WomanDTO> selAllCloth2() {
		
		//ArrayList 생성 및 선언하기 
		ArrayList<WomanDTO> clothList = new ArrayList<>();
		
		String sql = "SELECT * FROM clothData";
		
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null; 
		
		try {
			//DB랑 연결
			conn = DbManager.getConnection(); // 연결객체 선언
			pstmt = conn.prepareStatement(sql); //전송객체 선언

			// executeQuery(); => ResultSet 클래스 리턴 (쿼리를 실행한 뒤, 조회된 결과를 ResultSet에 담아 테이블 형태로 리턴)
			rs = pstmt.executeQuery(); //결과객체 선언
			
			while(rs.next()) {
				WomanDTO wDto = new WomanDTO();
				
				wDto.setClothNum(rs.getString("clothNum"));
				wDto.setClothName(rs.getString("clothName"));
				wDto.setPrice(rs.getInt("price"));
				wDto.setClothSize(rs.getString("clothSize"));
				wDto.setClothType2(rs.getString("clothType2"));
				wDto.setClothType(rs.getString("clothType"));
				
				clothList.add(wDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbManager.close(conn, pstmt, rs);
		} return clothList;
	}
		
//---------------------------------------상품 등록하기---------------------------------------------
		
	// public int registerCloth~ finally까지 다 기입하고(공식) try 안을 채우기. 
		public int registerCloth (WomanDTO wDto){
			int result = 0 ;
			
			// sql문 쓸 때 짝맞추기 중요 (컬럼갯수와 물음표 갯수 짝 맞추는게 중요함)
			String sql = "INSERT INTO clothData(clothNum, clothName, price, clothSize, clothType, clothType2) \r\n"
					+ "VALUES (?,?,?,?,?,?)";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DbManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				// wDto (저장소)에서 .get (가져오겠다 ) ClothNum(상품번호를)
				// 물음표값에 대입해주기 위해 pstmt.set자료형(물음표순서, 매개변수(Dto를 매개변수로 가져오면 get메소드를 써야한다. ))
				pstmt.setString(1, wDto.getClothNum()); 
				pstmt.setString(2, wDto.getClothName());
				pstmt.setInt(3, wDto.getPrice());
				pstmt.setString(4, wDto.getClothSize());
				pstmt.setString(5, wDto.getClothType());
				pstmt.setString(6, wDto.getClothType2());
				
				result = pstmt.executeUpdate(); // Update 행위만큼 숫자가 늘어남. 한번 업데이트 -> 1 . 두번 업데이트 -> 2
				
				if (result > 0) {
					System.out.println("등록 성공");
					System.out.println();
				} else {
					System.out.println("등록 실패");
					System.out.println();					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbManager.close(conn, pstmt);	
			}			
			return result ;
			
		}
//---------------------------------------상품 수정하기---------------------------------------------
		public int modifyCloth(int price, String clothName) {
			int result = 0;
			String sql = "UPDATE clothData SET price = ? WHERE clothName = ?";
			
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			
			try {
				conn = DbManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, price);
				pstmt.setString(2, clothName);
				
				result = pstmt.executeUpdate();
				
				if(result>0) {
					System.out.println("수정 성공");
					System.out.println();
				} else {
					System.out.println("수정 성공");
					System.out.println();					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbManager.close(conn, pstmt);
			} return result ;
			
			
		}
		//---------------------------------------상품 삭제하기---------------------------------------------
		public int deleteCloth(String clothName) {
			int result = 0;
			
			String sql = "DELETE FROM clothData WHERE clothName = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DbManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, clothName);
				
				result = pstmt.executeUpdate();
				
				if(result >0) {
					System.out.println("삭제 성공");
					System.out.println();
				}else {
					System.out.println("삭제 실패");
					System.out.println();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbManager.close(conn, pstmt);
			} return result;
		}
		
		
}
