package com.jdbc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.vo.Movie;
import com.jdbc.vo.User;

public interface ManagerDAO extends UserDAO{
	void addMovie(int admin, Movie movie)throws SQLException;
	
	void deleteMovie(int admin, Movie movie)throws SQLException; 
	
	void updateMovie(int admin, Movie movie)throws SQLException;
	
	ArrayList<User> getAllUsers() throws SQLException; 

}
