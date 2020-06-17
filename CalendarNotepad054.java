package practice5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;


public class CalendarNotepad054 extends  JFrame implements ActionListener,MouseListener,FocusListener {
	int year,month,day;
	Calendar1 calendar1; 
	CalendarPad calendarPad;
	JTextField []showDay; 
	CanvasPanel CanvasPanel;
	String picturename;
	CalendarPanel2 calendarPanel2;
	
	private static final long serialVersionUID=1L;
	@SuppressWarnings("unused")
	private JTextField tf;
	Color backColor=Color.white;
	JTextField showYear,showMonth;
	JButton nextYear,previousYear,nextMonth,previousMonth;   
	JButton saveDailyRecord,deleteDailyRecord,readDailyRecord;
	JButton changeImage;
	public CalendarNotepad054() {
		super("j54j");

		showDay = new JTextField[42];
		for(int i=0;i<showDay.length;i++)
		{
		showDay[i]=new JTextField();
		showDay[i].setBackground(backColor);
		showDay[i].setLayout(new GridLayout(3,3));
		showDay[i].addMouseListener(this);
		showDay[i].addFocusListener(this);
		}
//		calendarPad=new CalendarPad();
//		calendar1=new Calendar1();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		JMenuItem changepicture=new JMenuItem();
		JMenuItem clearpicture = new JMenuItem();
		
		
		
		changepicture.addActionListener(this);
		clearpicture.addActionListener(this);
	
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
//		calendar1.setYear(year);
//		calendar1.setMonth(month);
//		calendar1.setDay(day);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.setShowDayTextField(showDay);
//		calendarPad.showMonthCalendar(); 
		calendarPanel2=new CalendarPanel2(year,month,day);
		calendarPanel2.setShowDayTextField(showDay);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		
		nextYear = new JButton("下一年");
		previousYear = new JButton("上一年");
		nextMonth = new JButton("下一月 ");
		previousMonth = new JButton("上一月 ");
		showYear = new JTextField(""+year,6);
		showYear.setFont(new Font("TimesRoman",Font.BOLD,12));
		showYear.setHorizontalAlignment(JTextField.CENTER);
		showMonth = new JTextField(""+month,4);
		showMonth.setFont(new Font("TimesRoman",Font.BOLD,12));
		showMonth.setHorizontalAlignment(JTextField.CENTER);
		nextYear.addActionListener(this);
		previousYear.addActionListener(this);
		nextMonth.addActionListener(this);
		previousMonth.addActionListener(this);
		showYear.addActionListener(this);
		showMonth.addActionListener(this);

		
		saveDailyRecord = new JButton("保存日志");
		deleteDailyRecord = new JButton("删除日志");
		readDailyRecord = new JButton("读取日志");
		changeImage=new JButton("更换图片");
		changeImage.addActionListener(this);
		
		JPanel north = new JPanel();
		north.add(previousYear);
		north.add(showYear);
		north.add(nextYear);
		north.add(previousMonth);
		north.add(showMonth);
		north.add(nextMonth);
		north.add(changeImage);
		add(north,BorderLayout.NORTH);
			
			JPanel pSouth = new JPanel();
			pSouth.add(saveDailyRecord);
			pSouth.add(deleteDailyRecord);
			pSouth.add(readDailyRecord);
			add(pSouth,BorderLayout.SOUTH);

		URL url=getClass().getResource("12.png");
		Image img=Toolkit.getDefaultToolkit().getImage(url);
		CanvasPanel=new CanvasPanel(img);
//		CanvasPanel=
			
		JSplitPane sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,calendarPanel2,CanvasPanel);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JButton("右上方"),new JButton("右下方"));
//		splitPane.setDividerLocation(100);
		JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,sp,splitPane);
		add(splitH,BorderLayout.CENTER);
		
		setBounds(200,200,700,600);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==nextYear){	
		year++;
		showYear.setText(""+year);
//		calendar1.setYear(year);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setYear(year);
		calendarPanel2.changeCalendar(year,month,day);

		}
		
		else if(e.getSource()==previousYear){
		year--;
		showYear.setText(""+year);
//		calendar1.setYear(year);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setYear(year);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		}
		
		else if (e.getSource()==nextMonth){
		month++;
		if(month>12) month=1;
		showMonth.setText(""+month);
//		calendar1.setMonth(month);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setmonth(month);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		}
		
		else if (e.getSource()==previousMonth){
		month--;
		if(month<1) month=12;
		showMonth.setText(""+month);
//		calendar1.setMonth(month);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setmonth(month);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		}
		
		else if (e.getSource()==showYear){
		String s = showYear.getText().trim();
		char a[] = s.toCharArray();
		boolean boo = false;
		
		for(int i = 0;i < a.length;i++){
			
		if(!(Character.isDigit(a[i])))  
		boo = true;
		}
		
		if(boo==true)
		JOptionPane.showMessageDialog(this,"输入错误","请重试 ",JOptionPane.WARNING_MESSAGE);
		
		else if(boo==false)
		year = Integer.parseInt(s);
		showYear.setText(""+year);
//		calendar1.setYear(year);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setYear(year);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		}
		
		else if (e.getSource()==showMonth)
		{
		String s = showMonth.getText().trim();
		char a[] = s.toCharArray();
		boolean boo = false;
		
		for(int i = 0;i < a.length;i++)
		{
		
			if(!(Character.isDigit(a[i]))) 
		boo = true;
		}
		
		if(boo==true)
		JOptionPane.showMessageDialog(this,"输入错误","请重试 ",JOptionPane.WARNING_MESSAGE);
		
		else if(boo==false)
		month = Integer.parseInt(s);
		showMonth.setText(""+month);
//		calendar1.setMonth(month);
//		calendarPad.setCalendarMessage(calendar1);
//		calendarPad.showMonthCalendar();
		calendarPanel2.setmonth(month);
//		calendarPanel2.showCalendar();
		calendarPanel2.changeCalendar(year,month,day);
		}else if(e.getSource()==changeImage) {
		FileDialog fileDialog=new FileDialog(this,"请选择图片",FileDialog.LOAD);
		fileDialog.setModal(true);
		fileDialog.setVisible(true);
		String path=fileDialog.getDirectory()+fileDialog.getFile();
		Image img2=getToolkit().getImage(path);
		CanvasPanel.changeImage(img2);
		}
	}
	public void focusGained(FocusEvent e)
	{
	Component com = (Component)e.getSource();
	com.setBackground(Color.pink);
	}
	
	
	public void focusLost(FocusEvent e)
	{
	Component com = (Component)e.getSource();
	com.setBackground(Color.white);
	}
		
	public static void main(String[] args) {
		new CalendarNotepad054();
}
	
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}
		}
		


	


	
