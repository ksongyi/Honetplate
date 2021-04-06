package search.model.vo;

public class RatingList {

	private int avgRating;
	private int boardNo;
	private String storeName;
	
	public RatingList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RatingList(int avgRating, int boardNo, String storeName) {
		super();
		this.avgRating = avgRating;
		this.boardNo = boardNo;
		this.storeName = storeName;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
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

	@Override
	public String toString() {
		return "RatingList [avgRating=" + avgRating + ", boardNo=" + boardNo + ", storeName=" + storeName + "]";
	}
	
	
}
