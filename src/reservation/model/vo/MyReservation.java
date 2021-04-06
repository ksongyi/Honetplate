package reservation.model.vo;

import java.sql.Timestamp;

public class MyReservation extends Reservation{
	private String storeName;

	public MyReservation() {
		super();
	}

	public MyReservation(String storeName) {
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
		return "Reservation [reservationNo=" + super.getReservationNo() + ", memberId=" + super.getMemberId() + ", boardNo=" + super.getBoardNo()
				+ ", adult=" + super.getAdult()+ ", child=" + super.getChild() + ", rsvDate=" + super.getRsvDate() + ", noShowYn=" + super.getNoShowYn()
				+ ", reservationPhone=" + super.getReservationPhone() + ", depositYn=" + super.getDepositYn() + ", reservationStatus="
				+ super.getReservationStatus() + ", noShowFreq=" + super.getNoShowFreq() + ", storeName=" + storeName + "]";
	}

}
