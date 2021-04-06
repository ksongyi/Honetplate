package payment.model.vo;

import java.sql.Date;

public class Payment {
	
	/*
	 *  MERCHANT_UID   NOT NULL VARCHAR2(25) 
		MEMBER_ID      NOT NULL VARCHAR2(15) 
		BOARD_NO       NOT NULL NUMBER       
		BUYER_NAME     NOT NULL VARCHAR2(30) 
		BUYER_TEL      NOT NULL CHAR(11)     
		BUYER_EMAIL             VARCHAR2(50) 
		AMOUNT         NOT NULL NUMBER       
		PAY_METHOD     NOT NULL VARCHAR2(10) 
		PAYMENT_DATE   NOT NULL DATE         
		PG             NOT NULL VARCHAR2(30) 
		REFUND_YN               VARCHAR2(1)  
		RESERVATION_NO NOT NULL VARCHAR2(1)  
	 */
	
	private String merchantUid;
	private String memberId;
	private int boardNo;
	private String buyerName;
	private String buyerTel;
	private String buyerEmail;
	private int amount;
	private String payMethod;
	private Date paymentDate;
	private String pg;
	private String refundYn;
	private int reservationNo;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(String merchantUid, String memberId, int boardNo, String buyerName, String buyerTel,
			String buyerEmail, int amount, String payMethod, Date paymentDate, String pg, String refundYn,
			int reservationNo) {
		super();
		this.merchantUid = merchantUid;
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.buyerName = buyerName;
		this.buyerTel = buyerTel;
		this.buyerEmail = buyerEmail;
		this.amount = amount;
		this.payMethod = payMethod;
		this.paymentDate = paymentDate;
		this.pg = pg;
		this.refundYn = refundYn;
		this.reservationNo = reservationNo;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerTel() {
		return buyerTel;
	}

	public void setBuyerTel(String buyerTel) {
		this.buyerTel = buyerTel;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPg() {
		return pg;
	}

	public void setPg(String pg) {
		this.pg = pg;
	}

	public String getRefundYn() {
		return refundYn;
	}

	public void setRefundYn(String refundYn) {
		this.refundYn = refundYn;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	@Override
	public String toString() {
		return "Payment@VO [merchantUid=" + merchantUid + ", memberId=" + memberId + ", boardNo=" + boardNo
				+ ", buyerName=" + buyerName + ", buyerTel=" + buyerTel + ", buyerEmail=" + buyerEmail + ", amount="
				+ amount + ", payMethod=" + payMethod + ", paymentDate=" + paymentDate + ", pg=" + pg + ", refundYn="
				+ refundYn + ", reservationNo=" + reservationNo + "]";
	}

	
	
}
