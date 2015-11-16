package service;

import entity.User;

public class UserService {


	/**
	 * 验证用户信息
	 * @param user
	 * @return
	 */
	public boolean isUser(User user) {
		return "1".equals(user.getUsername()) && "1".equals(user.getPassword());
	}
}
