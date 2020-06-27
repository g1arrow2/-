package arrow3;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class NumberTubeTimer extends JPanel implements ActionListener {
	private Timer timer ;
	private NumberCodeTube[] tubes;
	private int hours;
	private int minutes;
	private int seconds;
	private int lineLength;
	
	
	public NumberTubeTimer() {
	this(0,0,0);
	}
	
	
	public NumberTubeTimer(int hours,int minutes,int seconds) {
	this.hours = hours ;
	this.minutes = minutes;
	this.seconds = seconds;
	timer=new Timer(1000,this);
	//创建定时器对象，定时时间
	//设置模拟数码管中发光二极管的直线段的长度
	}
	
	
	public void setLineLength(int lineLength) {
	this. lineLength= lineLength;
	makeNumberCodeTube();
	//创建计时器中的6个数码
	}
	//创建计时器中的6个数码管对象，并存储于一维数组tubes中private void makeNumberCodeTube()

	private void makeNumberCodeTube() {
		tubes=new NumberCodeTube[6];
		tubes[0]=new NumberCodeTube(hours/10,lineLength,0,0);
		tubes[1]=new NumberCodeTube(hours%10,lineLength,2*lineLength,0);
		tubes[2]=new NumberCodeTube(minutes/10,lineLength,4*lineLength+10,0);
		tubes[3]=new NumberCodeTube(minutes%10,lineLength,6*lineLength+10,0);
		tubes[4]=new NumberCodeTube(seconds/10,lineLength,8*lineLength+20,0);
		tubes[5]=new NumberCodeTube(seconds%10,lineLength,10*lineLength+20,0);

	}
	
	
	public Timer getTimer() {
		return this.timer;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < tubes.length; i++) {
			tubes[i].draw(g);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		seconds++;
		tubes[5].setDisplayNumber(seconds%10);
		tubes[4].setDisplayNumber(seconds/10);
		
		if (seconds==60) {
			minutes++;
			seconds=0;
			tubes[4].setDisplayNumber(0);
		}
		tubes[3].setDisplayNumber(minutes%10);
		tubes[2].setDisplayNumber(minutes/10);
		if (minutes==60) {
			hours++;
			minutes=0;
			tubes[2].setDisplayNumber(0);
		}
		if (hours==24) {
			hours=0;
		}
		tubes[1].setDisplayNumber(hours%10);
		tubes[0].setDisplayNumber(hours/10);
		
		repaint();
	}

}
