package cn.itsource.cms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/s")
public class SessionController {
	
	@RequestMapping("/s1")
	public String s1(HttpServletRequest req,HttpSession session) {
		HttpSession s2 = req.getSession();
		System.out.println("比较s1 与 session："+(s2 == session));
		
		//从不操作时开始计时,销毁HttpSession对象
		session.setMaxInactiveInterval(8);
		
		session.setAttribute("name","铁憨憨");
		s2.setAttribute("age", 2.5);
		s2.setAttribute("birthday", new Date());
		
		return "redirect:/session.jsp";
	}
	
}
