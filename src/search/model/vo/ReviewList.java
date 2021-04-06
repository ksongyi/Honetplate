package search.model.vo;

public class ReviewList {

	private int reviewCount;
	private int boardNo;
	private int boardNo1;
	private String storeName;
	
	public ReviewList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ReviewList(int reviewCount, int boardNo, int boardNo1, String storeName) {
		super();
		this.reviewCount = reviewCount;
		this.boardNo = boardNo;
		this.boardNo1 = boardNo1;
		this.storeName = storeName;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardNo1() {
		return boardNo1;
	}
	public void setBoardNo1(int boardNo1) {
		this.boardNo1 = boardNo1;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	@Override
	public String toString() {
		return "ReviewList [reviewCount=" + reviewCount + ", boardNo=" + boardNo + ", boardNo1=" + boardNo1
				+ ", storeName=" + storeName + "]";
	}
	
	
	
	
}
