package practice4;

	import javax.swing.*;


import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	import java.io.*;

	public class CalendarWindow extends JFrame implements ActionListener,
	MouseListener,FocusListener    //接口
	{
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	int year,month,day;
	CalendarMessage calendarMessage;
	CalendarPad calendarPad;
	NotePad notePad;
	JTextField showYear,showMonth;
	JTextField showDay[];
	CalendarImage calendarImage;
	String picturename;
	
	Clock clock;
	JButton alarm;
	JButton nextYear,previousYear,nextMonth,previousMonth;    
	JButton saveDailyRecord,deleteDailyRecord,readDailyRecord;   //增删改查日志
	File dir;
	Color backColor=Color.white;
	
	
	public CalendarWindow(String str)
	{super(str);
	setIconImage(Toolkit.getDefaultToolkit().getImage(CalendarWindow.class.getResource("电脑.png")));
	//设置图标
	dir= new File("./dailyRecord");
	dir.mkdir();
	showDay = new JTextField[42];
	
	for(int i=0;i<showDay.length;i++)
	{
	showDay[i]=new JTextField();
	showDay[i].setBackground(backColor);
	showDay[i].setLayout(new GridLayout(3,3));
	showDay[i].addMouseListener(this);
	showDay[i].addFocusListener(this);
	}
	
	calendarMessage = new CalendarMessage();
	calendarPad = new CalendarPad();
	notePad = new NotePad();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	
	year = calendar.get(Calendar.YEAR);
	month = calendar.get(Calendar.MONTH)+1;
	day = calendar.get(Calendar.DAY_OF_MONTH);
	calendarMessage.setYear(year);
	calendarMessage.setMonth(month);
	calendarMessage.setDay(day);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.setShowDayTextField(showDay);
	notePad.setMessage(year,month,day);
	calendarPad.showMonthCalendar();
	doMark();   //对有日志的日期做标记
	
	calendarImage = new CalendarImage();
	picturename=getPicture_address();        //下面的抽象方法
	calendarImage.setImageFile(new File(picturename));   //设置对象，并根据calendarimage创 //建image
	JMenuBar menuBar = new JMenuBar();   
	JMenu menusetting = new JMenu();
	JMenuItem changepicture=new JMenuItem();//菜单选项
	JMenuItem clearpicture = new JMenuItem();
	
	menusetting.setText("图像设置");//设置标题
	changepicture.setText("更改图片背景");//设置标题
	clearpicture.setText("去除背景");
	//添加菜单条上去；
	
	menusetting.add(changepicture);
	menusetting.add(clearpicture);
	menuBar.add(menusetting);
	setJMenuBar(menuBar);   //将JMenu 对象添加到菜单栏以构造菜单
	//注册监听器
	
	changepicture.addActionListener(this);
	clearpicture.addActionListener(this);
	clock = new Clock();
	clock.showUI();
	
	JSplitPane splitV1 = new  JSplitPane(JSplitPane.VERTICAL_SPLIT,calendarPad,calendarImage);
	//拆分窗格，就是被分为两部分的容器
	JSplitPane splitV2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,notePad,clock);
	JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitV1,splitV2);
	add(splitH,BorderLayout.CENTER);
	
	showYear = new JTextField(""+year,6);
	showYear.setFont(new Font("TimesRoman",Font.BOLD,12));
	showYear.setHorizontalAlignment(JTextField.CENTER);
	showMonth = new JTextField(""+month,4);
	showMonth.setFont(new Font("TimesRoman",Font.BOLD,12));
	showMonth.setHorizontalAlignment(JTextField.CENTER);
	nextYear = new JButton("下年");
	previousYear = new JButton("上年");
	nextMonth = new JButton("下月 ");
	previousMonth = new JButton("上月 ");
	alarm = new JButton("设置闹钟 ");

	nextYear.addActionListener(this);
	previousYear.addActionListener(this);
	nextMonth.addActionListener(this);
	previousMonth.addActionListener(this);
	showYear.addActionListener(this);
	showMonth.addActionListener(this);
	alarm.addActionListener(this);

	
	JPanel north = new JPanel();
	north.add(previousYear);
	north.add(showYear);
	north.add(nextYear);
	north.add(previousMonth);
	north.add(showMonth);
	north.add(nextMonth);
	north.add(alarm);

	add(north,BorderLayout.NORTH);
	
	saveDailyRecord = new JButton("保存日志");
	deleteDailyRecord = new JButton("删除日 志");
	readDailyRecord = new JButton("读取日志");
	saveDailyRecord.addActionListener(this);
	deleteDailyRecord.addActionListener(this);
	readDailyRecord.addActionListener(this);
	
	JPanel pSouth = new JPanel();
	pSouth.add(saveDailyRecord);
	pSouth.add(deleteDailyRecord);
	pSouth.add(readDailyRecord);
	add(pSouth,BorderLayout.SOUTH);
	
	setVisible(true);
	setBounds(70,70,750,680);
	validate();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public String getPicture_address()
	{
	FileInputStream outOne = null;
	String address=null;
	
	try
	{
	outOne =new FileInputStream("picture_address.txt");
	ObjectInputStream outTwo=new ObjectInputStream(outOne);   //可以照抄的
	try{address=(String)outTwo.readObject();}
	catch(Exception ex){}
	outTwo.close();
	}
	catch(IOException eee)
	{
	}
	
	if(address!=null)
	{
	return address;
	}
	
	else
	{
	return "picture.jpg";
	}
	
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
	
		if(e.getActionCommand().equals("更改图片背景"))
	{
	FileDialog dia=new FileDialog(this,"选定图片",FileDialog.LOAD);//新建一个文件选择对
	//话框， 用 dia 获得选择得到的信息；
	dia.setModal(true);       //设置为模态对话框；
	dia.setVisible(true);
	//将取得的内容存入“picture_address.txt”文件中， 下次打开软件时还能记住上次的图片路径的选择；
	
	if((dia.getDirectory()!=null)&&(dia.getFile()!=null))//如果得到的路径和文件名不为空；
	{
		try
	{
	FileOutputStream inOne=new FileOutputStream("picture_address.txt");  //只要能把这里删了就可以清空背景！！！
	ObjectOutputStream inTwo=new ObjectOutputStream(inOne);
	inTwo.writeObject(dia.getDirectory()+dia.getFile());   //将对象写入流中  //获取此文件对话框的目录    //用于获取文件  //先调用getFile()后才能使用getPara()系列方法获取参数
	inTwo.close();
	}
	
		catch(IOException ee)
	{
	}
	picturename=getPicture_address();//从文件中读出显示图片的路径地址；
	calendarImage.setImageFile(new File(picturename));//将获得路径传给图片， 显示该路径指
	//定的图片
	System.out.println("111");
	}
	}
	
	if (e.getActionCommand().equals("去除背景")) {
System.out.println("222");	
		File f = new File("D:\\java\\SchoolPractice\\picture_address.txt"); // 输入要删除的文件位置
		if(f.exists())
		f.delete();
		picturename=getPicture_address();
		calendarImage.setImageFile(new File(picturename));
	}
	
	if(e.getSource()==nextYear)
	{
	year++;
	showYear.setText(""+year);
	calendarMessage.setYear(year);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	doMark();
	}
	
	else if (e.getSource()==alarm)
	{
	clock.AClock();
	}
	
	else if (e.getSource()==previousYear)
	{
	year--;
	showYear.setText(""+year);
	calendarMessage.setYear(year);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	doMark();
	}
	
	else if (e.getSource()==nextMonth)
	{
	month++;
	if(month>12) month=1;
	showMonth.setText(""+month);
	calendarMessage.setMonth(month);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	doMark();
	}
	
	else if (e.getSource()==previousMonth)
	{
	month--;
	if(month<1) month=12;
	showMonth.setText(""+month);
	calendarMessage.setMonth(month);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	
	doMark();
	}
	
	else if (e.getSource()==showYear)
	{
	String s = showYear.getText().trim();
	char a[] = s.toCharArray();
	boolean boo = false;
	
	for(int i = 0;i < a.length;i++)
	{
		
	if(!(Character.isDigit(a[i])))  //用于检查是不是十进制数字字符
	boo = true;
	}
	
	if(boo==true)
	JOptionPane.showMessageDialog(this,"你输入了非法年份","警告！ ",
	JOptionPane.WARNING_MESSAGE);
	
	else if(boo==false)
	year = Integer.parseInt(s);
	showYear.setText(""+year);
	calendarMessage.setYear(year);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	doMark();
	}
	
	else if (e.getSource()==showMonth)
	{
	String s = showMonth.getText().trim();
	char a[] = s.toCharArray();
	boolean boo = false;
	
	for(int i = 0;i < a.length;i++)
	{
	
		if(!(Character.isDigit(a[i])))  //用于检查是不是十进制数字字符
	boo = true;
	}
	
	if(boo==true)
	JOptionPane.showMessageDialog(this,"你输入了非法月份","警告！ ",
	JOptionPane.WARNING_MESSAGE);
	
	else if(boo==false)
	month = Integer.parseInt(s);
	showMonth.setText(""+month);
	calendarMessage.setMonth(month);
	calendarPad.setCalendarMessage(calendarMessage);
	calendarPad.showMonthCalendar();
	notePad.setMessage(year,month,day);
	doMark();
	}
	
	else if (e.getSource()== saveDailyRecord)
	{
	notePad.savefile(dir,year,month,day);
	doMark();
	}
	
	else if(e.getSource()==deleteDailyRecord)
	{
	notePad.deletefile(dir,year,month,day);
	doMark();
	}
	
	else if (e.getSource()==readDailyRecord)
	{
	notePad.readfile(dir,year,month,day);
	doMark();
	}
	}

	
	public void mousePressed(MouseEvent e)
	{
	JTextField text = (JTextField)e.getSource();
	String str = text.getText().trim();
	
	try
	{
	day = Integer.parseInt(str);
	}
	
	catch (NumberFormatException exp)
	{}
	calendarMessage.setDay(day);
	notePad.setMessage(year,month,day);
	}
	
	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	
	
	public void focusGained(FocusEvent e)
	{
	Component com = (Component)e.getSource();
	com.setBackground(Color.pink);
	}
	
	
	public void focusLost(FocusEvent e)
	{
	Component com = (Component)e.getSource();
	com.setBackground(backColor);
	}
	
	
	public void doMark()
	{
	
		for(int i=0;i<showDay.length;i++)
	{
	showDay[i].removeAll();  //除当前列表中所有与传入Collection有交集的元素
	String str = showDay[i].getText().trim();   //返回数据窗口控件中悬浮bai在当前行列之上的编辑框中的文本  //删除字符串开始和末尾的空格
	
	try
	{
	int n = Integer.parseInt(str);
	
	if(isHaveDailyRecord(n)==true)
	{
	JLabel mess = new JLabel("yes");
	mess.setFont(new Font("TimesRoman",Font.PLAIN,11));
	mess.setForeground(Color.blue);
	showDay[i].add(mess);
	}
	}
	catch (Exception exp){}
	}
	calendarPad.repaint();
	calendarPad.validate();
	}
	
	
	public boolean isHaveDailyRecord(int n)
	{
	String key = ""+year+""+month+""+n;
	String []dayFile = dir.list();
	boolean boo = false;
	
	for(int k = 0;k<dayFile.length;k++)
	{
	
		if(dayFile[k].equals(key+".txt"))
	{
	boo = true;
	break;
	}
	}
	return boo;
	}
	
	
	public static void main(String args[])
	{
	new CalendarWindow("j日历记事本j");

	}
	}

