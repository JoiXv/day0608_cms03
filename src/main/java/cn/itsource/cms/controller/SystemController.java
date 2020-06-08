package cn.itsource.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
* @Title: SystemController.java
* @Package:cn.itsource.cms.controller
* @Description:(进入管理系统后台)
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
@Controller
@RequestMapping("/system")
public class SystemController {

	/**
	 * @Description:(进入后台首页)
	 * @param:@return   
	 * @return:String  
	 * @author:Joi
	 * @date:2020年6月5日
	 * @version:V1.0
	 */
	@RequestMapping("/index")//经过视图解析器
	public String goSystemIndex() {
		return "index";
	}
	
	
	
}
