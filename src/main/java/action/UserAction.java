package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import entity.User;
import service.UserService;

@Action("user")
@Results({
	@Result(name="success",location="/index.jsp"),
	@Result(name="input",location="/login.jsp"),
	@Result(name="error",location="/error.jsp")
})
@ExceptionMappings({	// 声明式异常处理, 当前2.3.20版本此功能不可用
	@ExceptionMapping(exception="java.lang.Exception",result="error")
})
public class UserAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	// action中定义属性并提供set方法, struts会自动创建对象并根据请求数据进行封装
	private User user;
	
	/**
	 * 数据校验方法, 方法名符合struts规范:validateXXX(), 在XXX方法之前自动执行
	 */
	public void validateLogin() {//数据校验 - 自动执行(在执行Login方法之前)
		if (user.getUsername()==null || user.getUsername().equals("")) {
			addFieldError("user.username", "username can not null!"); // struts提供方法, 该信息会在相应的组件上方显示
		}
		if (user.getPassword()==null || user.getPassword().equals("")) {
			addFieldError("user.password", "password can not null!");
		}
	}

	/**
	 * 用户登录
	 * @return
	 */
	public String login(){//自定义方法 - 自动执行(通过!login访问时)
		UserService userService = new UserService();
		if(userService.isUser(user)){
			return SUCCESS;
		}else{
			addActionMessage("username or password is error!"); // struts提供方法, 该信息会在返回的页面显示
			return INPUT;
		}
	}

	/**
	 * 测试异常处理
	 * @return
	 */
	@Action(value="error")
	public void error(){
		user.getPassword();
	}
	
	
	//使用struts2自动装配数据 - 必写
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
