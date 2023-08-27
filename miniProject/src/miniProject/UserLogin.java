package miniProject;

import java.util.Scanner;

public class UserLogin {
	public String login() {
		String userId = null;
		Scanner sc = new Scanner(System.in);
		userDAO uDao = new userDAO();
		while(true) {
			
			System.out.print("ID를 입력하세요:");
			String inputId = sc.nextLine();
			System.out.print("비밀번호를 입력하세요:");
			String inputPwd = sc.nextLine();
			if(!inputId.equals("")&&!inputPwd.equals("")) {
				
				System.out.println("로그인 하시겠습니까? 1.y 2.N(돌아가기)");
				String check = sc.nextLine();
				
				if(check.equals("1")) {
					int userLogin = uDao.loginUser(inputId,inputPwd);
					if(userLogin==-1) {
						System.out.println("존재하지 않는 아이디 입니다.");
						continue;
					}else if(userLogin==0) {
						System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
						continue;
					}else {
						System.out.println("로그인 성공");
						userId = inputId; 
						while (true) {
							System.out.println("1.회원정보수정 2.돌아가기");
							String inputNum2 = sc.nextLine();
							if (inputNum2.equals("1")) {
								UserUpdate update = new UserUpdate();
								update.update(userId);
								continue;
							} else if (inputNum2.equals("2")) {
								break;
							} else {
								System.err.println("다시입력하세요");
								continue;
							}

						}
						
					}
				}else if(check.equals("2")) {
					break;
				}else {
					System.err.println("다시입력해주세요");
					continue;
				}
			}else if(inputId.equals("")&&!inputPwd.equals("")) {
				System.err.println("아이디를 입력해주세요");
				continue;
			}else if(!inputId.equals("")&&inputPwd.equals("")) {
				System.err.println("비밀번호를를 입력해주세요");
				continue;
			}else {
				System.err.println("다시입력해주세요");
				System.out.println();
				continue;
			}
			break;
		}
		return userId;
	}
}
