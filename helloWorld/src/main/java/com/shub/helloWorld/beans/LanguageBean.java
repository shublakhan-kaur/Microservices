package com.shub.helloWorld.beans;

public class LanguageBean {

	private int languageId;
	private String languageName;
	private int rating;

	public LanguageBean() {
	}

	public LanguageBean(int languageId, String languageName, int rating) {
		this.languageId = languageId;
		this.languageName = languageName;
		this.rating = rating;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}