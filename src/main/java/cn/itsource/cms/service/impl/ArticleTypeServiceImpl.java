package cn.itsource.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.cms.domain.ArticleType;
import cn.itsource.cms.mapper.ArticleTypeMapper;
import cn.itsource.cms.service.IArticleTypeService;

@Service
public class ArticleTypeServiceImpl implements IArticleTypeService{
	
	@Autowired
	private ArticleTypeMapper mapper;

	@Override
	public List<ArticleType> findAll() {
		return mapper.findAll();
	}

	
}
