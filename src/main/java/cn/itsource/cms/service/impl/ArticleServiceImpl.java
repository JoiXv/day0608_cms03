package cn.itsource.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.domain.ArticleType;
import cn.itsource.cms.mapper.ArticleMapper;
import cn.itsource.cms.mapper.ArticleTypeMapper;
import cn.itsource.cms.query.ArticleQuery;
import cn.itsource.cms.service.IArticleService;
import cn.itsource.cms.util.Constent;
import cn.itsource.cms.util.PageBean;

@Service
/*@Service注解作用  -----实例化bean的注解
 *1、 其getBean的默认名称是类名（头字母小写），可以@Service(“xxxx”)这样来指定，
   2、其定义的bean默认是单例的，可以使用@Service(“beanName”) @Scope(“prototype”)来改变。
   3、可以通过@PostConstruct和@PreDestroy指定初始化方法和销毁方法（方法名任意）
 * */
public class ArticleServiceImpl implements IArticleService{

	/**
	 * ArticleMapper注入
	 */
	@Autowired
	private ArticleMapper mapper;
	
	/**
	 * ArticleTypeMapper注入
	 */
	@Autowired
	private ArticleTypeMapper typeMapper;

	/**
	 * 查询指定页面中的内容返回一个list对象，前台传入一个ArticleQuery对象（包含localPage和pageSize），
	 * 	然后返回一个满足gridManager规范的PageBean对象，用于前台接收数据
	 */
	@Override
	public PageBean<Article> findPageList(ArticleQuery query) {
		//System.out.println(query);
		//1、先查询数量（修改ArticleMapper接口中的查询为查询总数量的高级查询）
		Long totals = mapper.findPageCountByQuery(query);
		if (totals == 0l) {//根据查询条件没匹配到对象，返回一个空的默认PageBean
			return new PageBean<Article>();
		}
		//查询指定页面中的内容返回一个list对象
		//修改ArticleMapper.xml的findPageList方法为高级分页查询
		List<Article> articles = mapper.findPageList(query);
		for (Article article : articles) {
			//查询文章id
			Long id = article.getTypeId();
			//设置的是整个ArticleType对象，里面包含id name code等内容
			ArticleType type = typeMapper.findOne(id);
			//设置文章类型
			article.setType(type);
		}
		return new PageBean<Article>(totals,articles);
	}

	@Override
	public void del(Long id) {
		mapper.del(id);
	}

	@Override
	public void save(Article article) {
		if (article.getId() == null) {//更新操作
			mapper.add(article);
		}else {//添加操作
			mapper.update(article);
		}
	}

	@Override
	public Map<String, Object> articles() {
		Map<String,Object> map = new HashMap<>();//jdk8以后可以这样使用,jdk7不行，需要将HashMap<>()中的泛型补齐
		//技术文章
		List<Article> articles1 = mapper.findListByCode(Constent.TECHNOLOGY);
		//行业新闻
		List<Article> articles2 = mapper.findListByCode(Constent.INDUSTRY);
		//学科咨询
		List<Article> articles3 = mapper.findListByCode(Constent.SUBJECT);
		return null;
	}
	
}
