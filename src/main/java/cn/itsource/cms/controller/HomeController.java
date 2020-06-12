package cn.itsource.cms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.domain.Slide;
import cn.itsource.cms.service.IArticleService;
import cn.itsource.cms.service.ISlideService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private IArticleService service;
	
	@Autowired
	private ISlideService slideService;
	
	/**
	 * @Description:(根据文章类型分类查询，返回给前端页面)
	 * @param:@return   
	 * @return:Map<String,Object>  
	 * @author:Joi
	 * @date:2020年6月9日
	 * @version:V1.0
	 */
	@RequestMapping("/articles")
	@ResponseBody
	public Map<String, Object> List() {
		return service.articles();
	}
	
	/**
	 * @Description:(更新静态页面的点击次数)
	 * @param:@return   
	 * @return:Map<String,Object>  
	 * @author:Joi
	 * @date:2020年6月9日
	 * @version:V1.0
	 */
	@RequestMapping("/updateArticleClickCount")
	@ResponseBody
	public Article updateArticleClickCount(String url) {
		return service.updateArticleClickCount(url);
	}
	
	/**
	 * @Description:(首页轮播图显示)
	 * @param:@return   
	 * @return:List<Slide>  
	 * @author:Joi
	 * @date:2020年6月11日
	 * @version:V1.0
	 */
	@RequestMapping("/slides")
	@ResponseBody
	public List<Slide> showSlides() {
		return slideService.findSlides();
	}
}
