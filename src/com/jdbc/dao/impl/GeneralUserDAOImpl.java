package com.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.dao.GeneralUserDAO;
import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.GeneralUser;
import com.jdbc.vo.Movie;
import com.jdbc.vo.Review;
import com.jdbc.vo.User;

import config.ServerInfo;

public class GeneralUserDAOImpl extends UserDAOImpl implements GeneralUserDAO {
	public GeneralUserDAOImpl(String serverIp) throws ClassNotFoundException {
		Class.forName(ServerInfo.DRIVER_NAME);
		
	}

	private static GeneralUserDAOImpl gdao = new GeneralUserDAOImpl();

	private GeneralUserDAOImpl() {
		
	}

	public static GeneralUserDAOImpl getInstance() {
		return gdao;
	}

	//////////////////// 공통적인 로직 //////////////////////////////////

	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		
		return conn;
	}

	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}

//리뷰 여부 확인
	private boolean isExist(String userid, String title, Connection conn) throws SQLException {
		String query = "SELECT userid FROM reviews WHERE userid=? and title=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, userid);
		ps.setString(2, title);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	@Override
	public void addReview(Review review) throws SQLException, DuplicateIDException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (!isExist(review.getUserId(), review.getTitle(), conn)) {// 추가하E려는 리뷰가 없다면
				calculateRate1(review);
				String query = "INSERT INTO reviews(userid, title, content, rate) VALUES(?,?,?,?)";
				ps = conn.prepareStatement(query);
				ps.setString(1, review.getUserId());
				ps.setString(2, review.getTitle());
				ps.setString(3, review.getContent());
				ps.setInt(4, review.getRate());

				System.out.println(ps.executeUpdate() + " 명 INSERT 성공...addReview()..");
				

			} else {
				throw new DuplicateIDException("이미 작성한 영화리뷰입니다");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	// 평점 + 계산
	public void calculateRate1(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			conn = getConnect();

			String query1 = "SELECT totalrate FROM movies WHERE title=?";
			String query2 = "SELECT NVL(COUNT(*),0) FROM reviews WHERE title=?";
			ps1 = conn.prepareStatement(query1);
			ps2 = conn.prepareStatement(query2);
			ps1.setString(1, review.getTitle());
			ps2.setString(1, review.getTitle());
			rs1 = ps1.executeQuery();
			rs2 = ps2.executeQuery();
			if (rs1.next() & rs2.next()) {
				int q = rs1.getInt(1); // q는 현재 평점
				int cnt = rs2.getInt(1); // cnt는 현재 리뷰 개수
				int newRate = Math.round((q * cnt + review.getRate()) / (cnt + 1)); // 평점계산
				System.out.println(cnt);
				System.out.println(q);
				System.out.println(newRate);
				// UPDATE
				String query3 = "UPDATE movies SET totalrate=? WHERE title=?";
				ps = conn.prepareStatement(query3);
				ps.setInt(1, newRate);
				ps.setString(2, review.getTitle());

				System.out.println(ps.executeUpdate() + " row calculateRate()....INSERT OK");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
	}

	// 평점 - 계산
	public void calculateRate2(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			conn = getConnect();

			String query1 = "SELECT totalrate FROM movies WHERE title=?";
			String query2 = "SELECT NVL(COUNT(*),0) FROM reviews WHERE title=?";
			ps1 = conn.prepareStatement(query1);
			ps2 = conn.prepareStatement(query2);
			ps1.setString(1, review.getTitle());
			ps2.setString(1, review.getTitle());
			rs1 = ps1.executeQuery();
			rs2 = ps2.executeQuery();
			if (rs1.next() & rs2.next()) {
				int q = rs1.getInt(1); // q는 현재 평점
				int cnt = rs2.getInt(1); // cnt는 현재 리뷰 개수
				int newRate = Math.round((q * cnt - review.getRate()) / (cnt - 1)); // 평점계산

				// UPDATE
				String query3 = "UPDATE movies SET totalrate=? WHERE title=?";
				ps = conn.prepareStatement(query3);
				ps.setInt(1, newRate);
				ps.setString(2, review.getTitle());

				System.out.println(ps.executeUpdate() + " row calculateRate2()....DELETE OK");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
	}

	@Override
	public void deleteReview(Review review) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (isExist(review.getUserId(), review.getTitle(), conn)) { // 삭제할 리뷰가 있다면
				calculateRate2(review);

				String query = "DELETE reviews WHERE userid=? and title=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, review.getUserId());
				ps.setString(2, review.getTitle());

				System.out.println(ps.executeUpdate() + "개 DELETE OK...deleteReview()..");
			} else {
				throw new RecordNotFoundException("삭제할 리뷰가 없습니다 ");
			}
		} finally {
			closeAll(ps, conn);
		}
	}

	@Override
	public void updateReview(Review review) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			String query = "UPDATE reviews SET userid=?, title=?, content=?, rate=? WHERE userid=? AND title=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, review.getUserId());
			ps.setString(2, review.getTitle());
			ps.setString(3, review.getContent());
			ps.setInt(4, review.getRate());
			ps.setString(5, review.getUserId());
			ps.setString(6, review.getTitle());

			System.out.println(ps.executeUpdate() + " 명 UPDATE 성공...updateReview()..");

		} finally {
			closeAll(ps, conn);
		}

	}

	// 모든 영화 리뷰 출력
	@Override
	public ArrayList<Review> getAllReviews() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = getConnect();
			String query = "SELECT r.content, r.rate, r.userId, r.title, u.nickname FROM reviews r , users u WHERE r.userId = u.userId";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Review(rs.getString("content"), rs.getInt("rate"), rs.getString("userId"),
						rs.getString("title"), rs.getString("nickname")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	// 특정 평점 리뷰보기
	@Override
	public ArrayList<Review> getAllReviewsByRate(int rate) throws SQLException {

		System.out.println(rate + "점을 남긴 리뷰\n");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = getConnect();
			String query = "SELECT r.content, r.rate, r.userId, r.title, m.nickname FROM reviews r, users m WHERE r.userId = m.userId AND r.rate = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, rate);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Review(rs.getString("content"), rs.getInt("rate"), rs.getString("userId"),
						rs.getString("title"), rs.getString("nickname")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	// 영화별 리뷰보기
	@Override
	public ArrayList<Review> getAllReviewsByMovie(String title) throws SQLException {

		System.out.println(title + " 영화에 대한 리뷰\n");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		try {
			conn = getConnect();
			String query = "SELECT r.content, r.rate, r.userId, r.title, m.nickname FROM reviews r, users m WHERE r.userId = m.userId AND r.title = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Review(rs.getString("content"), rs.getInt("rate"), rs.getString("userId"),
						rs.getString("title"), rs.getString("nickname")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	// 영화목록 전체 뿌려주기
	// rate 어떻게 가져오는지?
	@Override
	public ArrayList<Movie> getAllMovies() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Movie> list = new ArrayList<Movie>();
		try {
			conn = getConnect();
			String query = "SELECT * FROM movies";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Movie(rs.getString("title"), rs.getString("genre"), rs.getDate("releaseDate"),
						rs.getString("company"), rs.getInt("totalrate")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	// 장르별 영화 뿌려주기
	@Override
	public ArrayList<Movie> getAllMoviesByGenre(String genre) throws SQLException {
		System.out.println(genre + " 장르 영화\n");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Movie> list = new ArrayList<Movie>();
		try {
			conn = getConnect();
			String query = "SELECT * FROM movies WHERE genre = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, genre);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Movie(rs.getString("title"), rs.getString("genre"), rs.getDate("releaseDate"),
						rs.getString("company"), rs.getInt("totalrate")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}
	// 평점별로 영화 추천/비추천 
	public ArrayList<Movie> getReviews() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

    ArrayList<Movie> movieList = new ArrayList<>();
    try {
        conn = getConnect();
        String query = "SELECT title, totalrate, DECODE(totalrate, 1,'완전비추',2,'완전비추',3,'비추',7,'추천',8,'추천',9,'완전추천',10,'인생영화', '보통') totalreview FROM movies";    
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            movieList.add(new Movie(rs.getString("title"), rs.getString("totalreview")));
        }
    } finally {
        closeAll(rs, ps, conn);
    }
    return movieList;
    
}

	@Override
	public ArrayList<Review> getPortfolio(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Review> reviewList = new ArrayList<Review>();
		try {
			conn = getConnect();
			String query = "SELECT r.userid, title,r.content,rate  FROM reviews r LEFT JOIN  users u ON r.userid = u.userid WHERE r.userid= ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				reviewList.add(new Review(rs.getString("userId"), rs.getString("title"), rs.getString("content"),
						rs.getInt("rate")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return reviewList;
	}

	@Override
	public ArrayList<User> getAllUsersSortByReviewCount() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<User> userList = new ArrayList<User>();
		try {
			conn = getConnect();
			String query = "SELECT u.nickname, COUNT(content) AS \"작성한 리뷰 수\" FROM reviews r LEFT JOIN  users u ON r.userid = u.userid GROUP BY nickname ORDER BY 2 DESC";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				userList.add(new GeneralUser(rs.getString("nickname"), rs.getInt("작성한 리뷰 수")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return userList;
	}

	@Override
	public ArrayList<Movie> getAllMoviesSortByReleasedDate() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			conn = getConnect();
			String query = "SELECT * FROM movies ORDER BY releasedate DESC";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				movieList.add(new Movie(rs.getString("title"), rs.getString("genre"), rs.getDate("releaseDate"),
						rs.getString("company"), rs.getInt("totalrate")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return movieList;
	}

	@Override
	public ArrayList<Movie> getAllMoviesSortByRate() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			conn = getConnect();
			//SELECT DISTINCT m.title, m.genre, m.releasedate, m.company, m.totalrate, 
			//DENSE_RANK() over (ORDER BY totalrate desc,(SELECT COUNT(*) FROM reviews r WHERE r.title = m.title))  
			//FROM  movies m;
			String query = "SELECT * FROM movies ORDER BY totalrate DESC, "
					+ "(SELECT COUNT(*) FROM reviews WHERE reviews.title = movies.title) DESC";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				movieList.add(new Movie(rs.getString("title"), rs.getString("genre"), rs.getDate("releaseDate"),
						rs.getString("company"), rs.getInt("totalrate")));
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return movieList;
	}

}