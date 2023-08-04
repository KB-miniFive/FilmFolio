package com.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.jdbc.dao.UserDAO;
import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.GeneralUser;
import com.jdbc.vo.Manager;
import com.jdbc.vo.User;

import config.ServerInfo;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User user) throws SQLException, DuplicateIDException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (!isExist(user.getUserId(), conn)) {
				if (user.getAdmin() == 1) {
					Manager ma = Manager.class.cast(user);
					String query = "INSERT INTO users(userid, admin, dept) VALUES( ?, 1, ?)";
					ps = conn.prepareStatement(query);
					ps.setString(1, ma.getUserId());
					ps.setString(2, ma.getDept());
					ps.executeUpdate();
					System.out.println(ma.getUserId() + "님이 추가되었습니다.");
				} else {
					GeneralUser gu = GeneralUser.class.cast(user);
					String query = "INSERT INTO users(userid, admin, nickname) VALUES( ?, 0, ?)";
					ps = conn.prepareStatement(query);
					ps.setString(1, gu.getUserId());
					ps.setString(2, gu.getNickname());
					ps.executeUpdate();
					System.out.println(gu.getUserId() + "님이 추가되었습니다.");
				}
			} else {
				throw new DuplicateIDException("이미 존재하는 회원입니다");
			}
		} finally {
			closeAll(ps, conn);
		}

	}

	@Override
	public void deleteUser(String userId) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnect();
			if (isExist(userId, conn)) {
				String query = "DELETE users WHERE userid=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, userId);
				ps.executeUpdate();
				System.out.println(userId + "가 삭제되었습니다");
			} else {
				throw new RecordNotFoundException("삭제할 대상의 고객이 없습니다 ");
			}
		} finally {
			closeAll(ps, conn);
		}
	}
	/*
	 * @Override public void updateUser(User user) throws SQLException,
	 * RecordNotFoundException { Connection conn = null; PreparedStatement ps =
	 * null; try { conn = getConnect(); if(isExist(user.getUserId(), conn)) {
	 * if(user.getAdmin() == 1) { Manager ma = Manager.class.cast(user); String
	 * query =
	 * "UPDATE users SET admin = 1, nickname = null, dept = ? WHERE userid = ?"; ps=
	 * conn.prepareStatement(query); ps.setString(1, ma.getDept()); ps.setString(2,
	 * ma.getUserId()); ps.executeUpdate(); System.out.println(ma.getUserId()
	 * +"님의 정보가 수정되었습니다"); }else { GeneralUser gu = GeneralUser.class.cast(user);
	 * String query =
	 * "UPDATE users SET admin = 0, nickname = ?, dept = null WHERE userid = ?"; ps=
	 * conn.prepareStatement(query); ps.setString(1, gu.getNickname());
	 * ps.setString(2, gu.getUserId()); ps.executeUpdate();
	 * System.out.println(gu.getUserId() +"님의 정보가 수정되었습니다"); } } else throw new
	 * RecordNotFoundException("해당 고객이 존재하지 않습니다."); }finally { closeAll(ps, conn);
	 * } }
	 */

	@Override
	public void updateUser(User user) throws SQLException, RecordNotFoundException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = getConnect();
			if (isExist(user.getUserId(), conn)) {
				String query = "UPDATE users SET nickname = ? , dept=? WHERE userId=?";
				ps = conn.prepareStatement(query);
				if (user instanceof Manager) {
					ps.setNull(1, Types.VARCHAR);
					ps.setString(2, ((Manager) user).getDept());
				} else {
					ps.setString(1, ((GeneralUser) user).getNickname());
					ps.setNull(2, Types.VARCHAR);
				}

				ps.setString(3, user.getUserId());
				System.out.println(ps.executeUpdate() + "명 업데이트 성공");

			} else {
				throw new RecordNotFoundException();
			}
		} finally {
			closeAll(ps, conn);
		}

	}

////////////////////     공통적인 로직      //////////////////////////////////
	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}

	@Override
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if (ps != null)
			ps.close();
		if (conn != null)
			conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(ps, conn);
	}

	private boolean isExist(String userId, Connection conn) throws SQLException {
		String query = "SELECT userId FROM users WHERE userId=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
}