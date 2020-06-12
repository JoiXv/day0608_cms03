package cn.itsource.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itsource.cms.util.Constent;

/**  
* @Title: LoginInterceptor.java
* @Package:cn.itsource.cms.interceptor
* @Description:(登陆拦截器)
* @author:Joi
* @date:2020年6月12日
* @version:V1.0  
*/
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		Object uesr = req.getSession().getAttribute(Constent.USER_IN_SESSION);//获取到session中的user对象
		if (uesr == null) {//如果SESSION中的值为空，就是登陆失败，拦截器拦截
			resp.sendRedirect("/system/login");
			return false;//拦截
		}
		return true;//放行
	}

}
