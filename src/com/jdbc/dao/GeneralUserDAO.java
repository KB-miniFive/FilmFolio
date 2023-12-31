package com.jdbc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jdbc.exception.DuplicateIDException;
import com.jdbc.exception.RecordNotFoundException;
import com.jdbc.vo.Movie;
import com.jdbc.vo.Review;
import com.jdbc.vo.User;

public interface GeneralUserDAO {

	void addReview(Review review) throws SQLException, DuplicateIDException;

	void deleteReview(Review review) throws SQLException, RecordNotFoundException;

	void updateReview(Review review) throws SQLException;

	ArrayList<Review> getAllReviews() throws SQLException;

	ArrayList<Review> getAllReviewsByRate(int rate) throws SQLException;
	// 삭제
//	    Movie getMovie(Movie movie) throws SQLException; 

	ArrayList<Movie> getAllMovies() throws SQLException;

	ArrayList<Movie> getAllMoviesByGenre(String genre) throws SQLException;

	// 추가
	ArrayList<Review> getAllReviewsByMovie(String title) throws SQLException;

	ArrayList<Review> getPortfolio(String userId) throws SQLException; // 내 리뷰보기

	ArrayList<User> getAllUsersSortByReviewCount() throws SQLException; // 리뷰를 많이 작성한 사용자 내림차순 정렬

	ArrayList<Movie> getAllMoviesSortByReleasedDate() throws SQLException; // 영화 개봉일 순서로 정렬

	ArrayList<Movie> getAllMoviesSortByRate() throws SQLException;

}
