package search.model.vo;

public class ViewList {

	private int boardNo;
	private String storeName;
	
	public ViewList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ViewList(int boardNo, String storeName) {
		super();
		this.boardNo = boardNo;
		this.storeName = storeName;
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
		return "ViewList [boardNo=" + boardNo + ", storeName=" + storeName + "]";
	}
	
	
	
}
