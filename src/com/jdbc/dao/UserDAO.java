package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.User;

public interface UserDAO {
	void addUser(User user) throws SQLException, DuplicateIDException;

	void deleteUser(String userId) throws SQLException, RecordNotFoundException;

	void updateUser(User user) throws SQLException, RecordNotFoundException;

	Connection getConnect() throws SQLException;

	void closeAll(PreparedStatement ps, Connection conn) throws SQLException;

	void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException;
}