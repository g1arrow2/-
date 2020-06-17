package practice5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CalendarPanel2 extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int year,month,day;
	private JButton [] weekArray;
	JPanel north,center;
	JPanel title[];
	JTextField []showDay;
	//构造方法
	public CalendarPanel2(int y,int m,int d) {
		year=y;
		month=m;
		day=d;
		setLayout(new BorderLayout());
		JPanel panel1=new JPanel();
		panel1.setLayout(new GridLayout(1,7));
		center=new JPanel();
		center.setLayout(new GridLayout(6,7));
		add(center,BorderLayout.CENTER);
		add(panel1,BorderLayout.NORTH);
		weekArray=new JButton[7];
		String[]weekStr= {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		for (int i=0;i<weekArray.length;i++) {
			weekArray [i]=new JButton(weekStr[i]);
			weekArray[i].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.RED));
			panel1.add(weekArray[i]);
		
			panel1.add(weekArray[i]);	
	}
		weekArray[0].setForeground(Color.red);
		weekArray[6].setForeground(Color.blue);
//		add(panel1,BorderLayout.NORTH);
//		showDay = new JTextField[42];
//		for(int i=0;i<showDay.length;i++)
//		{
//		showDay[i]=new JTextField();
//		center.add(showDay[i]);
//		}
		
	}
	
	public void setShowDayTextField(JTextField [] text){ 
		showDay=text;
		for(int i=0;i<showDay.length;i++){
		showDay[i].setFont(new Font("TimesRoman",Font.BOLD,15));
		showDay[i].setHorizontalAlignment(JTextField.CENTER);
		showDay[i].setEditable(false);
		center.add(showDay[i]);
		showDay[i].setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));

		}
		}
	public void changeCalendar(int y,int m,int d) {
		String [] calStringsq = calendarArray();
		for(int i=0;i<42;i++)
		showDay[i].setText(calStringsq[i]);
	validate();
	}

	public String [] calendarArray() {
		String str[]=new String[42];
		Calendar cal=Calendar.getInstance();
		cal.set(year, month-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		int dayAmount=0;
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		dayAmount=31; 
		if(month==4||month==6||month==9||month==11)
		dayAmount=30;
		if(month==2) 
		if(((year%4==0)&&(year%100!=0))||year%400==0) 
		dayAmount=29;
		else  
		dayAmount=28;
		for(int i=0;i<week;i++) 
		str[i]=" "; 
		for(int i=week,n=1;i<week+dayAmount;i++){  
		str[i]=String.valueOf(n);  
		n++;     
		}
		for (int i=week+dayAmount;i<42;i++ ) 
		str[i]=" ";
		return str;
	}
		public void setYear(int year) {
			this.year=year;
			
		}
		public void setmonth(int month) {
			this.month=month;
		}

}
