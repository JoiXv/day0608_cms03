package cn.itsource.cms.service;

import cn.itsource.cms.domain.User;
import cn.itsource.cms.exception.AuthenticationException;

public interface IUserService {

	/**
	 * @Description:(根据用户名和密码查询用户,抛出自定义异常AuthenticationException)
	 * @param:@param username
	 * @param:@param password
	 * @param:@return
	 * @param:@throws AuthenticationException   
	 * @return:User  
	 * @author:Joi
	 * @date:2020年6月11日
	 * @version:V1.0
	 */
	User findByUsernameAndPassword(String username, String password)throws AuthenticationException;
	
}
