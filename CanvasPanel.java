package practice5;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

	public class CanvasPanel extends Canvas{
		private static final long serialVersionUID =1L; 
		private Image img;
		Toolkit tool;
		public  CanvasPanel(Image image) {
			img =image;
		}
		public CanvasPanel() {
			tool=getToolkit();//获取工具；
			}
		public void changeImage(Image image) {
			img=image;
			repaint();
		}
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2=(Graphics2D)g;
			int w=getBounds().width;
			int h=getBounds().height;
			g2.drawImage(img, 0, 0,w,h, this);
			}
}