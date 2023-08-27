package board;

public class BoardDTO {
	
	// DB board table 클럽명
	String boardNo;
	String boardTitle;
	String boardContent;
	String userId;
	String creatDate;
	
	//생성자
	public BoardDTO() {
		
	}
	
	//using field 
	public BoardDTO(String boardNo, String boardTitle, String boardContent, String userId, String creatDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.userId = userId;
		this.creatDate = creatDate;
	}
	
	// setter getter
	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	
	// toString 오버라이딩 재선언
	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", userId=" + userId + ", creatDate=" + creatDate + "]";
	}

	
	
}