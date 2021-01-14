package com.shub.callingService.beans;

public class RatingDescriptionBean {

	int ratingLevel;
	String ratingGrading;

	public RatingDescriptionBean() {

	}

	public RatingDescriptionBean(int ratingLevel, String ratingGrading) {
		super();
		this.ratingLevel = ratingLevel;
		this.ratingGrading = ratingGrading;
	}

	public int getRatingLevel() {
		return ratingLevel;
	}

	public void setRatingLevel(int ratingLevel) {
		this.ratingLevel = ratingLevel;
	}

	public String getRatingGrading() {
		return ratingGrading;
	}

	public void setRatingGrading(String ratingGrading) {
		this.ratingGrading = ratingGrading;
	}

}