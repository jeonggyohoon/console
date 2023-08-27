package main;

import java.util.Scanner;

import board.BoardMain;
import manClothe.musinsaMan;
import miniProject.UserMain;
import womanClothe.musinsaWoman;


public class shopMallMain {
	
	public static void main(String[] args) {
		String userId = null;
		UserMain user = new UserMain();
		musinsaMan manClothe = new musinsaMan();
		musinsaWoman womanClothe = new musinsaWoman();
		BoardMain board = new BoardMain();
		Scanner sc = new Scanner(System.in);
		System.out.println("######################################################################");
		System.out.println();
		System.out.println("     ██╗ █████╗ ██╗   ██╗ █████╗   ██████╗ ██╗	     ████═╗  ██████╗	\r\n"
						 + "     ██║██╔══██╗██║   ██║██╔══██╗ ██╔════╝ ██╚═══╗  ██  ██╚╗ ██  ██║\r\n"
						 + "     ██║███████║██║   ██║███████║  ██████═╗██████║ ██    ██║ ██████║\r\n"
						 + "██   ██║██╔══██║╚██╗ ██╔╝██╔══██║       ██║██╔═██║ ╚██  ██╔╝ ██╔═══╝\r\n"
						 + "╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║  ██████╔╝██║ ██║  ╚████╔╝  ██║	   \r\n"
						 + " ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝  ╚═════╝ ╚═╝ ╚═╝   ╚═══╝   ╚═╝    ");
		System.out.println();
	    System.out.println("#####################################################################");
	
	    while (true) {
	    System.out.println();
		System.out.println("-----------------<< WELCOME TO Java Shop! >>------------------");
		System.out.println();
		System.out.println("  1.회원관리   2.여성복   3.남성복   4. 게시판   5.시스템 종료");
		System.out.println();
		System.out.println("=============================================================");
		System.out.println();
				System.out.print("필요한 메뉴를 선택해주세요 : ");
				int inputnum = sc.nextInt();
				sc.nextLine();
				
				if(inputnum == 1) {
					userId = user.user();
					continue;
				}else if(inputnum == 2) {
					womanClothe.womanClothe();
				}else if(inputnum == 3) {
					manClothe.manClothe();
				}else if(inputnum == 4) {
					board.board(userId);
				}else if(inputnum == 5) {
					System.exit(0);
				}else {
					System.out.println("똑디 입력하시오");
					continue;
				}
			}
	}
	
	
	
	
	
	}