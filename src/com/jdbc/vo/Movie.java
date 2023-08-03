package com.jdbc.vo;

import java.sql.Date;

public class Movie {
	private String title;
	private String genre;
	private Date releaseDate;
	private String nation;
	private int rate;
	
	public Movie() {}
	public Movie(String title, String genre, Date releaseDate, String nation, int rate) {
		super();
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.nation = nation;
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "Movie [title=" + title + ", genre=" + genre + ", releaseDate=" + releaseDate + ", nation=" + nation
				+ ", rate=" + rate + "]";
	}
	
	
	
}
