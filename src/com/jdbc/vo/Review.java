package com.jdbc.vo;

public class Review {
	private String content;
	private int rate;
	private String userId;
	private String title;
	private String nickname;

	public Review() {
	}

	public Review(String userId, String title, String content, int rate) {
		super();
		this.content = content;
		this.rate = rate;
		this.userId = userId;
		this.title = title;
	}

	// 리뷰 출력용 생성자
	public Review(String content, int rate, String userId, String title, String nickname) {
		super();
		this.content = content;
		this.rate = rate;
		this.userId = userId;
		this.title = title;
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return title;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "영화 제목 : " + title + " / 작성자아이디 : " + userId + " / 후기 : " + content + " / 평점 : " + rate + " / 작성자 : "
				+ nickname;
	}
}
