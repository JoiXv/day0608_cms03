package cn.itsource.cms.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itsource.cms.domain.User;
import cn.itsource.cms.service.IUserService;
import cn.itsource.cms.util.AjaxResult;
import cn.itsource.cms.util.Constent;

@Controller
@RequestMapping("/system")
public class UserController {

	@Autowired
	private IUserService service;
	
	/**
	 * @Description:(返回登陆首页界面)
	 * @param:@return   
	 * @return:String  
	 * @author:Joi
	 * @date:2020年6月12日
	 * @version:V1.0
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult Login(String username,String password,HttpSession session,
			Integer remember,HttpServletResponse resp,HttpServletRequest req) {
		try {
			User dbUser = service.findByUsernameAndPassword(username,password);
			session.setAttribute(Constent.USER_IN_SESSION, dbUser);
			
			if (remember != null) {//如果登陆成功，且点击了记住我选项
				//创建用户名和密码的Cookie对象
				Cookie c1 = new Cookie(Constent.USERNAME,username);
				Cookie c2 = new Cookie(Constent.PASSWORD,password);
				//设置销毁时间
				c1.setMaxAge(7*24*60*60);//设置失效时间为7天   保存在浏览器的磁盘中
				c2.setMaxAge(7*24*60*60);
				//设置根路径有效
				c1.setPath("/");
				c2.setPath("/");
				resp.addCookie(c1);
				resp.addCookie(c2);
			}else {//没点击记住我
				Cookie[] cookies = req.getCookies();//拿到所有Cookie
				for (Cookie c : cookies) {//遍历Cookie
					if (Constent.USERNAME.equals(c.getName()) || Constent.PASSWORD.equals(c.getName())) {
						c.setMaxAge(0);//清除该Cookie
						c.setPath("/");//删除所有的路径下的该Cookie
						resp.addCookie(c);
					}//if
				}//fore
			}//else
			return new AjaxResult();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return new AjaxResult(false,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		//销毁session对象
		session.invalidate();
		return "login/login";
	}
	
}
