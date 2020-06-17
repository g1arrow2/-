package practice4;
import javax.swing.*;    //日历可视化界面
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
public class CalendarPad extends JPanel{   //容器类
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int year,month,day;
		CalendarMessage calendarMessage;    //上面代码的主体类
		JTextField []showDay;     //单行文本，设计出数组形式，懂的都懂
		
		JLabel title [];    //标签组
		String [] 星期={"SUN 日 ","MON 一","TUE 二","WED 三","THU 四","FRI 五","SAT 六"};  //初始化
		JPanel north,center;   //容器名
		
		
		public CalendarPad(){  //构造器
		setLayout(new BorderLayout());  
		north=new JPanel();
		north.setLayout(new GridLayout(1,7));  //最上面一行
		center=new JPanel();
		center.setLayout(new GridLayout(6,7));  //日期
		add(center,BorderLayout.CENTER);     
		add(north,BorderLayout.NORTH);
		title=new JLabel[7];   //数组
		
		for(int j=0;j<7;j++){   
		title[j]=new JLabel();
		title[j].setFont(new Font("TimesRoman",Font.BOLD,12));
		title[j].setText(星期[j]);
		title[j].setHorizontalAlignment(JLabel.CENTER);
		title[j].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.RED)); //创建一个具有凸出斜面边缘的边框，将组件当前背景色的较亮的色度用于高亮显示，较暗的色度用于阴影。（在凸出边框中，高亮显示位于顶部，阴影位于其下。）
		
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
		String [] a=calendarMessage.getMonthCalendar();   //调用上面程序的方法，最终返回上面代码的值
		
		for(int i=0;i<42;i++)
		showDay[i].setText(a[i]);
		validate();  //验证  验证方式是什么？？？
		}  //总结代码需结合最上面的
		}
		
