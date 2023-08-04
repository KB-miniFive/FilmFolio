package com.jdbc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.Movie;
import com.jdbc.vo.User;

public interface ManagerDAO {
	void addMovie(int admin, Movie movie) throws SQLException, DuplicateIDException;

	void deleteMovie(int admin, Movie movie) throws SQLException, RecordNotFoundException;

	void updateMovie(int admin, Movie movie) throws SQLException, RecordNotFoundException;

	ArrayList<User> getAllUsers(int admin) throws SQLException;
}
