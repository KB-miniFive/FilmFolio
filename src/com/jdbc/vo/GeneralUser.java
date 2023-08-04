package com.jdbc.vo;

import java.util.ArrayList;

public class GeneralUser extends User {
	private String nickname;
	private int numOfReviews;
	private ArrayList<Review> portfolio;

	public GeneralUser() {
	}

	public GeneralUser(String userId, String nickname) {
		super(userId, 0);
		this.nickname = nickname;
	}

	public GeneralUser(String userId, String nickname, int numOfReviews) {
		super(userId, 0);
		this.nickname = nickname;
		this.numOfReviews = numOfReviews;
	}

	public GeneralUser(String userId, int admin, String nickname, ArrayList<Review> portfolio) {
		super(userId, admin);
		this.nickname = nickname;
		this.portfolio = portfolio;
	}

	public GeneralUser(String nickname, int numOfReviews) {
		this.nickname = nickname;
		this.numOfReviews = numOfReviews;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ArrayList<Review> getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(ArrayList<Review> portfolio) {
		this.portfolio = portfolio;
	}

	public int getNumOfReviews() {
		return numOfReviews;
	}

	public void setNumOfReviews(int numOfReviews) {
		this.numOfReviews = numOfReviews;
	}

	@Override
	public String toString() {
		return "GeneralUser [nickname=" + nickname + "]" + super.toString();
	}

}
