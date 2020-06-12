package cn.itsource.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.cms.domain.User;
import cn.itsource.cms.exception.AuthenticationException;
import cn.itsource.cms.mapper.UserMapper;
import cn.itsource.cms.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper mapper;

	@Override
	public User findByUsernameAndPassword(String username, String password) throws AuthenticationException {
		User dbUser = mapper.findOne(username);
		if (dbUser == null) {//用户名不正确，未查询到用户
			throw new AuthenticationException("该用户不存在！");
		}else {
			if (!password.trim().equals(dbUser.getPassword())) {//用户名正确，密码不正确
				throw new AuthenticationException("密码错误！");
			}else {//用户名和密码均正确
				return dbUser;
			}
		}//else
	}// findByUsernameAndPassword
}
