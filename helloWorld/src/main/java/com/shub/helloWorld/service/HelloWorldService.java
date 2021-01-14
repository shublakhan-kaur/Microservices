package com.shub.helloWorld.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shub.helloWorld.beans.LanguageBean;

@Service
public class HelloWorldService {

	List<LanguageBean> languageList = Arrays.asList(new LanguageBean(1, "Java", 7), new LanguageBean(2, "Spring", 7),
			new LanguageBean(3, "Hibernate", 8), new LanguageBean(4, "Spring boot", 6),
			new LanguageBean(5, "Microservices", 6));

	public LanguageBean traverseList(int languageId) {
		return languageList.stream().filter(languageBean -> languageBean.getLanguageId() == languageId)
				.findFirst().get();
	}

	
	public List<LanguageBean> getAllListValues()
	{
		return languageList;
	}
}
