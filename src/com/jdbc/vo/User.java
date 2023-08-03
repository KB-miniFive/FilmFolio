package com.jdbc.vo;

public class User {
	private String userId;
	private int admin;
	
	public User() {}

	public User(String userId, int admin) {
		super();
		this.userId = userId;
		this.admin = admin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", admin=" + admin + "]";
	}
	
}
