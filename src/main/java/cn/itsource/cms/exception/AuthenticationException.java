package cn.itsource.cms.exception;

/**  
* @Title: AuthenticationException.java
* @Package:cn.itsource.cms.exception
* @Description:(自定义登陆异常)
* @author:Joi
* @date:2020年6月11日
* @version:V1.0  
*/
@SuppressWarnings("serial")
public class AuthenticationException extends Exception{

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message) {
		super(message);
	}
	
}
