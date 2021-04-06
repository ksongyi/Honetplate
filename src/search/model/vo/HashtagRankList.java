package search.model.vo;

public class HashtagRankList {
	
	private int boardNo;
	private String storeName;
	private String hashtagKeyword;
	private int total;
	private String reviewOriginalFileName1;
	
	public HashtagRankList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HashtagRankList(int boardNo, String storeName, String hashtagKeyword, int total,
			String reviewOriginalFileName1) {
		super();
		this.boardNo = boardNo;
		this.storeName = storeName;
		this.hashtagKeyword = hashtagKeyword;
		this.total = total;
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

	public String getHashtagKeyword() {
		return hashtagKeyword;
	}

	public void setHashtagKeyword(String hashtagKeyword) {
		this.hashtagKeyword = hashtagKeyword;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getReviewOriginalFileName1() {
		return reviewOriginalFileName1;
	}

	public void setReviewOriginalFileName1(String reviewOriginalFileName1) {
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
	}

	@Override
	public String toString() {
		return "HashtagRankList [boardNo=" + boardNo + ", storeName=" + storeName + ", hashtagKeyword=" + hashtagKeyword
				+ ", total=" + total + ", reviewOriginalFileName1=" + reviewOriginalFileName1 + "]";
	}
	
	 
	
	
	

}
