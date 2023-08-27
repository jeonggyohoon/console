package womanClothe;

import java.util.Scanner;

public class musinsaWoman {
	public void womanClothe() {
		
		Scanner scan = new Scanner(System.in);
				
		while (true) {
		System.out.println("=============MUSINSA STORE=============");
		System.out.println("*회원 메뉴  1. 상품 검색");
		System.out.println("*관리자 메뉴 2. 상품 등록 3. 상품 수정/삭제");
		System.out.println("4.종료");
		System.out.println("=======================================");
		System.out.println();
		System.out.print("메뉴 선택 :");
		String choice = scan.nextLine();
		System.out.println();
		
			
			if(choice.equals("1")) {
				WomanMain wMain = new WomanMain();
				wMain.selCloth();
				continue;
			}else if(choice.equals("2")) {
				InsertClothe insertCloth = new InsertClothe();
				insertCloth.Insert();
				continue;
			}else if(choice.equals("3")) {
				Update_Delete_Cloth udc = new Update_Delete_Cloth();
				udc.UpdateDel();
				continue;
			}else if(choice.equals("4")) {
				break;
				
			}else {
				System.out.println("다시 입력해주세요.");
				continue;
			} 
		}
	}

}
