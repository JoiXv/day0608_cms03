package cn.itsource.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**  
* @Title: SpringTest.java
* @Package:cn.itsource.test
* @Description:(
* 				@RunWith：代表开启Spring的测试
 * 					SpringJUnit4ClassRunner：代表是Junit4的测试环境
 * 				@ContextConfiguration：找到我们的核心配置文件
* )
* @author:Joi
* @date:2020年6月5日
* @version:V1.0  
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {

	/**
	 * @Autowired的意思是自动注入，ApplcationContext这个类是Spring内部存在的，它也是一个Bean，
	 * 		Spring可以把它创建出来，在它看到上面的这个标签后，再把创建的Bean注入进来
	 */
	@Autowired
	private Date date;
	
	@Test
	public void testName() throws Exception {
		System.out.println(date);
	}
	
}
