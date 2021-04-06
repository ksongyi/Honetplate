package search.model.vo;

public class NewList {

	private int boardNo;
	private String storeName;
	
	public NewList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewList(int boardNo, String storeName) {
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
		return "NewList [boardNo=" + boardNo + ", storeName=" + storeName + "]";
	}
	
	
	
	
	
	
}
