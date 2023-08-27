package manClothe;

import java.util.ArrayList;
import java.util.Scanner;

public class musinsaMan {

	public void manClothe() {
		Scanner sc = new Scanner(System.in);
		musinsaDAO mDao = new musinsaDAO();
		musinsaDTO mDto = new musinsaDTO();

		// 상품 정보 변수
		String num = null;
		String name = null;
		int price = 0;
		String size = null;
		String category = null;
		String category2 = null;

		// 카테고리 구체화 변수
		String inputCategory = null;

		while (true) {
			System.out.println("=============MUSINSA STORE============");
			System.out.println("회원 메뉴 1. 카테고리 보기");
			System.out.println("관리자 메뉴 2. 상품 등록	3. 상품 수정	4. 상품 삭제");
			System.out.println("종료 : 5 ");
			System.out.println("=======================================");
			System.out.print("메뉴 선택 : ");

			int choice = sc.nextInt();
			sc.nextLine();

			// -----------------------------1번 선택------------------------------------------

			if (choice == 1) {
				System.out.println("============CATEGORY============");
				System.out.println("1. 아우터		2. 상의 ");
				System.out.println("3. 하의	 	4. 신발 ");
				System.out.println("5. 악세사리 	6. 돌아가기");
				System.out.println("===============================");
				System.out.print("입력 : ");
				choice = sc.nextInt();
				if (choice == 1) {
					inputCategory = "outer";
				} else if (choice == 2) {
					inputCategory = "top";
				} else if (choice == 3) {
					inputCategory = "bottom";
				} else if (choice == 4) {
					inputCategory = "shoes";
				} else if (choice == 5) {
					inputCategory = "acc";
				} else if (choice == 6) {
					continue;
				} else {
					System.out.println("똑디 입력하소");
					continue;
				}
				ArrayList<musinsaDTO> mList = new ArrayList<>();
				mList = mDao.aList(inputCategory);
				if (mList.isEmpty()) {
					System.out.println("상품이 없습니다.");
				} else {
					System.out.println("상품번호\t\t상품이름\t\t상품가격\t\t사이즈\t\t종류2");
					for (int i = 0; i < mList.size(); i++) {
						System.out.println(mList.get(i).getClothNum() + "\t\t" + mList.get(i).getClothName() + "\t\t"
								+ mList.get(i).getPrice() + "\t\t" + mList.get(i).getClothSize() + "\t\t"
								+ mList.get(i).getClothType2());
					}

				}

//-------------------------------- 상품 등록 선택----------------------------------------------------------- 
			} else if (choice == 2) {
				ArrayList<musinsaDTO> numNameList1 = new ArrayList<>();
				numNameList1 =mDao.productNumName2();
				int numResult = 0;
				System.out.println("=========MUSINSA STORE=========");
				System.out.println("상품 등록입니다. 상품 정보를 입력해 주세요");
				// 공백 유효성 검사
				while (true) {
					System.out.println("=========================");
					System.out.print("상품 번호 >>");
					num = sc.nextLine();
					if (num.equals("")) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품번호에 공백을 입력하셨습니다");
						System.out.println("-------다시 입력해주세요------");
						continue;
					} else if (num.length() > 10) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("영어나 숫자 10자 이내로 입력해주세요");
						System.out.println("---------다시 입력해주세요--------");
						continue;
					} else {
						for (int i = 0; i < numNameList1.size(); i++) {
							String numPrimary = numNameList1.get(i).getClothNum();
							//clothNum 가져와서 저장 - 조건 걸어서 반복
							
							if (numPrimary.equals(num)) {
								//사용자 입력한 값과 DB상 ClothNum과 비교해서 해당 문자열과 같은 데이터가 있는지 검사 있으면 true값
								//중복된 상품번호를 입력한 경우
								numResult = 0;
								break;// 중복된 경우 만나면 for문 탈출
							}else if (!numPrimary.equals(num)) {
								
								// 중복 아닌 상품번호 입력한 경우
								numResult = 1;
							}
							
						}
						if(numResult == 0) {
							//그 속에서 중복값이 있을경우
							System.out.println("=====================================");
							System.out.println(".");
							System.out.println(".");
							System.out.println(".");
							System.out.println("입력하신 상품번호와 중복되는 상품번호가 존재합니다");
							System.out.println("-------------다시 입력해주세요------------");
							continue;
							//else = 공백이 아니거나 길이가 10자 초과하지 않을경우, 중복이 아닌 경우를 포함한 모든경우 ? 
						}else { //result == 1 일 경우를 가리킬까?
							mDto.setClothNum(num);
							
						}
					}
					System.out.println("============================");
					System.out.println("상품 이름은 16자 이하로 작성해주세요");
					System.out.println("============================");
					System.out.print("상품 이름 >>");
					name = sc.nextLine();
					if (name.equals("")) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 이름에 공백을 입력하셨습니다");
						System.out.println("-------다시 입력해주세요------");
						continue;
					} else if (name.length() > 16) {
						System.out.println("=================================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품이름을 16자 이내로 다시 입력해 주세요.");
						continue;
					} else {
						mDto.setClothName(name);
					}
					System.out.println("=========================");
					System.out.print("상품 가격 >>");
					
					try {
						price = sc.nextInt();
						
						if (price >= 0 && price <= 1000000) {
							mDto.setPrice(price);
							
						} 
						
					} catch (Exception e) {
						System.out.println("==================================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 가격이 0보다 작거나 100만원 보다 큽니다");
						System.out.println("------------다시 입력해주세요-----------");
						
						sc.nextLine();
						continue;
						// TODO: handle exception
					}
					sc.nextLine();
					System.out.println("==============================");
					System.out.println("상품 사이즈는 large = L midium = M\n과같이 대문자로 입력해 주세요(XXL까지)");
					System.out.println("==============================");
					System.out.print("상품 사이즈 >>");
					size = sc.nextLine();
					if (!size.equals("")) {
						if (size.equals("L") || size.equals("M") || size.equals("XL") || size.equals("XXL")
								|| size.equals("S")) {
							mDto.setClothSize(size);
						} else {
							System.out.println("=====================================");
							System.out.println(".");
							System.out.println(".");
							System.out.println(".");
							System.out.println("사이즈에 M,L,XL,XXL 이외의 값을 입력하셨습니다");
							System.out.println("--------------다시 입력해주세요-----------");
							continue;
						}
					} else {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("사이즈에 공백을 입력하셨습니다");
						System.out.println("------다시 입력해주세요-----");
						continue;

					}
					System.out.println("==============================");
					System.out.println(
							"상품종류는 \n상의 = top\n하의 = bottom\n아우터 = outer\n신발 = shoes\n악세사리 = acc\n 위 5가지로 입력해주세요.\n그 외 입력시 다시 입력하셔야 됩니다");
					System.out.println("==============================");
					System.out.print("상품 종류 >>");
					category = sc.nextLine();
					if (!category.equals("")) {
						if (category.equals("top") || category.equals("bottom") || category.equals("outer")
								|| category.equals("shoes") || category.equals("acc")) {
							mDto.setClothType(category);

						} else {
							System.out.println("====================================");
							System.out.println(".");
							System.out.println(".");
							System.out.println(".");
							System.out.println("상품 종류에 top,bottom,outer,shoes,acc\n이외의 값을 입력하셨습니다");
							System.out.println("------------다시 입력해주세요------------");
							continue;
						}
					} else {
						System.out.println("=========================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 종류에 공백을 입력하셨습니다");
						System.out.println("------다시 입력해주세요------");
						continue;

					}
					System.out.println("=========================");
					System.out.print("세부 종류 >>");
					category2 = sc.nextLine();
					if (category2.equals("")) {
						System.out.println("=========================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("세부 종류에 공백을 입력하셨습니다");
						System.out.println("-------다시 입력해주세요------");
						continue;
					} else if (category2.length() > 20) {
						System.out.println("=====================================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("세부 종류를 영문자 20자 이내로 다시 입력해 주세요");
					} else {
						mDto.setClothType2(category2);

					}

					// 2차 유효성 검사 통과 시 DAO 등록 메소드 실행(int 형? void형?) -> if (RETURN값 1 돌아오면) 성공 else 실패

					int result = mDao.insertProduct(mDto);
					if (result > 0) {
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("=========★상품 등록이 완료되었습니다★========");
						System.out.println("추가 등록하시겠습니까? ");
						System.out.println("1. 예  2. 다른숫자 : 메인메뉴로");

						choice = sc.nextInt();
						sc.nextLine();

						if (choice == 1) {
							System.out.println("=========================");
							System.out.println("상품 추가 등록을 선택하셨습니다");
							System.out.println("=========================");

							continue;

						} else {
							break;

						}
					} else {
						System.out.println("상품 등록에 실패하였습니다");

						System.out.println("다시 등록하시겠습니까?");
						System.out.println("1. 예 	2.다른숫자 : 메인메뉴로");

						choice = sc.nextInt();
						sc.nextLine();

						// 다시 등록하기 선택시 공백 입력 버그
						if (choice == 1) {
							System.out.println("==========================");
							System.out.println("상품 다시 등록하기를 선택하셨습니다");
							System.out.println("==========================");
							continue;

						} else {
							System.out.println("================");
							System.out.println("프로그램을 종료합니다");
							System.out.println("================");
							break;

						} // 1, 2 아닌 다른 값 입력 받았을 때 어떻게 할지? 생각
					}

				}

//------------------------------------------상품 수정---------------------------------------------------- 
			} else if (choice == 3) {
				String dbNum = null;
				String dbName = null;
				int dbPrice = 0;
				String dbSize = null;
				String dbCategoty = null;
				String dbCategoty2 = null;

				// 유효성 검사
				System.out.println("==================MUSINSA STORE=================");
				System.out.println("상품 목록입니다");
				ArrayList<musinsaDTO> numNameList1 = new ArrayList<>();
				numNameList1 =mDao.productNumName2();
				if (numNameList1.isEmpty()) {
					System.out.println("상품 목록이 존재하지 않습니다");
					continue;
				} else {
					//상품 목록 출력
					System.out.println("상품 번호\t\t상품 이름");
					for (int i = 0; i < numNameList1.size(); i++) {
						System.out.println(
								numNameList1.get(i).getClothNum() + "\t\t" + numNameList1.get(i).getClothName());
					}
				}
				System.out.println("======================================");
				System.out.println("수정하고싶은 상품의 상품번호와 이름을 입력해 주세요");
				while (true) {
					
					System.out.print("상품 번호 >>");
					num = sc.nextLine();
					int result =0;
					
					if (num.equals("")) {
						System.out.println("=========================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품번호에 공백을 입력하셨습니다");
						System.out.println("-------다시 입력해주세요-----");
						continue;
					
					}else if (num.length() > 10) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("영어나 숫자 10자 이내로 입력해주세요");
						System.out.println("============================");
						continue;
					} else {
						mDto.setClothNum(num);
						
					}


					System.out.print("상품 이름 >>");
					name = sc.nextLine();
					if (name.equals("")) {
						System.out.println("=========================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품이름에 공백을 입력하셨습니다");
						System.out.println("-------다시 입력해주세요-----");
						continue;
					
					} else if (name.length() > 16) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("한글 16자 이내로 입력해주세요");
						System.out.println("============================");
						continue;
					} else {
						mDto.setClothNum(num);
					}

					System.out.println("==========<상품 조회중>==========");
					System.out.println(".");
					System.out.println(".");
					System.out.println(".");
					System.out.println("조회하신 상품 정보입니다");
					System.out.println("============================");
					ArrayList<musinsaDTO> numNameList = new ArrayList<>();

					numNameList = mDao.productNumName(num, name);
					if (numNameList.isEmpty()) {
						System.out.println("조회하신 상품과 일치하는 상품이 없습니다");
						System.out.println("================================");
						continue;
					} else {

						for (int i = 0; i < numNameList.size(); i++) {
							System.out.println("상품번호 : " + numNameList.get(i).getClothNum() + "\n" + "상품이름 : "
									+ numNameList.get(i).getClothName() + "\n" + "상품가격 : "
									+ numNameList.get(i).getPrice() + "\n" + "상품 사이즈 : "
									+ numNameList.get(i).getClothSize() + "\n" + "상품 종류 : "
									+ numNameList.get(i).getClothType() + "\n" + "상품 종류2 : "
									+ numNameList.get(i).getClothType2());

						}
					}
					// 관리자 유효성 검사

					System.out.println("=================================================");
					System.out.println("찾으시려는 상품과 일치 합니까?");
					System.out.println("1. 예 	2. 아니오");

					System.out.print("입력 >>");
					choice = sc.nextInt();
					sc.nextLine();

					
					// 수정 원하는 상품이 맞다면

					if (choice == 1) {
						System.out.println("수정하실 정보를 입력해주세요");

						while (true) {
							System.out.print("상품 번호 >>");
							dbNum = sc.nextLine();
							//공백이 아거나
							if (dbNum.equals("")) {
								System.out.println("==========================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품번호에 공백을 입력하셨습니다");
								System.out.println("-------다시 입력해주세요-------");
								continue;
							// 길이가 10자를 초과하면 continue
							}else if (dbNum.length() > 10) {
								System.out.println("=================================================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 번호의 길이를 영문 / 숫자 포함 10자 이내로 입력해 주세요");
								System.out.println("=================================================");
								continue;
							
							}else{
								for (int i = 0; i < numNameList1.size(); i++) {
									String numPrimary = numNameList1.get(i).getClothNum();
									//clothNum 가져와서 저장 - 조건 걸어서 반복
									
									if (numPrimary.equals(dbNum)) {
										//사용자 입력한 값과 DB상 ClothNum과 비교해서 해당 문자열과 같은 데이터가 있는지 검사 있으면 true값
										//중복된 상품번호를 입력한 경우
										result = 0;
										break;// 중복된 경우 만나면 for문 탈출
									}else if (!numPrimary.equals(dbNum)) {
										
										// 중복 아닌 상품번호 입력한 경우
										result = 1;
									}
									
								}
								if(result == 0) {
									//그 속에서 중복값이 있을경우
									System.out.println("======================================");
									System.out.println(".");
									System.out.println(".");
									System.out.println(".");
									System.out.println("입력하신 상품번호와 중복되는 상품번호가 존재합니다");
									System.out.println("-------------다시 입력해주세요------------");
									continue;
									//else = 공백이 아니거나 길이가 10자 초과하지 않을경우, 중복이 아닌 경우를 포함한 모든경우 ? 
								}else { //result == 1 일 경우를 가리킬까?
									mDto.setClothNum(dbNum);
									break;
								}
							}
							
							
							
							
						}
						while (true) {
							System.out.println("===============================");
							System.out.println("상품 이름은 한글 16자 이하로 작성해주세요");
							System.out.println("===============================");
							System.out.print("상품 이름 >>");
							dbName = sc.nextLine();
							if (dbName.equals("")) {
								System.out.println("==========================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품이름에 공백을 입력하셨습니다");
								System.out.println("-------다시 입력해주세요------");
								continue;
							}  else if (dbName.length() > 16) {
								System.out.println("============================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("한글 16자 이내로 입력해주세요");
								continue;
							} else {
								mDto.setClothNum(dbName);
								break;
							}
						}

						while (true) {
							System.out.print("상품 가격 >>");
							
							try {
								dbPrice = sc.nextInt();
								
								if (dbPrice >= 0 && price <= 1000000) {
									mDto.setPrice(dbPrice);
									break;
								} 
								
							} catch (Exception e) {
								System.out.println("===================================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 가격이 0보다 작거나 100만원 보다 큽니다");
								System.out.println("------------다시 입력해주세요-----------");
								
								sc.nextLine();
								continue;
								// TODO: handle exception
							}
							

							
						}
						sc.nextLine();
						while (true) {
							System.out.println("==============================");
							System.out.println("상품 사이즈는 large = L midium = M\n과같이 대문자로 입력해 주세요(XXL까지)");
							System.out.println("==============================");
							System.out.print("상품 사이즈 >>");
							dbSize = sc.nextLine();
							if (!dbSize.equals("")) {
								if (dbSize.equals("L") || dbSize.equals("M") || dbSize.equals("XL")
										|| dbSize.equals("XXL")) {
									mDto.setClothSize(dbSize);
									break;
								} else {
									System.out.println("======================================");
									System.out.println(".");
									System.out.println(".");
									System.out.println(".");
									System.out.println("사이즈에 M,L,XL,XXL 이외의 값을 입력하셨습니다");
									System.out.println("-------------다시 입력해주세요------------");
									continue;
								}
							} else  {
								System.out.println("===========================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 사이즈에 공백을 입력하셨습니다");
								System.out.println("--------다시 입력해주세요------");
								continue;
							}
						}

						while (true) {
							System.out.println("==============================");
							System.out.println(
									"상품종류는 \n상의 = top\n하의 = bottom\n아우터 = outer\n신발 = shoes\n악세사리 = acc\n 위 5가지로 입력해주세요.\n그 외 입력시 다시 입력하셔야 됩니다");
							System.out.println("==============================");
							System.out.print("상품 종류 >>");
							dbCategoty = sc.nextLine();
							if (!dbCategoty.equals("")) {
								if (dbCategoty.equals("top") || dbCategoty.equals("bottom")
										|| dbCategoty.equals("outer") || dbCategoty.equals("shoes")
										|| dbCategoty.equals("acc")) {
									mDto.setClothType(dbCategoty);
									break;
								} else {
									System.out.println("===================================");
									System.out.println(".");
									System.out.println(".");
									System.out.println(".");
									System.out.println("상품 종류에 top,bottom,outer,shoes,acc\n이외의 값을 입력하셨습니다");
									System.out.println("-----------다시 입력해주세요-----------");
									continue;
								}
							} else {
								System.out.println("===========================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 종류에 공백을 입력하셨습니다");
								System.out.println("-------다시 입력해주세요-------");
								continue;

							}
						}

						while (true) {
							System.out.print("세부 종류 >>");
							dbCategoty2 = sc.nextLine();
							if (dbCategoty2.equals("")) {
								System.out.println("==========================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("세부 종류에 공백을 입력하셨습니다");
								System.out.println("--------다시 입력해주세요------");;
								continue;
							}else if (dbCategoty2.length() > 10) {
								System.out.println("==================================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("세부 종류를 영문자 20자 이내로 입력해 주세요");
								System.out.println("==================================");
								continue;
							} 
							else {
								mDto.setClothType2(dbCategoty2);
								break;
							}
						}

//						// 관리자 확인
//						System.out.println("============<상품 수정중>============");
//						System.out.println(".");
//						System.out.println(".");
//						System.out.println(".");
//						System.out.println("=======★바뀐 상품 정보 입니다★=========");
//						
//						ArrayList<musinsaDTO> updateList = new ArrayList<>();
//						updateList =  mDao.productNumName(dbNum, dbName);
//						
//						for (int i = 0; i < updateList.size(); i++) {
//							System.out.println("상품번호 : " + updateList.get(i).getClothNum() + "\n"
//											 + "상품이름 : " + updateList.get(i).getClothName() + "\n"
//											 + "상품가격 : " + updateList.get(i).getPrice() + "\n"
//											+ "상품사이즈 : " + updateList.get(i).getClothSize() + "\n"
//											+ "상품 종류 : " + updateList.get(i).getClothType() + "\n"
//											+ "세부 종류 : " + updateList.get(i).getClothType2()); 
//							
//						}
						System.out.println("수정을 완료하시겠습니까?");
						System.out.println("1. 예 	2. 다른숫자 : 추가 수정");

						System.out.print("입력 >>");
						choice = sc.nextInt();
						sc.nextLine();
						// 관리자 수정 완료 했다면
						// DAO 수정 메소드 불러와서 수정 실행
						if (choice == 1) {
							System.out.println("수정 완!");
							break;
							// 좀더 수정할 부분 있으면 / 다른 수정할 상품 있으면

						
						} else {
							System.out.println("====================");
							System.out.println("추가 수정을 선택하셨습니다");
							System.out.println("====================");
							continue;
						}

					} else {
						System.out.println("====================");
						System.out.println("상품을 다시 조회해 주세요");
						System.out.println("====================");

						continue;
					}
				}

//--------------------------------상품 삭제-------------------------------------------------
			} else if (choice == 4) {

				//
				System.out.println("====================MUSINSA STORE==================");
				System.out.println("상품 삭제입니다. 목록에서 삭제하실 상품 번호와 이름을 입력해 주세요");
				System.out.println("==================================================");
				System.out.println("상품 목록입니다");
				ArrayList<musinsaDTO> numNameList1 = new ArrayList<>();
				numNameList1 =mDao.productNumName2();
				if (numNameList1.isEmpty()) {
					System.out.println("상품 목록이 존재하지 않습니다");
					continue;
				} else {
					//상품 목록 출력
					System.out.println("상품 번호\t\t상품 이름");
					for (int i = 0; i < numNameList1.size(); i++) {
						System.out.println(
								numNameList1.get(i).getClothNum() + "\t\t" + numNameList1.get(i).getClothName());
					}
				}
				System.out.println("==================================================");
				while (true) {
					// 여기 변수를 DB에 넘겨줘서 조회 할듯?
					System.out.print("상품 번호 >>");
					num = sc.nextLine();
					if (num.equals("")) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 번호에 공백을 입력하셨습니다");
						System.out.println("============================");
						continue;
					}else if (num.length() > 10) {
						System.out.println("=================================================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 번호의 길이를 영문 / 숫자 포함 10자 이내로 입력해 주세요");
						System.out.println("=================================================");
						continue;
					
					}else {
						mDto.setClothNum(num);
					}
					System.out.print("상품 이름 >>");
					name = sc.nextLine();
					if (name.equals("")) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("상품 이름에 공백을 입력하셨습니다");
						System.out.println("============================");
						continue;
					} else if (name.length() > 16) {
						System.out.println("============================");
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						System.out.println("한글 16자 이내로 입력해주세요");
						System.out.println("============================");
						continue;
					}else {
						mDto.setClothName(name);
					}

					// DAO에서 arrayList 불러오기

					System.out.println("==============<상품 조회중>====================");
					System.out.println(".");
					System.out.println(".");
					System.out.println(".");
					ArrayList<musinsaDTO> numNameList = new ArrayList<>();

					numNameList = mDao.productNumName(num, name);
					if (numNameList.isEmpty()) {
						System.out.println("조회하신 상품과 일치하는 상품이 없습니다");
						System.out.println("================================");
						continue;
					} else {

						for (int i = 0; i < numNameList.size(); i++) {
							System.out.println("상품번호 : " + numNameList.get(i).getClothNum() + "\n" + "상품이름 : "
									+ numNameList.get(i).getClothName() + "\n" + "상품가격 : "
									+ numNameList.get(i).getPrice() + "\n" + "상품 사이즈 : "
									+ numNameList.get(i).getClothSize() + "\n" + "상품 종류 : "
									+ numNameList.get(i).getClothType() + "\n" + "상품 종류2 : "
									+ numNameList.get(i).getClothType2());

						}
						
					}
					
					System.out.println("조회하신 상품 정보입니다");
					System.out.println("삭제 하시려는 상품과 일치 합니까?");
					System.out.println("1. 예 	2. 아니오");

					System.out.print("입력 >>");
					choice = sc.nextInt();
					sc.nextLine();
					// 찾는 상품과 일치하지 않는다면
					if (choice == 2) {
						continue;

						// 한번더 확인
					} else {
						System.out.println("1. 예 	2. 아니오");

						System.out.print("입력 >>");
						choice = sc.nextInt();
						sc.nextLine();
						// 한번더 체크

						// DAO 에서 삭제하는 메소드 들어갈 곳
						if (choice == 1) {
							int result = mDao.productInfoDel(num, name);
							if (result == 1) {
								System.out.println("==============<상품 삭제중>====================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 삭제에 실패하였습니다");
								System.out.println("==================");
								System.out.println("다시 삭제하시겠습니까?");
								System.out.println("1. 다시 삭제하기 	2. 끝내기");
								System.out.print("입력 >>");
								choice = sc.nextInt();
								sc.nextLine();
								if (choice == 1) {
									System.out.println("======================");
									System.out.println("다시 삭제하기를 선택하셨습니다");
									System.out.println("======================");
									continue;
								}else {
									System.out.println("=================");
									System.out.println("끝내기를 선택하셨습니다");
									System.out.println("=================");
									System.exit(0);
								}
								
								
							} else {
								System.out.println("==============<상품 삭제중>====================");
								System.out.println(".");
								System.out.println(".");
								System.out.println(".");
								System.out.println("상품 삭제에 성공하였습니다");
								
								System.out.println("=======================");
								System.out.println("더 삭제하실 상품이 있으신가요?");
								System.out.println("1. 더 삭제하기 	2. 끝내기");

								System.out.print("입력 >>");
								choice = sc.nextInt();
								sc.nextLine();
								if (choice == 1) {
									System.out.println("=====================");
									System.out.println("더 삭제하기를 선택하셨습니다");
									System.out.println("=====================");
								}else {
									System.out.println("=================");
									System.out.println("끝내기를 선택하셨습니다");
									System.out.println("=================");
									System.exit(0);
									
								}
								
							}

						} else {
							System.out.println("아니오를 선택한 당신, 우유부단한 성격이시군요");
							continue;
						}
					}

				}

				// 5번 (종료) 선택할 경우 종료
			} else if (choice == 5) {
				break;

				// 이상한거 입력할 시
			} else {
				System.out.println("똑디 입력하소");
				continue;
			}
			
		}
	}
}
