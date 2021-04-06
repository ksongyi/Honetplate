package store.model.vo;

import java.sql.Date;

public class Review {

	private int review_no;
	private String review_content;
	private Date review_date;
	private int rating;
	private int board_no;
	private String member_id;
	private String review_del_yn;
	private String reviewOriginalFileName1;
	private String reviewOriginalFileName2;
	private String reviewOriginalFileName3;
	private String reviewRenamedFileName1;
	private String reviewRenamedFileName2;
	private String reviewRenamedFileName3;
	public Review() {
		super();
	}
	public Review(int review_no, String review_content, Date review_date, int rating, int board_no, String member_id,
			String review_del_yn, String reviewOriginalFileName1, String reviewOriginalFileName2,
			String reviewOriginalFileName3, String reviewRenamedFileName1, String reviewRenamedFileName2,
			String reviewRenamedFileName3) {
		super();
		this.review_no = review_no;
		this.review_content = review_content;
		this.review_date = review_date;
		this.rating = rating;
		this.board_no = board_no;
		this.member_id = member_id;
		this.review_del_yn = review_del_yn;
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
		this.reviewOriginalFileName2 = reviewOriginalFileName2;
		this.reviewOriginalFileName3 = reviewOriginalFileName3;
		this.reviewRenamedFileName1 = reviewRenamedFileName1;
		this.reviewRenamedFileName2 = reviewRenamedFileName2;
		this.reviewRenamedFileName3 = reviewRenamedFileName3;
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReview_del_yn() {
		return review_del_yn;
	}
	public void setReview_del_yn(String review_del_yn) {
		this.review_del_yn = review_del_yn;
	}
	public String getReviewOriginalFileName1() {
		return reviewOriginalFileName1;
	}
	public void setReviewOriginalFileName1(String reviewOriginalFileName1) {
		this.reviewOriginalFileName1 = reviewOriginalFileName1;
	}
	public String getReviewOriginalFileName2() {
		return reviewOriginalFileName2;
	}
	public void setReviewOriginalFileName2(String reviewOriginalFileName2) {
		this.reviewOriginalFileName2 = reviewOriginalFileName2;
	}
	public String getReviewOriginalFileName3() {
		return reviewOriginalFileName3;
	}
	public void setReviewOriginalFileName3(String reviewOriginalFileName3) {
		this.reviewOriginalFileName3 = reviewOriginalFileName3;
	}
	public String getReviewRenamedFileName1() {
		return reviewRenamedFileName1;
	}
	public void setReviewRenamedFileName1(String reviewRenamedFileName1) {
		this.reviewRenamedFileName1 = reviewRenamedFileName1;
	}
	public String getReviewRenamedFileName2() {
		return reviewRenamedFileName2;
	}
	public void setReviewRenamedFileName2(String reviewRenamedFileName2) {
		this.reviewRenamedFileName2 = reviewRenamedFileName2;
	}
	public String getReviewRenamedFileName3() {
		return reviewRenamedFileName3;
	}
	public void setReviewRenamedFileName3(String reviewRenamedFileName3) {
		this.reviewRenamedFileName3 = reviewRenamedFileName3;
	}
	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", review_content=" + review_content + ", review_date=" + review_date
				+ ", rating=" + rating + ", board_no=" + board_no + ", member_id=" + member_id + ", review_del_yn="
				+ review_del_yn + ", reviewOriginalFileName1=" + reviewOriginalFileName1 + ", reviewOriginalFileName2="
				+ reviewOriginalFileName2 + ", reviewOriginalFileName3=" + reviewOriginalFileName3
				+ ", reviewRenamedFileName1=" + reviewRenamedFileName1 + ", reviewRenamedFileName2="
				+ reviewRenamedFileName2 + ", reviewRenamedFileName3=" + reviewRenamedFileName3 + "]";
	}
	
	
	
}
