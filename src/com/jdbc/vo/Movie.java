package com.jdbc.vo;

import java.sql.Date;

public class Movie {
	private String title;
	private String genre;
	private Date releaseDate;
	private String company;
	private int totalrate;
	private String totalreview;

    public Movie(String title, String totalreview) {
        super();
        this.title = title;
        this.totalreview = totalreview;
    }


    public String getTotalreview() {
        return totalreview;
    }

    public void setTotalreview(String totalreview) {
    this.totalreview = totalreview;
    }

	public Movie() {
	}

	public Movie(String title, String genre, Date releaseDate, String company, int totalrate) {
		super();
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.company = company;
		this.totalrate = totalrate;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String nation) {
		this.company = nation;
	}

	public int getTotalRate() {
		return totalrate;
	}

	public void setTotalRate(int totalrate) {
		this.totalrate = totalrate;
	}

	public static Date convertToDate(String dateString) {

		return Date.valueOf(dateString);

	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", genre=" + genre + ", releaseDate=" + releaseDate + ", company=" + company
				+ ", totalrate=" + totalrate + "]";
	}

}
