package com.shub.helloWorld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shub.helloWorld.beans.LanguageBean;
import com.shub.helloWorld.service.HelloWorldService;

@RestController
@RequestMapping(path = "/displayList")
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	/*
	 * @RequestMapping(path = "/helloWorld") public String getMessage() { return
	 * "This is my first Spring boot project"; }
	 */
	@GetMapping(path = "/allListValues")
	public List<LanguageBean> getAllListValue() {
		return helloWorldService.getAllListValues();
	}

	@RequestMapping(path = "/{languageId}", method = RequestMethod.GET)
	public LanguageBean getListValues(@PathVariable("languageId") int languageId) {
		return helloWorldService.traverseList(languageId);
	}
}