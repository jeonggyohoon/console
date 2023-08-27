package miniProject;

import java.util.Scanner;

public class UserMain {
	public String user() {
		String userId = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("========================");
			System.out.println("1.회원가입 2.로그인 3.돌아가기");
			System.out.println("========================");
			System.out.println("메뉴를 입력하세요===>");
			String inputNum = sc.nextLine();
			if (inputNum.equals("1")) {
				UserJoin join = new UserJoin();
				join.Join();
				continue;
			} else if (inputNum.equals("2")) {
				UserLogin login = new UserLogin();
				userId = login.login();
				continue;
			} else if (inputNum.equals("3")) {
				break;
			} else {
				System.err.println("잘못된 숫자입니다. 다시 입력하세요.");
				continue;

			}

		}
		return userId;
	}
}
