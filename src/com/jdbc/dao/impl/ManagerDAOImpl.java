package com.jdbc.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.dao.ManagerDAO;
import com.jdbc.vo.Movie;
import com.jdbc.vo.User;

public class ManagerDAOImpl extends UserDAOImpl implements ManagerDAO{


	@Override
	public void addMovie(int admin, Movie movie) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMovie(int admin, Movie movie) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMovie(int admin, Movie movie) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<User> getAllUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
