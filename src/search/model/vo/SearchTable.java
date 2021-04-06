package search.model.vo;

public class SearchTable {

	private int viewNum;
	private String location;
	private String storeName;
	private String menu;
	private String hashtagKeyword1;
	private String hashtagKeyword2;
	private String hashtagKeyword3;
	private String reviewOriginalFileName1;
	private int boardNo;
	
	public SearchTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchTable(int viewNum, String location, String storeName, String menu, String hashtagKeyword1,
			String hashtagKeyword2, String hashtagKeyword3, String reviewOriginalFileName1, int boardNo) {
		super();
		this.viewNum = viewNum;
		this.location = location;
		this.storeName = storeName;
		this.menu = menu;
		this.hashtagKeyword1 = hashtagKeyword1;
		this.hashtagKeyword2 = hashtagKeyword2;
		this.hashtagKeyword3 = hashtagKeyword3;
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
		this.boardNo = boardNo;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getHashtagKeyword1() {
		return hashtagKeyword1;
	}

	public void setHashtagKeyword1(String hashtagKeyword1) {
		this.hashtagKeyword1 = hashtagKeyword1;
	}

	public String getHashtagKeyword2() {
		return hashtagKeyword2;
	}

	public void setHashtagKeyword2(String hashtagKeyword2) {
		this.hashtagKeyword2 = hashtagKeyword2;
	}

	public String getHashtagKeyword3() {
		return hashtagKeyword3;
	}

	public void setHashtagKeyword3(String hashtagKeyword3) {
		this.hashtagKeyword3 = hashtagKeyword3;
	}

	public String getReviewOriginalFileName1() {
		return reviewOriginalFileName1;
	}

	public void setReviewOriginalFileName1(String reviewOriginalFileName1) {
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "SearchTable [viewNum=" + viewNum + ", location=" + location + ", storeName=" + storeName + ", menu="
				+ menu + ", hashtagKeyword1=" + hashtagKeyword1 + ", hashtagKeyword2=" + hashtagKeyword2
				+ ", hashtagKeyword3=" + hashtagKeyword3 + ", reviewOriginalFileName1=" + reviewOriginalFileName1
				+ ", boardNo=" + boardNo + "]";
	}
	
	
	
	
	
	
		
}
	
	
	
	

