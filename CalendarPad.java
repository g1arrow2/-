package practice4;
import javax.swing.*;    //�������ӻ�����
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
public class CalendarPad extends JPanel{   //������
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int year,month,day;
		CalendarMessage calendarMessage;    //��������������
		JTextField []showDay;     //�����ı�����Ƴ�������ʽ�����Ķ���
		
		JLabel title [];    //��ǩ��
		String [] ����={"SUN �� ","MON һ","TUE ��","WED ��","THU ��","FRI ��","SAT ��"};  //��ʼ��
		JPanel north,center;   //������
		
		
		public CalendarPad(){  //������
		setLayout(new BorderLayout());  
		north=new JPanel();
		north.setLayout(new GridLayout(1,7));  //������һ��
		center=new JPanel();
		center.setLayout(new GridLayout(6,7));  //����
		add(center,BorderLayout.CENTER);     
		add(north,BorderLayout.NORTH);
		title=new JLabel[7];   //����
		
		for(int j=0;j<7;j++){   
		title[j]=new JLabel();
		title[j].setFont(new Font("TimesRoman",Font.BOLD,12));
		title[j].setText(����[j]);
		title[j].setHorizontalAlignment(JLabel.CENTER);
		title[j].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.RED)); //����һ������͹��б���Ե�ı߿򣬽������ǰ����ɫ�Ľ�����ɫ�����ڸ�����ʾ���ϰ���ɫ��������Ӱ������͹���߿��У�������ʾλ�ڶ�������Ӱλ�����¡���
		
		north.add(title[j]);
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
//		Border etchedBorder;
//		etchedBorder = BorderFactory.createEtchedBorder();
//		Border b1 = BorderFactory.createMatteBorder(1, 0, 0, 1, Color.green);
//		TitledBorder b2;
//        b2=BorderFactory.createTitledBorder(etchedBorder,"", TitledBorder.CENTER, TitledBorder.BOTTOM);
//        
//        showDay[i].setBorder(BorderFactory.createCompoundBorder(b1, b2));
		showDay[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.cyan));
		center.add(showDay[i]);
		}
		}
		
		
		public void setCalendarMessage(CalendarMessage calendarMessage){
		this.calendarMessage=calendarMessage;
		}
		
		
		public void showMonthCalendar(){
		String [] a=calendarMessage.getMonthCalendar();   //�����������ķ��������շ�����������ֵ
		
		for(int i=0;i<42;i++)
		showDay[i].setText(a[i]);
		validate();  //��֤  ��֤��ʽ��ʲô������
		}  //�ܽ���������������
		}
		
