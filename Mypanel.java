package practice6;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mypanel extends JPanel { //�Զ���һ�����,Ϊ��������úñ���ͼƬ
	private static final long serialVersionUID = 1L;
	ImageIcon icon;
	Image img;
	public Mypanel(String pic) {
		icon = new ImageIcon(this.getClass().getResource(pic));
		img = icon.getImage();
	}
	@Override
	public void paintComponent(Graphics g) {
	/*��java��Ϊ��Ҫ���»��������ʱ����java���á�
	�������ڳ�����repaint();���߳��򴰿���С����Ȼ��ָ������߳��򴰿ڱ��ڵ��������ֵ�ʱ����ע��۲죬��������Ǹ��ܱ����ķ����������˵����ƽ�������ù�����������������ֻ������Ҫ�̳�paintComponent(һ����JFrame)��ʱ����д����,Ҳ���Բ����·���������㲻��Ҫ�ı������������Ļ�����*/
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
