package arrow3;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TimerFrame extends JFrame {

	private NumberTubeTimer tubeTimerPanel;
	private Timer timer;
	private Date now = new Date();
	int hour,minute,second;
	public TimerFrame()
	{   super("模拟数码管计时器");
		hour = now.getHours();
		minute = now.getMinutes();
		second = now.getSeconds();
		tubeTimerPanel = new NumberTubeTimer(hour,minute,second);
		tubeTimerPanel.setLineLength(20);
		timer=tubeTimerPanel.getTimer();
		timer.start();
		
		this.getContentPane().add(tubeTimerPanel);
		
		this.setSize(300,120);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new TimerFrame();

	}

}
