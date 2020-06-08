package cn.itsource.cms.mapper;

import java.util.List;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.query.ArticleQuery;

/**  
* @Title: ArticleMapper.java
* @Package:cn.itsource.cms.mapper
* @Description:(文章t_article表的mapper接口)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
public interface ArticleMapper {
	
	//查询所有
	List<Article> findAll();

	//查询总数量
	Long findPageCount();

	//分页查询
	List<Article> findPageList(ArticleQuery query);

	//分类查询
	Long findPageCountByQuery(ArticleQuery query);
	
	//删除
	void del(Long id);

	//添加
	void add(Article article);

	//更新
	void update(Article article);

	//根据学科类型code查询文章
	List<Article> findListByCode(String code);
	
}
