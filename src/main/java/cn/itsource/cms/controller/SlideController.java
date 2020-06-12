package cn.itsource.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itsource.cms.domain.Slide;
import cn.itsource.cms.query.SlideQuery;
import cn.itsource.cms.service.ISlideService;
import cn.itsource.cms.util.AjaxResult;
import cn.itsource.cms.util.PageBean;

@Controller
@RequestMapping("/system/slide")
public class SlideController {
	
	@Autowired//注入
	private ISlideService service;

	/**
	 * @Description:(跳转到slide.jsp页面)
	 * @param:@return   
	 * @return:String  
	 * @author:Joi
	 * @date:2020年6月9日
	 * @version:V1.0
	 */
	@RequestMapping("/index")
	public String goIndex() {
		return "slide/slide";
	}
	
	/**
  	 * @Description:(匹配请求/system/slide/datagrid  调用service代码，传递查询条件)
  	 * @param:@param query
  	 * @param:@return   
  	 * @return:PageBean<Slide>  
  	 * @author:Joi
  	 * @date:2020年6月9日
  	 * @version:V1.0
  	 */
  	@RequestMapping("/datagrid")
	@ResponseBody//跨过视图解析器，返回json格式对象
	public PageBean<Slide> datagrid(SlideQuery query) {
		//调用service代码，传递查询条件
		return service.findPageList(query);
	}
	
  	/**
  	 * @Description:(处理轮播图的新增和更新操作)
  	 * @param:@param article
  	 * @param:@param req
  	 * @param:@param photo
  	 * @param:@return   
  	 * @return:AjaxResult  
  	 * @author:Joi
  	 * @date:2020年6月9日
  	 * @version:V1.0
  	 */
  	@RequestMapping("/save")
  	@ResponseBody
  	public AjaxResult save(Slide slide,HttpServletRequest req,MultipartFile photo) {
  		try {
//  			System.out.println("执行save操作："+slide);
//  			System.out.println("执行save操作："+photo);
  			service.save(slide,req,photo);
  			return new AjaxResult(true,"操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(false,"操作失败！");
		}
	}
  	
  	/**
  	 * @Description:(处理轮播图的删除操作)
  	 * @param:@param id
  	 * @param:@param req
  	 * @param:@return   
  	 * @return:AjaxResult  
  	 * @author:Joi
  	 * @date:2020年6月10日
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
  	
}
