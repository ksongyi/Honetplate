package search.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import search.model.vo.RatingList;
import search.model.vo.HashtagRankList;
import search.model.vo.NewList;
import search.model.vo.ReservationRankList;
import search.model.vo.ReviewList;
import search.model.vo.SearchTable;
import search.model.vo.ViewList;

public class SearchDao {

	private Properties prop = new Properties();
	
	public SearchDao() {
		String fileName = "/sql/search/search-query.properties";
		String path = SearchDao.class.getResource(fileName).getPath();
		

		try {
			prop.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//조회결과 한페이지에 9개씩 나오게 
	public List<SearchTable> searchAll(Connection conn , String searchWord1, String searchWord2, String searchWord3, String searchWord4, String searchWord5, String searchWord6, int cpage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchPagedList");
		//searchPagedList = select *  from( select  view_num, location, s.store_name, menu, hashtag_keyword1, hashtag_keyword2, hashtag_keyword3, m.revieworiginalfilename1, s.board_no,  row_number() over(order by view_num desc) rnum  from (store_info S left join (select board_no, store_name, reservation_num, b.revieworiginalfilename1 from (select * from  (select board_no, MAX(review_no) review from  review group by board_no) n left join store_info r on n.board_no = r.board_no) a left join review b on a.review = b.review_no) M on s.board_no = m.board_no) left join hashtag_board h on s.board_no = h.board_no   where location like ?  or s.store_name like ?  or menu like ?  or hashtag_keyword1 like ? or hashtag_keyword2 like ?  or hashtag_keyword3 like ?) K  where rnum between ? and ?

		
		List<SearchTable> searchTableList =  new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord1+"%");
	        pstmt.setString(2, "%"+searchWord2+"%");
	        pstmt.setString(3, "%"+searchWord3+"%");
	        pstmt.setString(4, "%"+searchWord4+"%");
	        pstmt.setString(5, "%"+searchWord5+"%");
	        pstmt.setString(6, "%"+searchWord6+"%");
	        pstmt.setInt(7, (cpage - 1) * numPerPage + 1 );
	        pstmt.setInt(8, cpage * numPerPage);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				SearchTable st = new SearchTable();
				st.setViewNum(rset.getInt("view_num"));
				st.setLocation(rset.getString("location"));
				st.setStoreName(rset.getString("store_name"));
				st.setMenu(rset.getString("menu"));
				st.setHashtagKeyword1(rset.getString("hashtag_keyword1"));
				st.setHashtagKeyword2(rset.getString("hashtag_keyword2"));
				st.setHashtagKeyword3(rset.getString("hashtag_keyword3"));
				st.setReviewOriginalFileName1(rset.getString("reviewrenamedfilename1"));
				st.setBoardNo(rset.getInt("board_no"));
				searchTableList.add(st);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}	
		return searchTableList;
	}

	
	//총 페이지 구하기	
	public int selectTotalResult(Connection conn, String searchWord1, String searchWord2, String searchWord3, String searchWord4, String searchWord5, String searchWord6) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("searchtotalList");
		//searchtotalList = select count(*) from( select  view_num, location, store_name, menu, hashtag_keyword1, hashtag_keyword2, hashtag_keyword3, m.menu_original_filename1, s.board_no from (store_info S left join menu_pic M on s.board_no = m.board_no) left join hashtag_board h  on s.board_no = h.board_no  where location like ? or store_name like ? or menu like ? or hashtag_keyword1 like ?   or hashtag_keyword2 like ? or hashtag_keyword3 like ?) K 

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord1+"%");
	        pstmt.setString(2, "%"+searchWord2+"%");
	        pstmt.setString(3, "%"+searchWord3+"%");
	        pstmt.setString(4, "%"+searchWord4+"%");
	        pstmt.setString(5, "%"+searchWord5+"%");
	        pstmt.setString(6, "%"+searchWord6+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);//컬럼순서로 가져옴.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	
	
	//모두 조회 예약횟수별
	public List<ReservationRankList> searchAll(Connection conn){
		List<ReservationRankList> reservationRankList =  new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("reservationRank");
//		reservationRank = select e.board_no,  e.store_name,  e.reservation_num, m.menu_original_filename1 from ( select * from store_info)e left join (select board_no,  menu_original_filename1 from menu_pic) M on e.board_no = m.board_no order by reservation_num desc 

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				ReservationRankList rrl = new ReservationRankList();
				rrl.setBoardNo(rset.getInt("board_no"));
				rrl.setStoreName(rset.getString("store_name"));
				rrl.setReservationNum(rset.getInt("reservation_num"));
				rrl.setReviewOriginalFileName1(rset.getString("reviewrenamedfilename1"));
				reservationRankList.add(rrl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return reservationRankList;
	}
	
	
	
	
	//해시태그별 조회수 1위
	public List<HashtagRankList> hashtagAll(Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("hashtagRank");
		List<HashtagRankList> hashtagRankList =  new ArrayList<>();
//		hashtagRank = select * from (select s.board_no, store_name, view_num, hashtag_keyword1, hashtag_keyword2, hashtag_keyword3,m.menu_original_filename1, rank() over(order by view_num desc)rank from (store_info s left join hashtag_board h on s.board_no = h.board_no) left join menu_pic M on s.board_no = m.board_no where hashtag_keyword1 like '\uACE0\uAE30\uB9DB\uC9D1' or hashtag_keyword2 like '\uACE0\uAE30\uB9DB\uC9D1' or hashtag_keyword3 like '\uACE0\uAE30\uB9DB\uC9D1') e where rank = 1

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				HashtagRankList  hrl= new HashtagRankList();
				hrl.setBoardNo(rset.getInt("board_no"));
				hrl.setStoreName(rset.getString("store_name"));
				hrl.setHashtagKeyword(rset.getString("hashtag_keyword"));
				hrl.setTotal(rset.getInt("total"));
				hrl.setReviewOriginalFileName1(rset.getString("reviewrenamedfilename1"));	
				hashtagRankList.add(hrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}		
		return hashtagRankList;
	}

	//조회수순 다 조회
	public List<ViewList> viewAll(Connection conn) {
		List<ViewList> viewList =  new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("viewList");
//		viewList = select board_no, store_name from store_info order by view_num desc
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				ViewList vl = new ViewList();
				vl.setBoardNo(rset.getInt("board_no"));
				vl.setStoreName(rset.getString("store_name"));
				viewList.add(vl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}		
		return viewList;
	}

	//나중에 등록한 가게순 전부 
	public List<NewList> newAll(Connection conn) {
		List<NewList> newList =  new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("newList");
//		viewList = select board_no, store_name from store_info order by view_num desc
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				NewList nl = new NewList();
				nl.setBoardNo(rset.getInt("board_no"));
				nl.setStoreName(rset.getString("store_name"));
				newList.add(nl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}		
		return newList;
	}

	//리뷰 개수순
	public List<ReviewList> reviewAll(Connection conn) {
		List<ReviewList> reviewList =  new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("reviewList");
//		reviewList = select * from  (select  count(board_no) reviewcount, board_no from  review group by board_no order by count(board_no) desc)e join (select board_no, store_name from store_info) s  on s.board_no = e.board_no order by 1 desc
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while(rset.next()) {
				ReviewList rl = new ReviewList();
				rl.setReviewCount(rset.getInt("reviewcount"));
				rl.setBoardNo(rset.getInt("board_no"));
				rl.setStoreName(rset.getString("store_name"));
				reviewList.add(rl);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}		
		return reviewList;
	}
	
	
	//별점높은순
		public List<RatingList> ratingAll(Connection conn) {
			List<RatingList> ratingList =  new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("ratingList");
//			ratingList = select e.avgrating, e.board_no, s.store_name from  (select avg(rating) avgrating, board_no from  review group by board_no)e join (select board_no, store_name from store_info) s  on e.board_no = s.board_no order by 1 desc
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();

				while(rset.next()) {
					RatingList rl = new RatingList();
					rl.setAvgRating(rset.getInt("avgrating"));
					rl.setBoardNo(rset.getInt("board_no"));
					rl.setStoreName(rset.getString("store_name"));
					ratingList.add(rl);	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(rset);
				close(pstmt);
			}		
			return ratingList;
		}

	
	
}
