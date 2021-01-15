package com.shub.callingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shub.callingService.beans.CallingBean;
import com.shub.callingService.beans.LanguageBean;
import com.shub.callingService.beans.RatingDescriptionBean;

@RestController
@RequestMapping(path = "/display")
public class CallingServiceController {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * public CallingBean getAllList() { CallingBean callingBean = new
	 * CallingBean(); List<LanguageBean> listBean = restTemplate.getForObject(
	 * "http://hello-world-service/displayList/allListValues", List.class);
	 * callingBean.setLanguageList(listBean); return callingBean; }
	 */

	@RequestMapping(path = "{languageId}")
	public CallingBean displayList(@PathVariable("languageId") String languageId) {
		CallingBean callingBean = new CallingBean();
		LanguageBean languageBean = restTemplate.getForObject("http://hello-world-service/displayList/" + languageId,
				LanguageBean.class);

		RatingDescriptionBean ratingBean = restTemplate.getForObject(
				"http://rating-info-service/ratingDesc/" + languageBean.getRating(), RatingDescriptionBean.class);

		callingBean.setLanguageBean(languageBean);
		callingBean.setRatingBean(ratingBean);
		return callingBean;
	}
}
