package com.shub.callingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shub.callingService.beans.LanguageBean;
import com.shub.callingService.beans.RatingDescriptionBean;

@RestController
@RequestMapping(path = "/display")
public class CallingServiceController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path = "{languageId}")
	public RatingDescriptionBean displayList(@PathVariable("languageId") String languageId) {
		LanguageBean languageBean = restTemplate.getForObject("http://hello-world-service/displayList/" + languageId,
				LanguageBean.class);

		RatingDescriptionBean ratingBean = restTemplate.getForObject(
				"http://rating-info-service/ratingDesc/" + languageBean.getRating(), RatingDescriptionBean.class);
		return ratingBean;
	}
}
