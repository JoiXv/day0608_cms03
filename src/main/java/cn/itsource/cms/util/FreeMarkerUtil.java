package cn.itsource.cms.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**  
* @Title: FreeMarkUtil.java
* @Package:cn.itsource.cms.util
* @Description:(封装FreeMark的工具类)
* @author:Joi
* @date:2020年6月8日
* @version:V1.0  
*/
public class FreeMarkerUtil {
	
	/**
	 * @Description:(获取文件)
	 * @param:@param templatePath：模板的路径
	 * @param:@param templateName：模板名称
	 * @param:@param data：传入模板的数据
	 * @param:@param suffix：输出文件后缀
	 * @param:@return   
	 * @return:String  
	 * @author:Joi
	 * @date:2020年6月8日
	 * @version:V1.0
	 */
	public static String getFile(String templatePath,String templateName,Object data,String suffix) {
		FileWriter out = null;
		try {
			Configuration config = new Configuration(Configuration.VERSION_2_3_28);//获取最新版本
			//获取Configuration对象 -- 为了获取模板对象
			File file = new File(templatePath);//相对路径
			//		设置默认加载路径
			config.setDirectoryForTemplateLoading(file);
			//		设置默认编码
			config.setDefaultEncoding("UTF-8");
			//		获取模板
			Template template = config.getTemplate(templateName);
			long filename = System.currentTimeMillis();
			out = new FileWriter(new File(file , filename+suffix));
			//	4.template.process()生成静态资源
			template.process(data, out);
			return filename+suffix;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
