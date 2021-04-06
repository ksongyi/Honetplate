package reservation.model.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reservation {
	private int reservationNo; //예약 번호
	private String memberId; //예약자 아이디
	private int boardNo; //예약 가게 번호
	private int adult; //어른 인원
	private int child; //어린이 인원
	private Timestamp rsvDate; //예약일
	private String noShowYn; //예약자의 노쇼 여부
	private String reservationPhone; //예약자의 휴대폰 번호
	private String depositYn; //결제여부  
	private String reservationStatus; //예약 승인 거절 여부 
	private int noShowFreq;

	
	public Reservation() {
		super();
	}

	public Reservation(int reservationNo, String memberId, int boardNo, int adult, int child, Timestamp rsvDate,
			String noShowYn, String reservationPhone, String depositYn, String reservationStatus, int noShowFreq) {
		super();
		this.reservationNo = reservationNo;
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.adult = adult;
		this.child = child;
		this.rsvDate = rsvDate;
		this.noShowYn = noShowYn;
		this.reservationPhone = reservationPhone;
		this.depositYn = depositYn;
		this.reservationStatus = reservationStatus;
		this.noShowFreq = noShowFreq;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
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

	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}

	public int getChild() {
		return child;
	}

	public void setChild(int child) {
		this.child = child;
	}

	public Timestamp getRsvDate() {
		return rsvDate;
	}

	public void setRsvDate(Timestamp rsvDate) {
		this.rsvDate = rsvDate;
	}

	public String getReservationPhone() {
		return reservationPhone;
	}

	public void setReservationPhone(String reservationPhone) {
		this.reservationPhone = reservationPhone;
	}

	public String getDepositYn() {
		return depositYn;
	}

	public void setDepositYn(String depositYn) {
		this.depositYn = depositYn;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
	public int getNoShowFreq() {
		return noShowFreq;
	}

	public void setNoShowFreq(int noShowFreq) {
		this.noShowFreq = noShowFreq;
	}

	public String getNoShowYn() {
		return noShowYn;
	}

	public void setNoShowYn(String noShowYn) {
		this.noShowYn = noShowYn;
	} 

	@Override
	public String toString() {
		return "Reservation [reservationNo=" + reservationNo + ", memberId=" + memberId + ", boardNo=" + boardNo
				+ ", adult=" + adult + ", child=" + child + ", rsvDate=" + rsvDate + ", noShowYn=" + noShowYn
				+ ", reservationPhone=" + reservationPhone + ", depositYn=" + depositYn + ", reservationStatus="
				+ reservationStatus + ", noShowFreq=" + noShowFreq + "]";
	}

	public String timestampToString(){
		java.util.Date date= new Date(rsvDate.getTime());
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd EE HH:ss");
		return sdf.format(date);
		
	}


}
