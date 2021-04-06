package payment.model.service;

import payment.model.dao.PaymentDAO;
import payment.model.vo.Payment;

import static common.JDBCTemplate.*;

import java.sql.Connection;

public class PaymentService {
	private PaymentDAO paymentDao = new PaymentDAO();

	public int insertPayment(Payment py) {
		Connection conn = getConnection();
		int result = paymentDao.insertPayment(conn, py);
		commitOrRollBack(result, conn);
		return result;
	}
}
