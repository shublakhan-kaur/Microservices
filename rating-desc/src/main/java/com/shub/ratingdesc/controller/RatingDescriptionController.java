package com.shub.ratingdesc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shub.ratingdesc.bean.RatingDescriptionBean;
import com.shub.ratingdesc.service.RatingDescriptionService;

@RestController
@RequestMapping(path = "/ratingDesc")
public class RatingDescriptionController {

	@Autowired
	private RatingDescriptionService ratingDescService;

	@RequestMapping( path = "/{ratingId}")
	public RatingDescriptionBean ratingDescription(@PathVariable(name = "ratingId") int ratingId) {
		return ratingDescService.getRatingDescription(ratingId);
	}
}