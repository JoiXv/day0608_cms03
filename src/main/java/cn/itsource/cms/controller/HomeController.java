package cn.itsource.cms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itsource.cms.service.IArticleService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private IArticleService service;
	
	@RequestMapping("/articles")
	@ResponseBody
	public Map<String, Object> List() {
		return service.articles();
	}
	
}
