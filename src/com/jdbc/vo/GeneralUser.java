package com.jdbc.vo;

public class GeneralUser extends User{
	private String nickname;
	
	public GeneralUser() {}

	public GeneralUser(String userId, int admin, String nickname) {
		super(userId, admin);
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "GeneralUser [nickname=" + nickname + "]"+super.toString();
	}
	

}
