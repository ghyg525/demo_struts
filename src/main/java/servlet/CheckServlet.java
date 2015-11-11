package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/java_check/check")
public class CheckServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		
		String checkString = req.getSession().getAttribute("checkstring").toString();
		String inputString = req.getParameter("inputstring");
		
		if (checkString.equals(inputString.toUpperCase())) {
			resp.getWriter().write("验证成功!");
		}
		else {
			resp.getWriter().write("验证失败!");
		}
		
	}


}
