package store.model.vo;

import java.sql.Date;

//마이페이지에서 출력할 리뷰객체
//Review 클래스 상속 
public class MyReview extends Review{
	private String storeName;

	public MyReview() {
		super();
	}

	public MyReview(String storeName) {
		super();
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public String toString() {
		return "Review [review_no=" + super.getBoard_no() + ", review_content=" + super.getReview_content() + ", review_date=" + super.getReview_date()
				+ ", rating=" + super.getRating() + ", board_no=" + super.getBoard_no() + ", member_id=" + super.getMember_id() + ", review_del_yn="
				+ super.getReview_del_yn() + ",  storeName=" + storeName +  "]";
	}
}
