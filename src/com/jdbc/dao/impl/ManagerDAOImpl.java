package com.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.dao.ManagerDAO;
import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.GeneralUser;
import com.jdbc.vo.Manager;
import com.jdbc.vo.Movie;
import com.jdbc.vo.User;

import config.ServerInfo;

public class ManagerDAOImpl extends UserDAOImpl implements ManagerDAO {

	public ManagerDAOImpl(String serverIp) throws ClassNotFoundException {
		Class.forName(ServerInfo.DRIVER_NAME);

	}

	private static ManagerDAOImpl mdao = new ManagerDAOImpl();

	private ManagerDAOImpl() {

	}

	public static ManagerDAOImpl getInstance() {
		return mdao;
	}

	/* 공통적인 로직 */
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);

		return conn;
	}

	private void closeAll(Connection conn, PreparedStatement ps) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	private void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(conn, ps);
	}

	/* 비지니스 로직 */
	public boolean idExists(String userId, Connection conn) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT userId from users where userId = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, userId);
		rs = ps.executeQuery();
		return rs.next();

	}

	public boolean movieExists(String title, Connection conn) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT title from movies where title = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, title);
		rs = ps.executeQuery();
		return rs.next();

	}

	public boolean isAdmin(int admin) {
		if (admin == 1)
			return true;
		else
			return false;
	}

	@Override
	public void addMovie(int admin, Movie movie) throws SQLException, DuplicateIDException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = getConnect();
			if (!movieExists(movie.getTitle(), conn) && isAdmin(admin)) {
				String query = "INSERT INTO movies (title, genre, releaseDate, company, totalrate) VALUES(?,?,?,?,?)";
				ps = conn.prepareStatement(query.toString());
				ps.setString(1, movie.getTitle());
				ps.setString(2, movie.getGenre());
				ps.setDate(3, movie.getReleaseDate());
				ps.setString(4, movie.getCompany());
				ps.setInt(5, movie.getTotalRate());

				System.out.println(ps.executeUpdate() + " 개 영화가 등록되었습니다");
			} else {
				throw new DuplicateIDException();
			}
		} finally {
			closeAll(conn, ps);
		}
	}

	@Override
	public void deleteMovie(int admin, Movie movie) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (movieExists(movie.getTitle(), conn) && isAdmin(admin)) {
				String query = "DELETE movies WHERE title=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, movie.getTitle());
				System.out.println(ps.executeUpdate() + " 개 삭제 성공했습니다");
			} else {
				throw new RecordNotFoundException();
			}

		} finally {
			closeAll(conn, ps);
		}

	}

	@Override
	public void updateMovie(int admin, Movie movie) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			String cmovie = movie.getTitle();
			if (movieExists(cmovie, conn) && isAdmin(admin)) {
				String query = "UPDATE movies SET genre=?, releaseDate = ? , company =? , totalrate=? WHERE title=?";
				ps = conn.prepareStatement(query);

				ps.setString(1, movie.getGenre());
				ps.setDate(2, movie.getReleaseDate());
				ps.setString(3, movie.getCompany());
				ps.setInt(4, movie.getTotalRate());
				ps.setString(5, cmovie);
				System.out.println(ps.executeUpdate() + "개 업데이트 성공");
			} else {
				throw new RecordNotFoundException();
			}
		} finally {
			closeAll(conn, ps);
		}

	}

	public ArrayList<User> getAllUsers(int admin) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<>();

		try {
			conn = getConnect();
			String query = "SELECT userId, nickname, admin, dept FROM users";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next() && isAdmin(admin)) {
				String userId = rs.getString("userId");
				String nickname = rs.getString("nickname");
				String dept = rs.getString("dept");
				int isAdminValue = rs.getInt("admin");

				if (isAdmin(isAdminValue)) {
					Manager manager = new Manager();
					manager.setUserId(userId);
					manager.setAdmin(1);
					manager.setDept(dept);
					users.add(manager);
					// System.out.println(manager);
				} else {
					GeneralUser generalUser = new GeneralUser();
					generalUser.setUserId(userId);
					generalUser.setNickname(nickname);
					generalUser.setAdmin(0);
					users.add(generalUser);
					// System.out.println(generalUser);
				}
			}
		} finally {
			closeAll(conn, ps, rs);
		}
		System.out.println(users);
		return users;
	}

}
