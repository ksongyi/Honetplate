package search.model.vo;

public class ReservationRankList {

	private int boardNo;
	private String storeName;
	private int reservationNum;
	private String reviewOriginalFileName1;
	
	public ReservationRankList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationRankList(int boardNo, String storeName, int reservationNum, String reviewOriginalFileName1) {
		super();
		this.boardNo = boardNo;
		this.storeName = storeName;
		this.reservationNum = reservationNum;
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(int reservationNum) {
		this.reservationNum = reservationNum;
	}

	public String getReviewOriginalFileName1() {
		return reviewOriginalFileName1;
	}

	public void setReviewOriginalFileName1(String reviewOriginalFileName1) {
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
	}

	@Override
	public String toString() {
		return "ReservationRankList [boardNo=" + boardNo + ", storeName=" + storeName + ", reservationNum="
				+ reservationNum + ", reviewOriginalFileName1=" + reviewOriginalFileName1 + "]";
	}
	
	
	
	
}
