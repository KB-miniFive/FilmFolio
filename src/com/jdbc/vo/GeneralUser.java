package com.jdbc.vo;

import java.util.ArrayList;

public class GeneralUser extends User{
	private String nickname;
	private ArrayList<Review> portfolio;
	
	public GeneralUser() {}

	public GeneralUser(String userId, int admin, String nickname) {
		super(userId, admin);
		this.nickname = nickname;
	}
	

	public GeneralUser(String userId, int admin, String nickname, ArrayList<Review> portfolio) {
		super(userId, admin);
		this.nickname = nickname;
		this.portfolio = portfolio;
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

	@Override
	public String toString() {
		return "GeneralUser [nickname=" + nickname + ", portfolio=" + portfolio + "]"+super.toString();
	}

	
	

}
