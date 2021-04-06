package store.model.vo;

public class Bookmark {
	private int bookmakrkNo;
	private int boardNo;
	private String memberId;
	
	public Bookmark() {
		super();
	}

	public Bookmark(int bookmakrkNo, int boardNo, String memberId) {
		super();
		this.bookmakrkNo = bookmakrkNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
	}

	public int getBookmakrkNo() {
		return bookmakrkNo;
	}

	public void setBookmakrkNo(int bookmakrkNo) {
		this.bookmakrkNo = bookmakrkNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Bookmark [bookmakrkNo=" + bookmakrkNo + ", boardNo=" + boardNo + ", memberId=" + memberId + "]";
	}
	
	
	
	
}
