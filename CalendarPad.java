package practice5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class CalendarPad extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	Calendar1 calendar1;
	int year,month,day;
	JTextField []showDay;
	JLabel title[];
	String[]WEEK= {"SUN","MON","TUE","WED","THU","FRI","SAT"};
	JPanel north,center;
	
	public CalendarPad() {
		setLayout(new BorderLayout());
		north=new JPanel();
		north.setLayout(new GridLayout(1,7));
		center=new JPanel();
		center.setLayout(new GridLayout(6,7));
		add(center,BorderLayout.CENTER);
		add(north,BorderLayout.NORTH);
		title=new JLabel[7];

	for(int j=0;j<7;j++){   
		title[j]=new JLabel();
		title[j].setFont(new Font("TimesRoman",Font.BOLD,15));
		title[j].setText(WEEK[j]);
		title[j].setHorizontalAlignment(JLabel.CENTER);
		title[j].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.RED));

		north.add(title[j]);
		title[j].setBackground(Color.green);
		
		}
		title[0].setForeground(Color.red);
		title[6].setForeground(Color.blue);
		
		}
		public void setShowDayTextField(JTextField [] text){ 
		showDay=text;
		for(int i=0;i<showDay.length;i++){
		showDay[i].setFont(new Font("TimesRoman",Font.BOLD,15));
		showDay[i].setHorizontalAlignment(JTextField.CENTER);
		showDay[i].setEditable(false);
		center.add(showDay[i]);
		showDay[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.green));
		}
		}
		public void setCalendarMessage(Calendar1 calendar){
		this.calendar1=calendar;
		}
		public void showMonthCalendar(){
		String [] a= calendar1.getMonthCalendar();   
		for(int i=0;i<42;i++)
		showDay[i].setText(a[i]);
		validate();  
		}
		public void setCalendar(Calendar1 calendar) {
			// TODO 自动生成的方法存根
			
		} 
		}