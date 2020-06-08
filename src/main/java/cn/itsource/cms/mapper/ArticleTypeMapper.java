package cn.itsource.cms.mapper;

import java.util.List;

import cn.itsource.cms.domain.ArticleType;


/**  
* @Title: ArticleTypeMapper.java
* @Package:cn.itsource.cms.mapper
* @Description:(文章类型t_article_type表的mapper)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
public interface ArticleTypeMapper {
	
	/**
	 * @Description:(查询单个文章id对应的文章类型)
	 * @param:@param id
	 * @param:@return   
	 * @return:ArticleType  
	 * @author:Joi
	 * @date:2020年6月5日
	 * @version:V1.0
	 */
	ArticleType findOne(Long id);
	
	//查询所有
	List<ArticleType> findAll();
	
}
