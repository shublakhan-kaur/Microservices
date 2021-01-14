package com.shub.ratingdesc.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shub.ratingdesc.bean.RatingDescriptionBean;

@Service
public class RatingDescriptionService {

	private List<RatingDescriptionBean> ratingDescBean = Arrays.asList(new RatingDescriptionBean(10, "Outstanding"),
			new RatingDescriptionBean(9, "Excellent"), new RatingDescriptionBean(8, "Very Good"),
			new RatingDescriptionBean(7, "Good"), new RatingDescriptionBean(6, "Above Average"),
			new RatingDescriptionBean(5, "Average"), new RatingDescriptionBean(4, "Below Average"),
			new RatingDescriptionBean(3, "Weak"), new RatingDescriptionBean(2, "Very Weak"),
			new RatingDescriptionBean(1, "Poor"));

	public RatingDescriptionBean getRatingDescription(int ratingId) {
		return ratingDescBean.stream().filter(ratingBean -> ratingBean.getRatingLevel() == ratingId).findFirst().get();
	}

}
