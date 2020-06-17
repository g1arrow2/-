package practice4;
import javax.swing.*;
import java.io.*;
import java.awt.*;
public class CalendarImage extends JPanel
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		File imageFile;
		Image image;
		Toolkit tool;   //使用Toolkit可以获得本机系统的屏幕的参数
		//构造方法
		
		
		public CalendarImage() {
		tool=getToolkit();//获取工具；
		}
		//设置图像文件；
		
		
		public void setImageFile(File f)
		{
		imageFile =f;   //
		
		try{
		image=tool.getImage(imageFile.toURI().toURL());//得到图像文件；
		}
		catch (Exception e)
		{
		}
		repaint();//重绘；
		}
		//重载 paintComponent 方法， 覆盖原来的， 供 repaint()调用；
		
		
		public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		int w=getBounds().width;
		int h=getBounds().height;
		g.drawImage(image,0,0,w,h,this);
		}
		}
