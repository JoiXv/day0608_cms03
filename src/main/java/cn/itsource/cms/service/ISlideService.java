package cn.itsource.cms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.itsource.cms.domain.Slide;
import cn.itsource.cms.query.SlideQuery;
import cn.itsource.cms.util.PageBean;

/**  
* @Title: ISlideService.java
* @Package:cn.itsource.cms.service
* @Description:(轮播图业务类，处理轮播图模块Slide的业务)
* @author:Joi
* @date:2020年6月9日
* @version:V1.0  
*/
public interface ISlideService {

	
	/**
	 * @Description:(查询轮播图页面，返回满足gridManager的ajax接收数据的totals和data
	 * 								后台：分页查询  +  高级查询)
	 * @param:@param query
	 * @param:@return   
	 * @return:PageBean<Slide>  
	 * @author:Joi
	 * @date:2020年6月9日
	 * @version:V1.0
	 */
	PageBean<Slide> findPageList(SlideQuery query);

	/**
	 * @Description:(处理slide的保存请求)
	 * @param:@param slide
	 * @param:@param req
	 * @param:@param photo   
	 * @return:void  
	 * @author:Joi
	 * @date:2020年6月9日
	 * @version:V1.0
	 */
	void save(Slide slide, HttpServletRequest req, MultipartFile photo);

	
	/**
	 * @Description:(删除操作)
	 * @param:@param id
	 * @param:@param req   
	 * @return:void  
	 * @author:Joi
	 * @date:2020年6月10日
	 * @version:V1.0
	 */
	void del(Long id, HttpServletRequest req);

	List<Slide> findSlides();

	
}
