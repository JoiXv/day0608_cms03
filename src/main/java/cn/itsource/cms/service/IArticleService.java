package cn.itsource.cms.service;

import java.util.Map;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.query.ArticleQuery;
import cn.itsource.cms.util.PageBean;

public interface IArticleService {
	
//	List<Article> findAll();
	
	/**
	 * @Description:(查询指定页面中的内容返回一个list集合，
	 * 								过程可以理解为：前台传入一个ArticleQuery对象（包含localPage和pageSize），
	 * 														然后返回一个满足gridManager规范的PageBean对象，用于前台接收数据)
	 * @param:@param query
	 * @param:@return   
	 * @return:PageBean<Article>  
	 * @author:Joi
	 * @date:2020年6月5日
	 * @version:V1.0
	 */
	PageBean<Article> findPageList(ArticleQuery query);

	void del(Long id);

	void save(Article article);

	Map<String, Object> articles();
	
}