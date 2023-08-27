package womanClothe;

import java.util.Scanner;

public class InsertClothe {
	public void Insert() {
		WomanDTO wDto = new WomanDTO();
		WomanDAO wDao = new WomanDAO();

		// clothNum, clothName, price, clothSize, clothType, clothType2
		Scanner scan = new Scanner(System.in); // 상품 입력하기]\

		// 유효성 검사
		while (true) {
			System.out.println("상품 번호 입력>>");
			String clothNum = scan.nextLine();
			if (clothNum.equals("")) { // 입력받은 값이 공백이라면 다시 입력하도록하기.
				System.out.println("다시 입력해주세요.");
				continue;
			} else if (clothNum.length() > 5) {
				System.out.println("5글자 이내로 다시 입력해주세요.");

				continue;
			} else {
				wDto.setClothNum(clothNum);
				break;
			}
		}
		while (true) {

			System.out.println("상품 이름 입력>>");
			String clothName = scan.nextLine();

			if (clothName.equals("")) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else if (clothName.length() > 15) {
				System.out.println("15글자 이내로 다시 입력해주세요");
				continue;
			} else {
				wDto.setClothName(clothName);
				break;
			}
		}

		while (true) {

			System.out.println("가격 입력>>");
			int price = scan.nextInt();
			scan.nextLine();

			if (price > 200000 && price < 10000) {
				System.out.println("20만원 이내로 입력해주세요.");
				continue;
			} else if (price == 0) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else {
				wDto.setPrice(price);
				break;
			}
		}

		while (true) {

			System.out.println("사이즈 입력>> ");
			String clothSize = scan.nextLine();

			if (clothSize.equals("")) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else if (!clothSize.equals("S") && !clothSize.equals("M") && !clothSize.equals("L")) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else {
				wDto.setClothSize(clothSize);
			}
			break; // (while문 탈출)
		}

		while (true) {
			System.out.println("상품 카테고리 입력>>");
			String clothType = scan.nextLine();

			if (clothType.equals("")) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else if (clothType.length() > 7) {
				System.out.println("7자 이내 영문으로 다시 입력해주세요.");
				continue;
			} else {
				wDto.setClothType(clothType);
			}
			break;
		}

		while (true) {

			System.out.println("상품 상세 카테고리 입력>>");
			String clothType2 = scan.nextLine();

			if (clothType2.equals("")) {
				System.out.println("다시 입력해주세요.");
				continue;
			} else if (clothType2.length() > 20) {
				System.out.println("20자 이내 영문으로 다시 입력해주세요.");
				continue;
			} else {
				wDto.setClothType2(clothType2);
			}
			break;
		}
		// 등록이 잘되어있는지 확인하기
		System.out.println(wDto.toString());
		wDao.registerCloth(wDto);
	}
}
