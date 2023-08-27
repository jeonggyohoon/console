package womanClothe;

import java.util.ArrayList;
import java.util.Scanner;

public class WomanMain {

	public void selCloth(){

		// 사용할 변수 초기화
	

		String clothNum = null; // 상품번호
		String clothName = null; // 상품명
		String price = null; // 가격
		String clothSize = null; // 의류 사이즈
		String clothType = null; //전체 카테고리(아우터, 상의, 하의 등 )
		String clothType2 = null; //상세 카테고리( 아우터> 자켓, 점퍼, 가디건 등)
		
		/*
		 * String outer = null; // 아우터 String top = null; // 상의 String bottom = null; //
		 * 하의 String shoes = null; // 신발 String acc = null; // 악세사리
		 */
		Scanner scan = new Scanner(System.in);
		
		// WomanDTO에 담긴 정보들을 한번에 꺼내서 보여줄 'ArrayList' 생성하기 
		ArrayList<WomanDTO> clothList = new ArrayList<>();
		
		
			/*
			 * while (true) { System.out.println("메인으로 돌아가기: 0을 눌러주세요");
			 * System.out.print("조회하고 싶은 카테고리명: "); category = scan.next(); }
			 */
			while (true) {
				System.out.println("================여성의류 목록==================");
				// 6.돌아가기 : break; 탈출하게만들기
				System.out.println("1.아우터  2.상의  3.하의  4.신발  5.악세사리 6.돌아가기");
				System.out.println();
				System.out.print("메뉴 선택 :");
				String choice1 = scan.nextLine();
				
				//아우터 조회하기
				if(choice1.equals("1")) {
					
					//DAO에 만들어놓은 조회기능 사용해야하니까 DAO 생성하기
					WomanDAO wDao = new WomanDAO();
					
					// SQL문 SELECT의 WHERE가 clothType이다.
					clothType = "OUTER"; 
					
					// 생성한 DAO사용하기 -> DAO에 접근해서 모든 의류정보를 보여주는 기능 호출하기(clothType에는 outer가 들어감)
					wDao.selAllCloth(clothType);
					
					// 위에 만들어놓은 ArrayList 사용하기 -> ArrayList변수명 clothList 적고 Dao 조회기능 할당하기
					clothList = wDao.selAllCloth(clothType);
					
					if(clothList.isEmpty()) {
						System.out.println("상품이 없습니다!");
					}else{
						
						//리스트에 접근해서 forEach문을 사용하여 ArrayList에 담아놓은 것들 순회하기
						// forEach문 : 익명함수 사용한다 . 
						System.out.println("상품번호\t상품명\t가격\t사이즈\t상세 카테고리");
						clothList.forEach(clothData -> {
							// get메소드는 DTO에서만 쓸수있다.
							System.out.println(clothData.getClothNum()+"\t" 
									+ clothData.getClothName()+"\t" 
									+ clothData.getPrice()+"\t" 
									+ clothData.getClothSize()+"\t" 
									+ clothData.getClothType2());
						});
						// 0429 토요일 상의/하의/신발/악세사리 보여주기 기능 만들고 => 마지막으로 else{} 유효성검사하기.
					}
					// 2번 상의 조회하기
				} else if(choice1.equals("2")) {
					
					WomanDAO wDao = new WomanDAO();
					clothType = "top";
					wDao.selAllCloth(clothType);
					clothList = wDao.selAllCloth(clothType);
					if(clothList.isEmpty()) {
						System.out.println("상품이 없습니다!");
					}else {
						
						System.out.println("상품번호\t상품명\t가격\t사이즈\t상세 카테고리");
						clothList.forEach(clothData -> {
							System.out.println(clothData.getClothNum()+"\t"
									+ clothData.getClothName()+"\t"
									+ clothData.getPrice()+"\t"
									+ clothData.getClothSize()+"\t"
									+ clothData.getClothType2());
						});
					}
				
				} else if(choice1.equals("3")) {
					
					WomanDAO wDao = new WomanDAO();
					clothType = "bottom";
					wDao.selAllCloth(clothType);
					clothList = wDao.selAllCloth(clothType);
					if(clothList.isEmpty()) {
						System.out.println("상품이 없습니다!");
					}else {
						
						System.out.println("상품번호\t상품명\t가격\t사이즈\t상세 카테고리");
						clothList.forEach(clothData -> {
							System.out.println(clothData.getClothNum()+"\t"
									+ clothData.getClothName()+"\t"
									+ clothData.getPrice()+"\t"
									+ clothData.getClothSize()+"\t"
									+ clothData.getClothType2());
						});
						
					}
				} else if(choice1.equals("4")) {
					
					WomanDAO wDao = new WomanDAO();
					clothType = "shoes";
					wDao.selAllCloth(clothType);
					clothList = wDao.selAllCloth(clothType);
					if(clothList.isEmpty()) {
						System.out.println("상품이 없습니다!");
					}else {
						
						System.out.println("상품번호\t상품명\t가격\t사이즈\t상세 카테고리");
						clothList.forEach(clothData -> {
							System.out.println(clothData.getClothNum()+"\t"
									+ clothData.getClothName()+"\t"
									+ clothData.getPrice()+"\t"
									+ clothData.getClothSize()+"\t"
									+ clothData.getClothType2());
						});
					}
				} else if(choice1.equals("5")) {
					
					WomanDAO wDao = new WomanDAO();
					clothType = "acc";
					wDao.selAllCloth(clothType);
					clothList = wDao.selAllCloth(clothType);
					if(clothList.isEmpty()) {
						System.out.println("상품이 없습니다!");
					}else {
						
						System.out.println("상품번호\t상품명\t가격\t사이즈\t상세 카테고리");
						clothList.forEach(clothData -> {
							System.out.println(clothData.getClothNum()+"\t"
									+ clothData.getClothName()+"\t"
									+ clothData.getPrice()+"\t"
									+ clothData.getClothSize()+"\t"
									+ clothData.getClothType2());			
						});
					}
					
				} else if(choice1.equals("6")) {
					break;
				} else {
					System.out.println("다시 입력해주세요.");
					continue;
				}
					
			}

		}

	

}
