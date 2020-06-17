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
		Toolkit tool;   //ʹ��Toolkit���Ի�ñ���ϵͳ����Ļ�Ĳ���
		//���췽��
		
		
		public CalendarImage() {
		tool=getToolkit();//��ȡ���ߣ�
		}
		//����ͼ���ļ���
		
		
		public void setImageFile(File f)
		{
		imageFile =f;   //
		
		try{
		image=tool.getImage(imageFile.toURI().toURL());//�õ�ͼ���ļ���
		}
		catch (Exception e)
		{
		}
		repaint();//�ػ棻
		}
		//���� paintComponent ������ ����ԭ���ģ� �� repaint()���ã�
		
		
		public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		int w=getBounds().width;
		int h=getBounds().height;
		g.drawImage(image,0,0,w,h,this);
		}
		}
