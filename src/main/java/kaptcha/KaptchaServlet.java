package kaptcha;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.Constants;

public class KaptchaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 预期验证码
		String kaptchaExpected = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

		// 收到验证码
		String kaptchaReceived = request.getParameter("kaptcha");
		
		if (kaptchaReceived != null && kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
			request.setAttribute("kaptcha", "check success");
		} else {
			request.setAttribute("kaptcha", "check fail");
		}
		
		request.getRequestDispatcher("kaptcha/check.jsp").forward(request, response);
		
	}

	

}
