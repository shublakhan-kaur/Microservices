package com.shub.callingService.beans;

import java.util.List;

public class CallingBean {

	private LanguageBean languageBean;
	private RatingDescriptionBean ratingBean;
	private List<LanguageBean> languageList;

	public CallingBean() {
	}

	public LanguageBean getLanguageBean() {
		return languageBean;
	}

	public void setLanguageBean(LanguageBean languageBean) {
		this.languageBean = languageBean;
	}

	public RatingDescriptionBean getRatingBean() {
		return ratingBean;
	}

	public void setRatingBean(RatingDescriptionBean ratingBean) {
		this.ratingBean = ratingBean;
	}

	public List<LanguageBean> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<LanguageBean> languageList) {
		this.languageList = languageList;
	}

}