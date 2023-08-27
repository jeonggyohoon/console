
package board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BoardMain {

	public void board(String userId) {
		// 수정 변수
		String boardTitle = null;
		String boardContent = null;
		// 로그인 체크 변수
		int result = 0;

		// 매뉴선택 변수
		String inputNum = null;

		// 입력 변수
		String keyWord = null;
		String inputNo = null;
		String correctNo = null;
		String deleteNo = null;
		String correctTitle = null;
		String correctContent = null;

		// 스캐너 객체 생성
		Scanner sc = new Scanner(System.in);

		// 메인
		while (true) {
			System.out.println("================================================");
			System.out.println();
			System.out.println("                 메뉴를 선택해 주세요");
			System.out.println("        *게시글 삭제와 수정은 조회(2번) 후 가능합니다.*");
			System.out.println("================================================");
			System.out.println("------------------------------------------------");
			System.out.println();
			System.out.println(" 1.게시판 등록 2.게시판 조회 3.나가기");
			System.out.println("------------------------------------------------");
			System.out.println();
			System.out.print(" 원하시는 번호를 골라주세요 : ");
			inputNum = sc.nextLine();
			System.out.println("------------------------------------------------");

			if (inputNum.equals("1")) {
				if (userId == null) {
					System.err.println("로그인을 해야 이용가능한 기능입니다.");
					System.out.println();
				} else {

					System.out.println("================================================");
					System.out.println();
					System.out.println("게시판 등록 기능입니다 제목과 게시글을 작성해주세요");
					System.out.println();
					System.out.println("================================================");

					while (true) {
						System.out.println("제목을 작성해세요");
						boardTitle = sc.nextLine();

						if (boardTitle.equals("") || boardTitle.length() > 20) {
							System.out.println("제목은 20자 이하로 작성해주세요^^");
							continue;
						} else {
							break;
						}
					}

					while (true) {
						System.out.println("게시글을 작성해주세요");
						boardContent = sc.nextLine();

						if (boardContent.equals("")) {
							continue;
						} else {
							break;
						}

					}

					if (!boardTitle.equals("") && !boardContent.equals("")) {

						BoardDTO bDto = new BoardDTO();
						bDto.setUserId(userId);
						bDto.setBoardTitle(boardTitle);
						bDto.setBoardContent(boardContent);

						BoardDAO bDao4 = new BoardDAO();

						result = bDao4.insert(bDto);

						if (result > 0) {
							System.out.println("------------------------------------------------");
							System.out.println("정상등록 됐습니다^^");
							System.out.println();
							System.out.println("------------------------------------------------");
						} else {
							System.out.println(" ... 등록이 실패했습니다 ㅠㅠ");
							System.out.println();
							System.out.println("------------------------------------------------");
						}

					}
				}

			} else if (inputNum.equals("2")) {
				System.out.println("================================================");
				System.out.println();
				System.out.println(" 게시글 조회 기능입니다. 제목의 키워드를 통해 관련 게시글을 조회합니다. ");
				System.out.println();
				System.out.println("================================================");
				xx: while (true) {
					System.out.println("------------------------------------------------");
					System.out.println();
					System.out.println("1.찾으시는 게시글 키워드를 입력하세요^^ 2.돌아가기");
					keyWord = sc.nextLine();
					System.out.println("------------------------------------------------");

					if (keyWord.equals("1")) {
						System.out.println("키워드==>");
						String keyWord2 = sc.nextLine();
						ArrayList<BoardDTO> boardlist = new ArrayList<>();

						BoardDAO bDao = new BoardDAO();
						boardlist = bDao.search(keyWord2);

						if (boardlist.isEmpty()) {
							System.out.println();
							System.out.println("찾으시는 게시글이 없어요 ㅠㅠ");
							continue;
						} else {
							System.out.println();
							System.out.println("--< " + keyWord2 + " >-- 와 관련된 게시글을 가져옵니당");
							System.out.println("------------------------------------------------");

							System.out.println("====================================================================");
							System.out.println();
							System.out.println("게시글번호\t제목\t\t\t게시글 내용\t\t작성자\t작성일");
							for (int i = 0; i < boardlist.size(); i++) {
								System.out.println(boardlist.get(i).getBoardNo() + "\t"
										+ boardlist.get(i).getBoardTitle() + "\t" + boardlist.get(i).getBoardContent()
										+ "..." + "\t" + boardlist.get(i).getUserId() + "\t"
										+ boardlist.get(i).getCreatDate());
							}
							System.out.println();
							System.out.println("====================================================================");
						}
					} else if (keyWord.equals("2")) {
						break;
					} else {
						System.out.println("다시입력하세요");
						continue;
					}
					while (true) {
						System.out.println("------------------------------------------------");
						System.out.println();
						System.out.println("1.게시글 상세 조회 2.게시글 수정 3.게시글 삭제 4.다른글 조회");
						System.out.println("------------------------------------------------");
						System.out.println();
						System.out.print("원하시는 메뉴번호를 선택해주세요 : ");
						inputNum = sc.nextLine();
						System.out.println("------------------------------------------------");
						if (inputNum.equals("1")) {
							while (true) {
								System.out.println();
								System.out.print("조회하실 게시글 번호를 입력해주세요 : ");
								inputNo = sc.nextLine();
								System.out.println("------------------------------------------------");
								if (inputNo.equals("")) {
									continue;
								} else {
									ArrayList<BoardDTO> boardlist1 = null;

									BoardDAO bDao = new BoardDAO();
									boardlist1 = bDao.detail(inputNo);

									if (boardlist1.size() > 0) {
										System.out.println("================================================");
										System.out.println();
										System.out.println("게시글 번호 : " + boardlist1.get(0).getBoardNo());
										System.out.println("게시글 제목 : " + boardlist1.get(0).getBoardTitle());
										System.out.println();
										System.out.println("게시글");
										System.out.println(boardlist1.get(0).getBoardContent());
										System.out.println();
										System.out.println("게시글 아이디 : " + boardlist1.get(0).getUserId());
										System.out.println("게시글 작성일 : " + boardlist1.get(0).getCreatDate());
										System.out.println();
										System.out.println("================================================");

										break;
									} else {
										System.out.println("잘못된 게시글 번호입니다.");
										continue;
									}

								}
							}
						} else if (inputNum.equals("2")) {
							if (userId == null) {
								System.err.println("로그인을 해야 이용가능한 기능입니다.");
								System.out.println();
							} else {

								System.out.println("================================================");
								System.out.println();
								System.out.println(" 게시글 수정 기능입니다. ");
								System.out.println();
								System.out.println("================================================");

								ArrayList<BoardDTO> boardlist = new ArrayList<>();
								BoardDAO bDao1 = new BoardDAO();
								boardlist = bDao1.search1(userId);

								System.out.println(
										"====================================================================");
								System.out.println();
								System.out.println("게시글번호\t제목\t\t\t게시글 내용\t\t작성자\t작성일");
								for (int i = 0; i < boardlist.size(); i++) {
									System.out.println(boardlist.get(i).getBoardNo() + "\t"
											+ boardlist.get(i).getBoardTitle() + "\t"
											+ boardlist.get(i).getBoardContent() + "..." + "\t"
											+ boardlist.get(i).getUserId() + "\t" + boardlist.get(i).getCreatDate());
								}
								System.out.println();
								System.out.println(
										"====================================================================");

								while (true) {
									System.out.println("------------------------------------------------");
									System.out.print("수정할 게시글 번호를 고르세요 ");
									correctNo = sc.nextLine();
									System.out.println();
									System.out.println("------------------------------------------------");

									if (correctNo.equals("")) {
										continue;
									} else {

										BoardDAO bDao6 = new BoardDAO();
										result = bDao6.correctMatch(correctNo, userId);

										if (result == 0 || result == -1) {
											System.out.println("고객님이 작성하신 게시글만 수정 가능합니다.");
											continue;
										} else {

											while (true) {
												System.out.println("변경할 제목 입력");
												correctTitle = sc.nextLine();
												System.out.println();
												System.out.println("------------------------------------------------");

												if (correctTitle.equals("")) {
													continue;
												} else {
													break;
												}
											}

											while (true) {
												System.out.println("변경할 내용 입력");
												correctContent = sc.nextLine();
												System.out.println();
												System.out.println("------------------------------------------------");

												if (correctContent.equals("") || correctContent.length() > 20) {
													System.out.println("변경할 제목은 20자 이하로 작성해주세요^^");
													continue;
												} else {
													break;
												}
											}

											if (!correctTitle.equals("") && !correctContent.equals("")) {

												BoardDTO bDto = new BoardDTO();
												bDto.setBoardNo(correctNo);
												bDto.setBoardTitle(correctTitle);
												bDto.setBoardContent(correctContent);

												BoardDAO bDao2 = new BoardDAO();

												result = bDao2.correct(bDto);

												if (result > 0) {
													System.out.println("정상 수정 됬어요 ^^");
													System.out.println();
													System.out.println(
															"------------------------------------------------");
													continue xx;
												} else {
													System.out.println("수정 실패 ㅜㅜ");
													System.out.println();
													System.out.println(
															"------------------------------------------------");
													continue;
												}

											}
										}

									}
								}
							}

						} else if (inputNum.equals("3")) {
							if (userId == null) {
								System.err.println("로그인을 해야 이용가능한 기능입니다.");
								System.out.println();
							} else {

								System.out.println("================================================");
								System.out.println();
								System.out.println(" 게시글 삭제 기능입니다. ");
								System.out.println();
								System.out.println("================================================");

								ArrayList<BoardDTO> boardlist = new ArrayList<>();
								BoardDAO bDao1 = new BoardDAO();
								boardlist = bDao1.search1(userId);

								System.out.println(
										"====================================================================");
								System.out.println();
								System.out.println("게시글번호\t제목\t\t\t게시글 내용\t\t작성자\t작성일");
								for (int i = 0; i < boardlist.size(); i++) {
									System.out.println(boardlist.get(i).getBoardNo() + "\t"
											+ boardlist.get(i).getBoardTitle() + "\t"
											+ boardlist.get(i).getBoardContent() + "..." + "\t"
											+ boardlist.get(i).getUserId() + "\t" + boardlist.get(i).getCreatDate());
								}
								System.out.println();
								System.out.println(
										"====================================================================");

								while (true) {
									System.out.println("------------------------------------------------");
									System.out.print("삭제할 게시글 번호를 고르세요 ");
									deleteNo = sc.nextLine();
									System.out.println();
									System.out.println("------------------------------------------------");
									if (deleteNo.equals("")) {
										continue;
									} else {
										BoardDAO bDao6 = new BoardDAO();
										result = bDao6.correctMatch(deleteNo, userId);

										if (result == 0 || result == -1) {
											System.out.println("고객님이 작성하신 게시글만 삭제 가능합니다.");
											continue;
										} else {

											BoardDTO bDto = new BoardDTO();
											bDto.setBoardNo(deleteNo);
											BoardDAO bDao3 = new BoardDAO();

											result = bDao3.delete(bDto);

											if (result > 0) {
												System.out.println("삭제 되었습니다.");
												System.out.println();
												System.out.println("------------------------------------------------");
												continue xx;
											} else {
												System.out.println("삭제에 실패하셨어요 ㅠㅠ");
												System.out.println();
												System.out.println("------------------------------------------------");
												continue;
											}
										}

									}
								}
							}

						} else if (inputNum.equals("4")) {
							break;
						} else {
							System.out.println("1 ~ 4의 번호 중 선택해 주세요^^");
							continue;
						}
					}

				}

			} else if (inputNum.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 경로입니다. 1 ~ 3번 번호를 입력해주세요^^");
				continue;

			}
		}

	}
}
