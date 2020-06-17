package practice4;

	import javax.swing.*;


import java.awt.*;
	import java.awt.event.*;
	import java.util.*;
	import java.io.*;

	public class CalendarWindow extends JFrame implements ActionListener,
	MouseListener,FocusListener    //�ӿ�
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
	JButton saveDailyRecord,deleteDailyRecord,readDailyRecord;   //��ɾ�Ĳ���־
	File dir;
	Color backColor=Color.white;
	
	
	public CalendarWindow(String str)
	{super(str);
	setIconImage(Toolkit.getDefaultToolkit().getImage(CalendarWindow.class.getResource("����.png")));
	//����ͼ��
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
	doMark();   //������־�����������
	
	calendarImage = new CalendarImage();
	picturename=getPicture_address();        //����ĳ��󷽷�
	calendarImage.setImageFile(new File(picturename));   //���ö��󣬲�����calendarimage�� //��image
	JMenuBar menuBar = new JMenuBar();   
	JMenu menusetting = new JMenu();
	JMenuItem changepicture=new JMenuItem();//�˵�ѡ��
	JMenuItem clearpicture = new JMenuItem();
	
	menusetting.setText("ͼ������");//���ñ���
	changepicture.setText("����ͼƬ����");//���ñ���
	clearpicture.setText("ȥ������");
	//��Ӳ˵�����ȥ��
	
	menusetting.add(changepicture);
	menusetting.add(clearpicture);
	menuBar.add(menusetting);
	setJMenuBar(menuBar);   //��JMenu ������ӵ��˵����Թ���˵�
	//ע�������
	
	changepicture.addActionListener(this);
	clearpicture.addActionListener(this);
	clock = new Clock();
	clock.showUI();
	
	JSplitPane splitV1 = new  JSplitPane(JSplitPane.VERTICAL_SPLIT,calendarPad,calendarImage);
	//��ִ��񣬾��Ǳ���Ϊ�����ֵ�����
	JSplitPane splitV2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,notePad,clock);
	JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitV1,splitV2);
	add(splitH,BorderLayout.CENTER);
	
	showYear = new JTextField(""+year,6);
	showYear.setFont(new Font("TimesRoman",Font.BOLD,12));
	showYear.setHorizontalAlignment(JTextField.CENTER);
	showMonth = new JTextField(""+month,4);
	showMonth.setFont(new Font("TimesRoman",Font.BOLD,12));
	showMonth.setHorizontalAlignment(JTextField.CENTER);
	nextYear = new JButton("����");
	previousYear = new JButton("����");
	nextMonth = new JButton("���� ");
	previousMonth = new JButton("���� ");
	alarm = new JButton("�������� ");

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
	
	saveDailyRecord = new JButton("������־");
	deleteDailyRecord = new JButton("ɾ���� ־");
	readDailyRecord = new JButton("��ȡ��־");
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
	ObjectInputStream outTwo=new ObjectInputStream(outOne);   //�����ճ���
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
	
		if(e.getActionCommand().equals("����ͼƬ����"))
	{
	FileDialog dia=new FileDialog(this,"ѡ��ͼƬ",FileDialog.LOAD);//�½�һ���ļ�ѡ���
	//���� �� dia ���ѡ��õ�����Ϣ��
	dia.setModal(true);       //����Ϊģ̬�Ի���
	dia.setVisible(true);
	//��ȡ�õ����ݴ��롰picture_address.txt���ļ��У� �´δ����ʱ���ܼ�ס�ϴε�ͼƬ·����ѡ��
	
	if((dia.getDirectory()!=null)&&(dia.getFile()!=null))//����õ���·�����ļ�����Ϊ�գ�
	{
		try
	{
	FileOutputStream inOne=new FileOutputStream("picture_address.txt");  //ֻҪ�ܰ�����ɾ�˾Ϳ�����ձ���������
	ObjectOutputStream inTwo=new ObjectOutputStream(inOne);
	inTwo.writeObject(dia.getDirectory()+dia.getFile());   //������д������  //��ȡ���ļ��Ի����Ŀ¼    //���ڻ�ȡ�ļ�  //�ȵ���getFile()�����ʹ��getPara()ϵ�з�����ȡ����
	inTwo.close();
	}
	
		catch(IOException ee)
	{
	}
	picturename=getPicture_address();//���ļ��ж�����ʾͼƬ��·����ַ��
	calendarImage.setImageFile(new File(picturename));//�����·������ͼƬ�� ��ʾ��·��ָ
	//����ͼƬ
	System.out.println("111");
	}
	}
	
	if (e.getActionCommand().equals("ȥ������")) {
System.out.println("222");	
		File f = new File("D:\\java\\SchoolPractice\\picture_address.txt"); // ����Ҫɾ�����ļ�λ��
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
		
	if(!(Character.isDigit(a[i])))  //���ڼ���ǲ���ʮ���������ַ�
	boo = true;
	}
	
	if(boo==true)
	JOptionPane.showMessageDialog(this,"�������˷Ƿ����","���棡 ",
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
	
		if(!(Character.isDigit(a[i])))  //���ڼ���ǲ���ʮ���������ַ�
	boo = true;
	}
	
	if(boo==true)
	JOptionPane.showMessageDialog(this,"�������˷Ƿ��·�","���棡 ",
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
	showDay[i].removeAll();  //����ǰ�б��������봫��Collection�н�����Ԫ��
	String str = showDay[i].getText().trim();   //�������ݴ��ڿؼ�������bai�ڵ�ǰ����֮�ϵı༭���е��ı�  //ɾ���ַ�����ʼ��ĩβ�Ŀո�
	
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
	new CalendarWindow("j�������±�j");

	}
	}

