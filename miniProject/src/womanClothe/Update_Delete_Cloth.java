package womanClothe;

import java.util.ArrayList;
import java.util.Scanner;

public class Update_Delete_Cloth {
	public  void UpdateDel(){

		// 3번 눌렀을 때 -> 전체상품 목록 조회하게됨.

		WomanDTO wDto = new WomanDTO();
		WomanDAO wDao = new WomanDAO();
		Scanner scan = new Scanner(System.in);

		ArrayList<WomanDTO> wList = new ArrayList<>();
		// 자료형별 초기화 : int형 => 0 / boolean 기본값=> False / String , char => null
		int price = 0;
		String clothName = null;

		// wList라는 비어있는 변수에 DAO에서 가져온 리스트를 담는다.
		wList = wDao.selAllCloth2();
		// size()메소드
		System.out.println("상품번호\t상품명\t상품사이즈\t가격\t카테고리\t상세 카테고리");
		for (int i = 0; i < wList.size(); i++) {
			System.out.println(wList.get(i).getClothNum() + "\t" + wList.get(i).getClothName() + "\t"
					+ wList.get(i).getClothSize() + "\t" + wList.get(i).getPrice() + "\t" + wList.get(i).getClothType()
					+ "\t" + wList.get(i).getClothType2() + "\t");

		}
		while(true) {
			
			
			System.out.println("1. 상품 수정 2. 상품 삭제 3.돌아가기");
			System.out.print("====>");
			String chk = scan.nextLine();
			if (chk.equals("1")) {
				
				while (true) {
					System.out.println("수정하고싶은 상품명 입력>>");
					clothName = scan.nextLine(); // 개행까지 뽑아옴.
					int result = 0;
					for (int i = 0; i < wList.size(); i++) {
						String wClothName = wList.get(i).getClothName();
						
						if (!wClothName.equals(clothName)) {
							result = 0;
						} else {
							result = 1;
						}
					}
					if (result == 0) {
						System.out.println("존재하지 않는 상품명입니다. 다시 입력해주세요");
						continue;
						
					} else {
						while (true) {
							
							System.out.println("가격 수정>>");
							price = scan.nextInt();
							scan.nextLine(); // nextInt에 남아있는 엔터값을 먹는다.
							if (price > 200000 || price < 10000) { // || 둘 중 하나라도 TURE이면.
								System.out.println("가격을 다시 입력하세요.");
								continue;
							}
							
							wDao.modifyCloth(price, clothName);
							break;
						}
						
					}
					break;
				} continue;
			}else if(chk.equals("2")) {
				while (true) {
					System.out.println("삭제하고 싶은 상품명>>");
					clothName = scan.nextLine();
					int result = 0;
					for (int i = 0; i < wList.size(); i++) {
						String wClothName = wList.get(i).getClothName();
						
						if (!wClothName.equals(clothName)) {
							result = 0;
						} else {
							result = 1;
						}
					}
					if (result == 0) {
						System.out.println("존재하지 않는 상품명입니다. 다시 입력해주세요");
						continue;					
					}				
					wDao.deleteCloth(clothName); 
					break;
				}
				continue;
			}else if(chk.equals("3")) {
				break;
			} else {
				System.out.println("다시입력해주세요!");
				continue;
			}
		}

	}
}
