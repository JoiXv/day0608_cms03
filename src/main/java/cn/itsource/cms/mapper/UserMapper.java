package cn.itsource.cms.mapper;

import cn.itsource.cms.domain.User;

public interface UserMapper {

	//注：密码加密  MD5
//	User findByUsernameAndPassword();

	User findOne(String username);

}
