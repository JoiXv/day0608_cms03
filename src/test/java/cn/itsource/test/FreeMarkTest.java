package cn.itsource.test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.itsource.cms.domain.ArticleType;
import cn.itsource.cms.util.FreeMarkerUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkTest {
	
		//	1.导入freemarker.jar
	@Test
	public void testFtl() throws Exception {//	6.测试运行
		//	2.获取模板(Template)对象	
		Configuration config = new Configuration(Configuration.VERSION_2_3_28);//获取最新版本
		//获取Configuration对象 -- 为了获取模板对象
		File file = new File("src/main/webapp/static/template");
		//		设置默认加载路径
		config.setDirectoryForTemplateLoading(file);
		//		设置默认编码
		config.setDefaultEncoding("UTF-8");
		//		获取模板
		Template template = config.getTemplate("test.ftl");
		//	3.准备数据
		ArticleType type = new ArticleType(4l, "科技创新", "science");
		FileWriter out = new FileWriter(new File(file,"type.html"));
		//	4.template.process()生成静态资源
		template.process(type, out);
		//关流
		out.close();
	}
	
	/**
	 * @Description:(测试使用map数据类型添加数据)
	 * @param:@throws Exception   
	 * @return:void  
	 * @author:Joi
	 * @date:2020年6月8日
	 * @version:V1.0
	 */
	@Test
	public void testMap() throws Exception {
		//准备的数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 6);
		map.put("name", "张小天");
		map.put("age", 22);
		//输出
		String file = FreeMarkerUtil.getFile("src/main/webapp/static/template", "map.ftl", map, ".html");
		
		System.out.println(file);
	}
	
	@Test
	public void testList() throws Exception {
		//list集合不能直接用于ftl模板文件访问，需要使用map或java对象转换
		List<ArticleType> list = new ArrayList<>();
		list.add(new ArticleType(5l,"社会人文","social"));
		list.add(new ArticleType(6l,"中国地理","social"));
		list.add(new ArticleType(7l,"社会人文","social"));
		//准备的数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("types", list);//匹配名要一致
		//输出
		String file = FreeMarkerUtil.getFile("src/main/webapp/static/template", "test.ftl", map, ".html");
		
		System.out.println(file);
	}
	
}
