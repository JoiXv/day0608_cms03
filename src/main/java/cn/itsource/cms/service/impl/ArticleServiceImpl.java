package cn.itsource.cms.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.cms.domain.Article;
import cn.itsource.cms.domain.ArticleType;
import cn.itsource.cms.mapper.ArticleMapper;
import cn.itsource.cms.mapper.ArticleTypeMapper;
import cn.itsource.cms.query.ArticleQuery;
import cn.itsource.cms.service.IArticleService;
import cn.itsource.cms.util.Constent;
import cn.itsource.cms.util.FreeMarkerUtil;
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
	public void del(Long id,HttpServletRequest req) {
		//先根据id查询一个article对象
		Article article = mapper.findOne(id);
		mapper.del(id);
		String url = article.getUrl();
		String path = req.getServletContext().getRealPath("/static/template");
		File file = new File(path, url);
		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public void save(Article article,HttpServletRequest req) {
		//  获取/static/template的模板路径
		//增加HttpServletRequest为了获取getRealPath  即模板文件的绝对路径
		String templatePath = req.getServletContext().getRealPath("/static/template");
		//生成静态资源
		String url = FreeMarkerUtil.getFile(templatePath, "article.ftl", article, ".html");
		//将文件的名称设置回对象中
		article.setUrl(url);
		
		if (article.getId() == null) {//添加操作
			mapper.add(article);
		}else {//更新操作
			//查找对应id的Article对象
			Article one = mapper.findOne(article.getId());
			//执行更新操作
			mapper.update(article);
			
			File file = new File(templatePath, one.getUrl());
			//如果文件存在，删除以前的html页面
			if (file.exists()) {
				file.delete();
			}
		}//else
	}//save

	//前台首页：查询所有文章的业务逻辑
	@Override
	public Map<String, Object> articles() {
		Map<String,Object> map = new HashMap<>();//jdk8以后可以这样使用,jdk7不行，需要将HashMap<>()中的泛型补齐
		//技术文章
		List<Article> articles1 = mapper.findListByCode(Constent.TECHNOLOGY);
		map.put("technology", articles1);
		//行业新闻
		List<Article> articles2 = mapper.findListByCode(Constent.INDUSTRY);
		map.put("industry", articles2);
		//学科咨询
		List<Article> articles3 = mapper.findListByCode(Constent.SUBJECT);
		map.put("subject", articles3);
		return map;
	}

	
	//更新点击次数
	@Override
	public Article updateArticleClickCount(String url) {
		//1、根据url查询文章article
		Article article = mapper.findOneByUrl(url);
		//2、查询article点击次数，设置点击次数+1
		article.setClickCount(article.getClickCount()+1);
		//3、执行article的更新操作
		mapper.update(article);
		return article;
	}

}
