package com.ssafy.journey.model;

public class ReviewDto {
	private int id;
	private int journeyId;
	private String userId;
	private int ratings;
	private String comment;
	private String registerTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "ReviewDto [id=" + id + ", journeyId=" + journeyId + ", userId=" + userId + ", ratings=" + ratings
				+ ", comment=" + comment + ", registerTime=" + registerTime + "]";
	}

}
