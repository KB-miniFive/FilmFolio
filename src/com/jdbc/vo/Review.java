package com.jdbc.vo;

public class Review {
	private String content;
	private int rate;
	private String userId;
	private String title;
	
	public Review() {}
	public Review(String content, int rate, String userId, String title) {
		super();
		this.content = content;
		this.rate = rate;
		this.userId = userId;
		this.title = title;
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
	
	@Override
	public String toString() {
		return "Review [content=" + content + ", rate=" + rate + ", userId=" + userId + ", title=" + title + "]";
	}
	
	
	

}
