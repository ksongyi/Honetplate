package store.model.dao;
import static common.JDBCTemplate.close;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import store.model.vo.Review;

import store.model.vo.Store;


public class StoreDAO {
	private Properties prop = new Properties();
	
	public StoreDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			String fileName = StoreDAO.class.getResource("/sql/store/store-query.properties").getPath();
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public Store selectOne(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		//select * from store_info where board_no = ?
		Store s = null;
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			while(rset.next()){
				s = new Store();
				//컬럼명은 대소문자 구분이 없다.
				s.setBoard_no(rset.getInt("board_no"));
				s.setMember_id(rset.getString("member_id"));
				s.setStore_name(rset.getString("store_name"));
				s.setStore_phone(rset.getString("store_phone"));
				s.setLocation(rset.getString("location"));
				s.setClosing_day(rset.getString("closing_day"));
				s.setOpen_hours(rset.getString("open_hours"));
				s.setClose_hours(rset.getString("close_hours"));
				s.setBreaktime_open(rset.getString("breaktime_open"));
				s.setBreaktime_close(rset.getString("breaktime_close"));
				s.setMenu(rset.getString("menu"));
				s.setParking(rset.getString("parking"));
				s.setReservation_num(rset.getInt("reservation_num"));
				s.setView_num(rset.getInt("view_num"));
				s.setInfo_del_yn(rset.getString("info_del_yn"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return s;
	}

	public List<Review> selectreviewOne(Connection conn, int boardNo,int cpage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectreviewPagedList");
		sql = sql.replace("#", boardNo+"");

		//select * from ( select S.*, row_number() over(order by review_date desc) rnum 
		//from review S where board_no = '#') S  
		//where  rnum between ? and ? and board_no = ?
		List<Review> list = null;
		try {
			//1. PreparedStatement객체 생성
			//2. 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cpage - 1) * numPerPage + 1);
			pstmt.setInt(2, cpage * numPerPage);
			pstmt.setInt(3, boardNo);

			//3. 실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			//4. Member --> List에 추가
			list = new ArrayList<>();
			while(rset.next()) {
				Review review = new Review();
				review.setReview_no(rset.getInt("review_no"));
				review.setReview_content(rset.getString("review_content"));
				review.setReview_date(rset.getDate("review_date"));
				review.setRating(rset.getInt("rating"));
				review.setBoard_no(rset.getInt("board_no"));
				review.setMember_id(rset.getString("member_id"));
				review.setReview_del_yn(rset.getString("review_del_yn"));
				review.setReviewOriginalFileName1(rset.getString("ReviewOriginalFileName1"));
				review.setReviewOriginalFileName2(rset.getString("ReviewOriginalFileName2"));
				review.setReviewOriginalFileName3(rset.getString("ReviewOriginalFileName3"));
				review.setReviewRenamedFileName1(rset.getString("ReviewRenamedFileName1"));
				review.setReviewRenamedFileName2(rset.getString("ReviewRenamedFileName2"));
				review.setReviewRenamedFileName3(rset.getString("ReviewRenamedFileName3"));
				list.add(review);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	
	public int insertReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertReview");
//		String query = prop.getProperty("insertImg");
		//insertReview = 
		//insert into review values(seq_review_no.nextval, ?, default, ?, ?, ?, default)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReview_content());
			pstmt.setInt(2, review.getRating());
			pstmt.setInt(3, review.getBoard_no());
			pstmt.setString(4, review.getMember_id());
			pstmt.setString(5, review.getReviewOriginalFileName1());
			pstmt.setString(6, review.getReviewOriginalFileName2());
			pstmt.setString(7, review.getReviewOriginalFileName3());
			pstmt.setString(8, review.getReviewRenamedFileName1());
			pstmt.setString(9, review.getReviewRenamedFileName2());
			pstmt.setString(10, review.getReviewRenamedFileName3());
			
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			
			close(pstmt);
		}
		
		return result;
	}

	public int selectNextReviewSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNextReviewSeq");
		//selectNextReviewSeq = select seq_review_no.currval from dual
		
		int review_no = 0;
		try {
			//1. PreparedStatement객체 생성
			//2. 미완성 쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			//3. 실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			if(rset.next())
				review_no = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 자원반납
			close(rset);
			close(pstmt);
		}
		return review_no;
	}


//	public int insertImgReview(Connection conn, ReviewImg pic) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String sql = prop.getProperty("insertImgReview");
////		String query = prop.getProperty("insertImg");
//		//insertReview = 
//		//insert into review values(seq_review_no.nextval, ?, default, ?, ?, ?, default)
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pic.getReview_no()-1 );
//			pstmt.setString(2, pic.getReview_original_filename1());
//			pstmt.setString(3, pic.getReview_original_filename2());
//			pstmt.setString(4, pic.getReview_original_filename3());
//			pstmt.setString(5, pic.getReview_renamed_filename1());
//			pstmt.setString(6, pic.getReview_renamed_filename1());
//			pstmt.setString(7, pic.getReview_renamed_filename1());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}

	public int selectTotalReview(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int totalContents = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalReview");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalContents = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	
	}

	
	public int deleteReview(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteReview");
		//delete review where review_no= ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	//강유정 코드 
	public List<String> selectStoreHashtag(Connection conn, int boardNo) {
		String sql = prop.getProperty("selectStoreHashtag");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<String> hashtagList= new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				hashtagList.add(rset.getString("hashtag_keyword1"));
				hashtagList.add(rset.getString("hashtag_keyword2"));
				hashtagList.add(rset.getString("hashtag_keyword3"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);			
		}

		return hashtagList;
	}

	public int updateStoreInfo(Connection conn, Store store) {
		String sql = prop.getProperty("updateStoreInfo");
		
		//update store_info set 
		//store_phone = ?, location = ?, 
		//closing_day = ?, open_hours = ?, close_hours = ?, 
		//breaktime_open = ?, breaktime_close = ?,
		//menu = ?, parking = ? where board_no = ?
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store.getStore_phone());
			pstmt.setString(2, store.getLocation());
			
			if(store.getClosing_day().equals("") || store.getClosing_day() == null)
				pstmt.setObject(3, null);
			else 
				pstmt.setString(3, store.getClosing_day());
			
			pstmt.setString(4, store.getOpen_hours());
			pstmt.setString(5, store.getClose_hours());
			
			if(store.getBreaktime_open().equals(""))
				pstmt.setObject(6, null);
			else
				pstmt.setString(6, store.getBreaktime_open());
			
			if(store.getBreaktime_close().equals(""))
				pstmt.setObject(7, null);
			else
				pstmt.setString(7, store.getBreaktime_close());
			
			pstmt.setString(8, store.getMenu());
			pstmt.setString(9, store.getParking());
			pstmt.setInt(10, store.getBoard_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateStoreHashtag(Connection conn, int boardNo, List<String> hashTag) {
		String sql = prop.getProperty("updateStoreHashtag");		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hashTag.get(0));
			pstmt.setString(2, hashTag.get(1));
			pstmt.setString(3, hashTag.get(2));
			pstmt.setInt(4, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

}
