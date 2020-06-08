package cn.itsource.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.domain.ArticleType;
import cn.itsource.cms.query.ArticleQuery;
import cn.itsource.cms.service.IArticleService;
import cn.itsource.cms.service.IArticleTypeService;
import cn.itsource.cms.util.AjaxResult;
import cn.itsource.cms.util.PageBean;

/**  
* @Title: ArticleController.java
* @Package:cn.itsource.cms.controller
* @Description:(文章Article模块)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
//Controller就是单例的(使用成员变量请注意线程安全问题)
//注：不要忘了,配置全注解扫描包才能认识@Controller
@Controller
@RequestMapping("/system/article")//配置访问路径
public class ArticleController {
	
	/**
	 * @Autowired注解标记可以用在字段定义或setter方法定义前面：
		一般用于注入容器中对象，该注解可以直接写在字段上，可以不需要setter方法
		注意：使用该注解之前，一定要确保容器中是否有该类型的对象，否则注入失败；
		这里用于注入IArticleService
	 */
	@Autowired
	private IArticleService service;
	
	@Autowired
	private IArticleTypeService typeService;
	
	/**
	 * @Description:(查询所有)
	 * @param:@return   
	 * @return:List<Article>  
	 * @author:Joi
	 * @date:2020年6月5日
	 * @version:V1.0
	 */
	@RequestMapping("/index")
	public String findAll(Model model) {
		List<ArticleType> types = typeService.findAll();
		model.addAttribute("types", types);
		return "article/article";
	}
	
  	/**
  	 * @Description:(匹配请求/system/article/datagrid  调用service代码，传递查询条件)
  	 * @param:@param query
  	 * @param:@return   
  	 * @return:PageBean<Article>  
  	 * @author:Joi
  	 * @date:2020年6月5日
  	 * @version:V1.0
  	 */
  	@RequestMapping("/datagrid")
	@ResponseBody//跨过视图解析器，返回json格式对象
	public PageBean<Article> datagrid(ArticleQuery query) {
		//调用service代码，传递查询条件
		return service.findPageList(query);
	}

  	/**
  	 * @Description:(删除数据)
  	 * @param:@param id
  	 * @param:@return   
  	 * @return:AjaxResult  
  	 * @author:Joi
  	 * @date:2020年6月6日
  	 * @version:V1.0
  	 */
  	@RequestMapping("/del")
  	@ResponseBody
  	public AjaxResult del(Long id,HttpServletRequest req) {
  		try{
  			service.del(id,req);
	  		return new AjaxResult();
		} catch (Exception e) {
			return new AjaxResult(false,"操作失败！");
		}
	}
  	
  	/**
  	 * @Description:(模态框点击添加和修改之后的业务逻辑)
  	 * @param:@param article
  	 * @param:@return   
  	 * @return:AjaxResult  
  	 * @author:Joi
  	 * @date:2020年6月6日
  	 * @version:V1.0
  	 */
  	@RequestMapping("/save")
  	@ResponseBody
  	public AjaxResult save(Article article,HttpServletRequest req) {
  		try {
  			service.save(article,req);
  			return new AjaxResult();
		} catch (Exception e) {
			return new AjaxResult(false,"操作失败！");
		}
	}
	
}
