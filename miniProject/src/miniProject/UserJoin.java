package miniProject;

import java.util.Scanner;

public class UserJoin {
	public void Join() {
		
		Scanner sc = new Scanner(System.in);
		String userId = null;
		String userPwd = null;
		String userEmail = null;
		String userName = null;
		String userGender = null;
		userDTO uDto = new userDTO();
		userDAO uDao= new userDAO();
		
		
		//회원가입
		while(true) {
			System.out.print("아이디를 입력하세요:");
			String inputId = sc.nextLine();
			if(inputId.isEmpty()) {
				System.out.println("아이디는 필수사항입니다.");
				continue;
			}else {
				if(inputId.length()>=5&&inputId.length()<=15) {
					//공백제거
					userId=inputId.replaceAll("\\s", "");
					if(uDao.idCheck(userId)==1) {
						System.out.println("존재하는 아이디 입니다.");
					}else {
						uDto.setUserId(userId);
						System.out.println("입력하신 Id==>"+userId);
						break;
					}
				}else {
					System.out.println("아이디는 5~15자 이내로 작성해주세요");
				}
			}
			
		}
		while(true) {
			System.out.print("비밀번호를 입력하세요 :");
			String inputPwd = sc.nextLine();
			if(inputPwd.isEmpty()) {
				System.out.println("비밀번호는 필수 사항입니다.");
				continue;
			}else {
				if(inputPwd.length()>=8&&inputPwd.length()<=16) {
					userPwd=inputPwd.replaceAll("\\s", "");
					while(true) {
						System.out.print("비밀번호 재확인 :");
						String inputRePwd = sc.nextLine();
						if(inputRePwd.isEmpty()) {
							System.out.println("비밀번호는 재확인은 필수 사항입니다.");
							continue;
						}else {
							if(inputRePwd.length()>=8&&inputRePwd.length()<=16) {
								String userRePwd=inputRePwd.replaceAll("\\s", "");
								if(userPwd.equals(userRePwd)) {
									uDto.setUserPwd(userPwd);
									break;									
								}else {
									System.err.println("비밀번호가 다릅니다.");
									continue;
								}
							}else {
								System.out.println("비밀번호는 8~16자 이내로 입력해주세요.");
							}
						}
					}
					break;
				}else {
					System.out.println("비밀번호는 8~16자 이내로 입력해주세요.");
				}
			}
		}
		
		
		while(true) {
			System.out.print("이메일을 입력하세요 :");
			String inputEmail = sc.nextLine();
			if(inputEmail.isEmpty()) {
				System.out.println("이메일은 필수 사항입니다.");
				continue;
			}else {
				userEmail=inputEmail.replaceAll("\\s", "");
				uDto.setUserEmail(userEmail);
				System.out.println("입력하신 email은==>"+userEmail);
				break;
			}
			
		}
		while(true) {
			System.out.println("이름을 입력하세요 :");
			String inputName = sc.nextLine();
			if(inputName.isEmpty()) {
				System.out.println("이름은 필수 사항입니다.");
				continue;
			}else {
				userName=inputName.replaceAll("\\s", "");
				uDto.setUserName(userName);
				System.out.println("입력하신 이름은==>"+userName);
				break;
			}
			
		}
		while(true) {
			
			System.out.println("성별을 선택하세요 => 1.M 2.F");
			String inputGender = sc.nextLine();
			if(inputGender.isEmpty()) {
				System.out.println("성별을 선택해주세요.");
			}else if(inputGender.equals("1")){
				userGender="m";
				uDto.setUserGender(userGender);
				break;
			}else if(inputGender.equals("2")) {
				userGender="f";
				uDto.setUserGender(userGender);
				break;
			}else {
				System.out.println("다시 선택해주세요");
				continue;
			}
		}
		int result=uDao.addUser(uDto);
		if(result==1) {
			System.out.println("등록 완료!");
		}else {
			System.out.println("등록 실패!");
		}
		
	}

}
