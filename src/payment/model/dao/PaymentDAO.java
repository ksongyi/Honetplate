package payment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import payment.model.vo.Payment;

public class PaymentDAO {
	private Properties prop = new Properties();
	
	public PaymentDAO() {
		String fileName = "/sql/payment/payment-query.properties";
		String path = PaymentDAO.class.getResource(fileName).getPath();
		
		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertPayment(Connection conn, Payment py) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPayment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, py.getMerchantUid());
			pstmt.setString(2, py.getMemberId());
			pstmt.setInt(3, py.getBoardNo());
			pstmt.setString(4, py.getBuyerName());
			pstmt.setString(5, py.getBuyerTel());
			pstmt.setString(6, py.getBuyerEmail());
			pstmt.setInt(7, py.getAmount());
			pstmt.setString(8, py.getPayMethod());
			pstmt.setString(9, py.getPg());
			pstmt.setInt(10, py.getReservationNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
