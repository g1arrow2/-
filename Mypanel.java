package practice6;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mypanel extends JPanel { //自定义一个面板,为该面板设置好背景图片
	private static final long serialVersionUID = 1L;
	ImageIcon icon;
	Image img;
	public Mypanel(String pic) {
		icon = new ImageIcon(this.getClass().getResource(pic));
		img = icon.getImage();
	}
	@Override
	public void paintComponent(Graphics g) {
	/*当java认为需要重新绘制组件的时候由java调用。
	例如你在程序中repaint();或者程序窗口最小化，然后恢复。或者程序窗口被遮挡，又显现的时候。你注意观察，这个方法是个受保护的方法，这就是说我们平常并不用管这个方法，这个方法只在你需要继承paintComponent(一般是JFrame)的时候，重写方法,也可以不重新方法，如果你不需要改变绘制组件动作的话）。*/
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
