package member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberRole;
	private Date birthDay;
	private String email;
	private String phone;
	private Date enrollDate;
	private String quitYn;
	private int noshowFreq;
	private String corporateNo;
	
	public Member() {
		super();
	}

	public Member(String memberId, String memberPassword, String memberName, String memberRole, Date birthDay,
			String email, String phone, Date enrollDate, String quitYn, int noshowFreq, String corporateNo) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberRole = memberRole;
		this.birthDay = birthDay;
		this.email = email;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.quitYn = quitYn;
		this.noshowFreq = noshowFreq;
		this.corporateNo = corporateNo;
	}



	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getQuitYn() {
		return quitYn;
	}

	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}

	public int getNoshowFreq() {
		return noshowFreq;
	}

	public void setNoshowFreq(int noshowFreq) {
		this.noshowFreq = noshowFreq;
	}

	public String getCorporateNo() {
		return corporateNo;
	}

	public void setCorporateNo(String corporateNo) {
		this.corporateNo = corporateNo;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPassword=" + memberPassword + ", memberName=" + memberName
				+ ", memberRole=" + memberRole + ", birthDay=" + birthDay + ", email=" + email + ", phone=" + phone
				+ ", enrollDate=" + enrollDate + ", quitYn=" + quitYn + ", noshowFreq=" + noshowFreq + ", corporateNo="
				+ corporateNo + "]";
	}
}
