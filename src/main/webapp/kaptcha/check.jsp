<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script>
	$(function(){
		$("#kaptchaImage").click(function(){
			$(this).hide()  // 隐藏原有图片
				.attr("src", "kaptcha.jpg?"+Math.floor(Math.random()*100)) // 切换图片
				.fadeIn();	// 显示新图片
		})
	})
</script>
</head>

<body>${kaptcha }

	<form action="kaptcha.action">
		<img id="kaptchaImage" src="kaptcha.jpg" /> <br/><br/>
		<input type="text" name="kaptcha" value="" />
		<input type="submit" value="submit" />
	</form>

</body>
</html>