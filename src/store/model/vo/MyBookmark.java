package store.model.vo;

import java.util.List;

public class MyBookmark extends Bookmark{
	private String fileName;
	private String storeName;
	private String hashtag1;
	private String hashtag2;
	private String hashtag3;
	
	public MyBookmark() {
		super();
	}
	
	public MyBookmark(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getHashtag1() {
		return hashtag1;
	}

	public void setHashtag1(String hashtag1) {
		this.hashtag1 = hashtag1;
	}

	public String getHashtag2() {
		return hashtag2;
	}

	public void setHashtag2(String hashtag2) {
		this.hashtag2 = hashtag2;
	}

	public String getHashtag3() {
		return hashtag3;
	}

	public void setHashtag3(String hashtag3) {
		this.hashtag3 = hashtag3;
	}

	@Override
	public String toString() {
		return "Bookmark [bookmakrkNo=" + super.getBookmakrkNo() 
				+ ", boardNo=" + super.getBoardNo() 
				+ ", memberId=" + super.getMemberId() 
				+ ", fileName=" + fileName 
				+ ", storeName=" + storeName
				+ ", hashtag1=" + hashtag1 
				+ ", hashtag2=" + hashtag2
				+ ", hashtag3=" + hashtag3
				+ "]";
	}
	
	
}
