package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码图片生成工具 - 英文
 * @author yj
 */
public class ImageUtil_en {

    //随机的字符库
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //字符串的字体
    private String fontType = "Fixedsys";
    
    private int width = 100;		//图片宽
    private int height = 25;	//图片高
    private int fontNum = 4;	//字符数量
    private int fontSize = 20;	//字符大小
    private int lineSize = 40;	//干扰线数量
    
    private Random random = new Random();		//持有随机类的引用
    
    
    //无参构造方法(验证码图片默认大小)
    public ImageUtil_en(){
    	
    }
    //有参构造方法(可指定验证码图片的大小)
    public ImageUtil_en(int width, int height){
    	this.width = width;
    	this.height = height;
    }
    //有参构造方法(可指定验证码图片的大小和字符数量)
    public ImageUtil_en(int width, int height, int number){
    	this.width = width;
    	this.height = height;
    	this.fontNum = number;
    }
    //有参构造方法(可指定验证码图片的大小和字符数量及大小)
    public ImageUtil_en(int width, int height, int fontNum, int fontSize){
    	this.width = width;
    	this.height = height;
    	this.fontNum = fontNum;
    	this.fontSize = fontSize;
    }
    
    //生成随机图片
    public Map<String, Object> getCheckCode() {
  
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage randomImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        //产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = randomImage.getGraphics();	
 
        graphics.fillRect(0, 0, width, height);	//填充整个图片(背景)
        
        graphics.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        graphics.setColor(getRandColor(110, 133));
        
        //绘制干扰线
        for(int i=0;i<=lineSize;i++){
            drowLine(graphics);
        }
        
        //绘制随机字符
        String randomString = "";
        for(int i=1;i<=fontNum;i++){
            randomString=drowString(graphics,randomString,i);
        }
        
        //将随机图片和字符串放入map
        Map<String, Object> checkCode = new HashMap<String, Object>();
        checkCode.put("checkstring", randomString);
        checkCode.put("checkimage",randomImage);

        //释放资源
        graphics.dispose();
        
		return checkCode;

    }
    
    
    //获得颜色
    private Color getRandColor(int fc,int bc){
        if(fc > 255)	fc = 255;
        if(bc > 255)	bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    
    //绘制干扰线
    private void drowLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }

    //绘制字符串
    private String drowString(Graphics g,String randomString,int i){
        g.setFont(new Font(fontType,Font.CENTER_BASELINE,fontSize));
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, fontSize*i-random.nextInt(fontSize/2), height/2+fontSize/4);//随机位置放置验证码       
        
        return randomString;
    }

    //获取随机的字符
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
    
    
}