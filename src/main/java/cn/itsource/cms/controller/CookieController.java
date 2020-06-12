package cn.itsource.cms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c")
public class CookieController {
	
	/**
	 * @Description:(测试添加Cookie)
	 * @param:@param resp   
	 * @return:void  
	 * @author:Joi
	 * @date:2020年6月11日
	 * @version:V1.0
	 */
	@RequestMapping("/c1")
	public void testCookie(HttpServletResponse resp) {
		//resp.setCharacterEncoding("UTF-8");
		//先创建
		Cookie c1 = new Cookie("c1","21");
		Cookie c2 = new Cookie("c2","32");
		Cookie c3 = null;
		try {
			c3 = new Cookie("c3",URLEncoder.encode("测试222222","UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		c1.setMaxAge(-1);//默认
		c2.setMaxAge(0);//立即删除
		c3.setMaxAge(8);//保存在硬盘上
		
		c1.setPath("/");
		
		//后添加
		resp.addCookie(c1);
		resp.addCookie(c2);
		resp.addCookie(c3);
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<h3>添加成功！</h3>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:(查询当前浏览器中的Cookie)
	 * @param:@param resp
	 * @param:@param req
	 * @param:@throws IOException   
	 * @return:void  
	 * @author:Joi
	 * @date:2020年6月11日
	 * @version:V1.0
	 */
	@RequestMapping("/c2")
	public void find(HttpServletResponse resp,HttpServletRequest req) throws IOException {
		//告诉浏览器，需求是响应一个html页面
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		
		//获取所有的cookie
		Cookie[] cookies = req.getCookies();
		if (cookies == null) {
			writer.println("<h3>未添加Cookie！</h3>");
		}
		for (Cookie cookie : cookies) {
			writer.println("<h3>"+cookie.getName()+"  =  "+URLDecoder.decode(cookie.getValue(), "UTF-8")+"</h3>");
		}
	}
}
