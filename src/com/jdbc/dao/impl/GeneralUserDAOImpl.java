package com.jdbc.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.dao.GeneralUserDAO;
import com.jdbc.vo.Movie;
import com.jdbc.vo.Review;
import com.jdbc.vo.User;

public class GeneralUserDAOImpl extends UserDAOImpl implements GeneralUserDAO {


	@Override
	public void addReview(String userId, Review review) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReview(String userId, Review review) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReview(String userId, Review review) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Review> getAllReviews() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Review> getAllReviewsByRate(int rate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie getMovie(Movie movie) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movie> getAllMovies() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movie> getAllMoviesByGenre(String genre) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Review> getPortfolio(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getAllUsersSortByReviewCount() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movie> getAllMoviesSortByReleasedDate() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Movie> getAllMoviesSortByRate() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
