package store.model.vo;

public class Store {

	private int board_no;
	private String member_id;
	private String store_name;
	private String store_phone;
	private String location;
	private String closing_day;
	private String open_hours;
	private String close_hours;
	private String breaktime_open;
	private String breaktime_close;
	private String menu;
	private String parking;
	private int reservation_num;
	private int view_num;
	private String info_del_yn;
	public Store() {
		super();
		
	}
	public Store(int board_no, String member_id, String store_name, String store_phone, String location,
			String closing_day, String open_hours, String close_hours, String breaktime_open, String breaktime_close,
			String menu, String parking, int reservation_num, int view_num, String info_del_yn) {
		super();
		this.board_no = board_no;
		this.member_id = member_id;
		this.store_name = store_name;
		this.store_phone = store_phone;
		this.location = location;
		this.closing_day = closing_day;
		this.open_hours = open_hours;
		this.close_hours = close_hours;
		this.breaktime_open = breaktime_open;
		this.breaktime_close = breaktime_close;
		this.menu = menu;
		this.parking = parking;
		this.reservation_num = reservation_num;
		this.view_num = view_num;
		this.info_del_yn = info_del_yn;
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
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClosing_day() {
		return closing_day;
	}
	public void setClosing_day(String closing_day) {
		this.closing_day = closing_day;
	}
	public String getOpen_hours() {
		return open_hours;
	}
	public void setOpen_hours(String open_hours) {
		this.open_hours = open_hours;
	}
	public String getClose_hours() {
		return close_hours;
	}
	public void setClose_hours(String close_hours) {
		this.close_hours = close_hours;
	}
	public String getBreaktime_open() {
		return breaktime_open;
	}
	public void setBreaktime_open(String breaktime_open) {
		this.breaktime_open = breaktime_open;
	}
	public String getBreaktime_close() {
		return breaktime_close;
	}
	public void setBreaktime_close(String breaktime_close) {
		this.breaktime_close = breaktime_close;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public int getReservation_num() {
		return reservation_num;
	}
	public void setReservation_num(int reservation_num) {
		this.reservation_num = reservation_num;
	}
	public int getView_num() {
		return view_num;
	}
	public void setView_num(int view_num) {
		this.view_num = view_num;
	}
	public String getInfo_del_yn() {
		return info_del_yn;
	}
	public void setInfo_del_yn(String info_del_yn) {
		this.info_del_yn = info_del_yn;
	}
	@Override
	public String toString() {
		return "Store [board_no=" + board_no + ", member_id=" + member_id + ", store_name=" + store_name
				+ ", store_phone=" + store_phone + ", location=" + location + ", closing_day=" + closing_day
				+ ", open_hours=" + open_hours + ", close_hours=" + close_hours + ", breaktime_open=" + breaktime_open
				+ ", breaktime_close=" + breaktime_close + ", menu=" + menu + ", parking=" + parking
				+ ", reservation_num=" + reservation_num + ", view_num=" + view_num + ", info_del_yn=" + info_del_yn
				+ "]";
	}

	
}