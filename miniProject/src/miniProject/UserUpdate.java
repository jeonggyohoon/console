package miniProject;

import java.util.ArrayList;
import java.util.Scanner;

public class UserUpdate {
	public void update(String inputId) {
		Scanner sc = new Scanner(System.in);
		userDAO uDao = new userDAO();
		ArrayList<userDTO> userList = new ArrayList<>();
		String inputPwd = null;
		while(true){
		System.out.println("1. 회원정보 수정 2. 회원탈퇴 3.돌아가기");
		System.out.print("===>");
		String inputNum = sc.nextLine();
			
			if(inputNum.equals("1")) {
				userList=uDao.userData(inputId);
				if(userList.isEmpty()) {
					System.err.println("로그인을 먼저 수행하세요");
					continue;
				}else {
					System.out.println("수정가능한 회원님의 정보입니다.");
					System.out.println("비밀번호\t\t이름\t\t이메일");
					userList.forEach(userData->{
						System.out.println(userData.getUserPwd()+"\t"
								+userData.getUserName()+"\t\t"
								+userData.getUserEmail());
					});
					userDTO uDto = new userDTO();
					String uid = userList.get(0).getUserId();
					String pwd = userList.get(0).getUserPwd();
					String uname = userList.get(0).getUserName();
					String uemail = userList.get(0).getUserEmail();
					uDto.setUserId(uid);
					while(true) {				
						System.out.println("수정하실 항목을 선택하세요");
						System.out.println("1.비밀번호 2.이름 3.이메일 4.돌아가기");
						inputNum=sc.nextLine();
						if(inputNum.equals("1")) {
							System.out.println("변경할 비밀번호를 입력하세요");
							String newPwd= sc.nextLine();
							pwd=newPwd;
						}else if(inputNum.equals("2")) {
							System.out.println("변경할 이름을 입력하세요");
							String newName= sc.nextLine();
							uname=newName;
						}else if(inputNum.equals("3")) {
							System.out.println("변경할 이메일을 입력하세요");
							String newEmail= sc.nextLine();
							uemail=newEmail;
						}else if(inputNum.equals("4")) {
							break;
						}else {
							System.err.println("다시입력하세요");
							continue;
						}
						uDto.setUserPwd(pwd);
						uDto.setUserName(uname);
						uDto.setUserEmail(uemail);
					}
					while(true) {
						System.out.println("변경사항을 저장하시겠습니까? y/n");
						System.out.print("==>");
						String check = sc.nextLine();
						check.toLowerCase();
						if(check.equals("y")) {
							uDao.updateUser(uDto);
						}else if(check.equals("n")){
							break;
						}else {
							System.err.println("다시입력하세요");
							continue;
						}
						
					}
					continue;
				}
			}else if(inputNum.equals("2")) {
				while(true) {
					System.out.println("회원님의 비밀번호를 입력하세요");
					inputPwd = sc.nextLine();
					
					if(uDao.delUser(inputId, inputPwd)>0) {
						System.out.println("정상적으로 탈퇴 되었습니다.");
						break;
					}else {
						System.err.println("탈퇴에 실패했습니다.");
						System.out.println("처음부터 다시 진행해주세요.");
						continue;
					}
				}
				continue;
				
			}else if(inputNum.equals("3")) {
				break;
			}else {
				System.out.println("잘못입력했습니다 다시 입력해주세요");
				continue;
			}
		}
	}
}
