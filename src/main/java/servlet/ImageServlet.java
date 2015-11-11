package servlet;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ImageUtil_en;

@WebServlet("/java_check/image")
public class ImageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ImageUtil_en imageUtil = new ImageUtil_en();
//		ImageUtil_en imageUtil = new ImageUtil_en(100,20);	//指定验证码图片的大小
//		ImageUtil_en imageUtil = new ImageUtil_en(100,20,5);	//可指定验证码图片的大小和字符数量
//		ImageUtil_en imageUtil = new ImageUtil_en(200,30,7,26);	//可指定验证码图片的大小和字符数量及大小
		
		//获取验证码图文组合
		Map<?, ?> checkCode = imageUtil.getCheckCode();
		//将验证码内容写入session
		req.getSession().setAttribute("checkstring", checkCode.get("checkstring"));
		//将验证码图片传到页面
		ImageIO.write((RenderedImage) checkCode.get("checkimage"), "JPEG", resp.getOutputStream());  

        //禁止页面缓存   
        resp.addHeader("Pragma", "No-Cache");   
        resp.addHeader("Cache-Control", "No-Cache");   
        resp.addHeader("Expires", "0"); 
		
	}


}
