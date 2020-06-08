package cn.itsource.cms.service;

import java.util.List;

import cn.itsource.cms.domain.ArticleType;

public interface IArticleTypeService {
	
	//查询所有
	List<ArticleType> findAll();
	
}
