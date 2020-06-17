package practice4;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.*;

import java.util.*;


public class Clock extends JComponent{    

    private static final long serialVersionUID = -5379472973578609775L;
    private Font f = new Font("微软雅黑",Font.PLAIN,15);
    private Font f2 = new Font("微软雅黑",Font.BOLD,15);
    private JLabel l = new JLabel("当前时间：");
    private JLabel display = new JLabel();
    private JLabel display2 = new JLabel("");
    private int hour = 0;     private int min = 0;
    private int sec = 0;
    private Date now = new Date();
    private Graphics2D g;
    final double PI = Math.PI;
   	static int sethour; 
   	static int setminute; 
   	static int setsecond; 
    private String strTime = "" ;
         @SuppressWarnings("deprecation")
     
         
         public Clock(){        
        add(l);
         l.setBounds(120, 320, 80, 20);
         l.setFont(f);
        add(display);
         display.setBounds(195, 320, 80, 20);
         display.setFont(f);
         display.setBorder(BorderFactory.createLineBorder(Color.black));
         add(display2);
         display2.setBounds(90, 350, 250, 20);
         display2.setFont(f);
         hour = now.getHours();
        min = now.getMinutes();
         sec = now.getSeconds();
         setVisible(true);
     }
 
     
         public void paintComponent(Graphics g1){
         double x,y;
         super.paintComponent(g1);
         g = (Graphics2D) g1;
         //反锯齿开关开        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
         //画表盘
        g.setPaint(new GradientPaint(5,40,Color.blue,15,50,Color.yellow,true));
        g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
        g.drawOval(75, 40, 250, 250);
        g.fillOval(195, 160, 10, 10);
        g.setColor(Color.black);
                 //画60个点
     
        for(int i = 0;i < 60;i++)
         {
             double[] co = new double[2];
            co = paint_Dot(i * 2 * PI / 60);
            x = co[0];
            y = co[1];
            if(i == 0 || i == 15 || i == 30 || i == 45)//画3,6,9,12四个大点
             {
                g.fillOval((int)(x - 5 + 200),(int)(y - 5 + 165),10,10);
            }
            else//其他小点
            {
                 g.fillOval((int)(x - 2.5 + 200),(int)(y - 2.5 + 165),5,5);
             }
         }
         
         //画四个数字
         g.setFont(f2);
         g.drawString("3", 300, 171);
         g.drawString("6", 195, 273);
         g.drawString("9", 91, 171);
         g.drawString("12", 190, 68);
         
         //画时针，分针，秒针
         paint_HourPointer(hour*3600 + min*60 + sec,g);//时针走过的秒数
         paint_MinutePointer(min*60 + sec,g);//分针走过的秒数
         paint_SecondPointer(sec,g);//秒针走过的秒数
     }

         
     public void showUI(){
         new Thread() {
           
        	 
        	 @SuppressWarnings("deprecation")
            public void run() {
            	 while (true) 
                 {
                     now = new Date();
                     hour = now.getHours();
                    min = now.getMinutes();
                    sec = now.getSeconds();
                    try {
                        Thread.sleep(1000);
                        if(sethour == hour && setminute == min && setsecond == sec){ 
                  		  System.out.println("进入闹着玩的闹钟");
                  		Play0 play= new Play0();
                  		play.playMusic();
                  	
                     }} catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                     showTime();                     repaint();
               }
             }
 }.start();
     }
     
     
   public void AClock() {
 	sethour = Integer.parseInt(JOptionPane.showInputDialog("请输入小时：")); 
 	setminute = Integer.parseInt(JOptionPane.showInputDialog("请输入分钟：")); 
 	setsecond = Integer.parseInt(JOptionPane.showInputDialog("请输入秒："));
 }
   
 
     public void paint_HourPointer(int second,Graphics2D g){//second表示当前时间的时针相对00:00:00走了多少秒
         double x,y,angle; 
         angle = second * PI / 21600;//时针的速度为PI/21600 (rad/s)
        x = 200 + 60 * Math.sin(angle);
         y = 165 - 60 * Math.cos(angle);
         g.setStroke( new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
         g.setPaint(new GradientPaint(200,165,Color.red,260,165,Color.blue,true));
         g.drawLine(200, 165, (int)x, (int)y);
    }
     
     
     public void paint_MinutePointer(int second,Graphics2D g){//second表示当前时间的分针相对00:00:00走了多少秒
        double x,y,angle;
        angle = second * PI / 1800;//分针的速度为PI/1800 (rad/s)
      x = 200 + 80 * Math.sin(angle);
         y = 165 - 80 * Math.cos(angle);
         g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
       g.setPaint(new GradientPaint(200,165,Color.magenta,280,165,Color.blue,true));
         g.drawLine(200, 165, (int)x, (int)y);
     } 
     
     public void paint_SecondPointer(int second,Graphics2D g){//second表示当前时间的秒针相对00:00:00走了多少秒
    	 double x,y,x1,y1,x2,y2,x3,y3,angle;
    	 double cos = 90 / Math.sqrt(8125);//90*90+5*5
        double sin = 5 / Math.sqrt(8125);
        angle = second * PI / 30;//时针的速度为PI/30 (rad/s)
         x = 200 + 95 * Math.sin(angle);
        y = 165 - 95 * Math.cos(angle);
         x1 = 200 + 20 * Math.sin(angle + PI);
        y1 = 165 - 20 * Math.cos(angle + PI);
        x2 = 200 + Math.sqrt(8125)* ( Math.sin(angle)*cos - Math.cos(angle)*sin ); //sin(a-b)
         y2 = 165 - Math.sqrt(8125)* ( Math.cos(angle)*cos + Math.sin(angle)*sin ); //cos(a-b)
         x3 = 200 + Math.sqrt(8125)* ( Math.sin(angle)*cos + Math.cos(angle)*sin ); //sin(a+b)
         y3 = 165 - Math.sqrt(8125)* ( Math.cos(angle)*cos - Math.sin(angle)*sin ); //cos(a+b)
        g.setStroke( new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
         g.setPaint(new GradientPaint(180,165,Color.CYAN,295,165,Color.MAGENTA,true));
         g.drawLine((int)x1, (int)y1, (int)x, (int)y);
         g.drawLine((int)x2, (int)y2, (int)x, (int)y);
        g.drawLine((int)x3, (int)y3, (int)x, (int)y);
     }

     
    public double[] paint_Dot(double angle){
         double[] co = new double[2];
         co[0] = 115 * Math.cos(angle);//横坐标
         co[1] = 115 * Math.sin(angle);//纵坐标
         return co;
     }

    
     @SuppressWarnings("deprecation")
     private void showTime(){
         String date;
         int hour_temp = hour,min_temp = min,sec_temp = sec;
         sec_temp += 1 ;
        
         if(sec_temp >= 60)
         {
             sec_temp = 0;
             min_temp += 1 ;
         }
        
         if(min_temp>=60){
             min_temp=0;
             hour_temp+=1;
         }
     
         if(hour_temp < 10)
             strTime = "0" + hour_temp + ":";
         else
             strTime = "" + hour_temp + ":";
          
         if(min_temp < 10)
             strTime = strTime + "0" + min_temp + ":";
         else
             strTime = strTime + "" + min_temp + ":";
          
        if(sec < 10)
            strTime = strTime + "0" + sec_temp;
        else
             strTime = strTime + "" + sec_temp;
         //在窗体上设置显示时间
         date = " " + (now.getYear()+1900) + "年" + (now.getMonth()+1) + "月" + now.getDate() + "日   " + "星期" ;
        
         switch (now.getDay()) {
         case 1:
             date += "一";
             break;
         case 2:
             date += "二";
             break;
        case 3:
             date += "三";
            break;
        case 4:
             date += "四";
            break;
         case 5:
             date += "五";
             break;
         case 6:
             date += "六";
             break;
         case 7:
             date += "日";
             break;
        }
         date += "  CST";
         strTime = "  " + strTime; 
         display.setText(strTime);
         display2.setText(date);
     }
 
 }

//==================================================================
//public class Clock extends JPanel implements ActionListener{
///**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//Date date;
//javax.swing.Timer secondTime;   //创建并启动一个每秒激发一次操作事件的计时器
//int hour,minute,second;
//Line2D secondLine,minuteLine,hourLine;
//int a,b,c,width,height;
//static int sethour; 
//static int setminute; 
//static int setsecond; 
//
//double [] pointSX=new double[60], //用来表示秒针端点坐标的数组
//pointSY=new double[60],
//pointMX=new double[60], //用来表示分针端点坐标的数组
//pointMY=new double[60],
//pointHX=new double[60], //用来表示时针端点坐标的数组
//pointHY=new double[60];
//Clock(){
//setBackground(Color.cyan);
//initPoint();
//secondTime=new javax.swing.Timer(1000,this);   //延迟1000ms
//secondLine=new Line2D.Double(0,0,0,0);   //起始结束 起始结束
//minuteLine=new Line2D.Double(0,0,0,0);
//hourLine=new Line2D.Double(0,0,0,0);
//secondTime.start(); //秒针开始计时
//
//}
//private void initPoint(){
//width=getBounds().width;
//height=getBounds().height;
//pointSX[0]=0; //12 点秒针位置
//pointSY[0]=-height/2*5/6;
//pointMX[0]=0; //12 点分针位置
//pointMY[0]=-(height/2*4/5);
//pointHX[0]=0; //12 点时针位置
//pointHY[0]=-(height/2*2/3);
//double angle=6*Math.PI/180; //刻度为 6 度
//for(int i=0;i<59;i++) { //计算数组中的坐标
//pointSX[i+1]=pointSX[i]*Math.cos(angle)-Math.sin(angle)*pointSY[i];
//pointSY[i+1]=pointSY[i]*Math.cos(angle)+pointSX[i]*Math.sin(angle);
//pointMX[i+1]=pointMX[i]*Math.cos(angle)-Math.sin(angle)*pointMY[i];
//pointMY[i+1]=pointMY[i]*Math.cos(angle)+pointMX[i]*Math.sin(angle);
//pointHX[i+1]=pointHX[i]*Math.cos(angle)-Math.sin(angle)*pointHY[i];
//pointHY[i+1]=pointHY[i]*Math.cos(angle)+pointHX[i]*Math.sin(angle);
//}     //不要死记懂的都懂    
//for(int i=0;i<60;i++){     //这里还要再理解一下  两个for循环结合为什么？
//pointSX[i]=pointSX[i]+width/2; //坐标平移
//pointSY[i]=pointSY[i]+height/2;
//pointMX[i]=pointMX[i]+width/2; //坐标平移
//pointMY[i]=pointMY[i]+height/2;
//pointHX[i]=pointHX[i]+width/2; //坐标平移
//pointHY[i]=pointHY[i]+height/2;
//}
//}
//@SuppressWarnings("deprecation")
//public void paintComponent(Graphics g){
//super.paintComponent(g);
//initPoint();
//for(int i=0;i<60;i++){ //绘制表盘上的小刻度和大刻度
//int m=(int)pointSX[i];
//int n=(int)pointSY[i];
//if(i%5==0){
//if(i==0||i==15||i==30||i==45){
//int k=10;
//g.setColor(Color.orange);
//g.fillOval(m-k/2,n-k/2,k,k);   //椭圆坐标宽度
//}
//else{
//int k=7;
//g.setColor(Color.orange);
//g.fillOval(m-k/2,n-k/2,k,k);
//}
//}
//else{    //如果除5不余0
//int k=2;
//g.setColor(Color.black);
//g.fillOval(m-k/2,n-k/2,k,k);
//}
//}
//g.fillOval(width/2-5,height/2-5,10,10); //钟表中心的实心圆
//Graphics2D g_2d=(Graphics2D)g;
//g_2d.setColor(Color.red);
//g_2d.draw(secondLine);
//BasicStroke bs=      //画虚线
//new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
//g_2d.setStroke(bs);
//g_2d.setColor(Color.blue);
//g_2d.draw(minuteLine);
//bs=new BasicStroke(4f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);  
////4f 宽度
//// BasicStroke.CAP_BUTT,结束未闭合的子路径和虚线段，没有添加装饰。
//// JOIN_MITER通过扩展它们的外边缘直到它们相遇来连接路径段
//g_2d.setStroke(bs);
//g_2d.setColor(Color.orange);
//g_2d.draw(hourLine);
//if((minute==59)&&(second==50))
//{
////播放指定音频；
//try {
//File f = new File("BGM.wav");//音频文件；自己加
//URI uri=f.toURI();
//URL url=uri.toURL();
//AudioClip aau;
////// AudioClip是一个声音片段，如：按钮点击，相当于是一首音乐；AudioSource是一个源，包含AudioClip，还有其它一些属性，如：是否静音等，相当于是一个音乐播放器
//aau = Applet.newAudioClip(url); //创建 AudioClip 对象；   //如果不是因为计算机二级或是某些该死的考试中需要出题，，我想我是不会理会这中东西的，毕竟这货淘汰了，为啥使用？为了考试。 已经淘汰的东西  都不知道能不能用。
//aau.play();//播放音频；
//} catch (MalformedURLException e) {
//e.printStackTrace();
//}
//}
//}
//public void AClock() {
//	sethour = Integer.parseInt(JOptionPane.showInputDialog("请输入小时：")); 
//	setminute = Integer.parseInt(JOptionPane.showInputDialog("请输入分钟：")); 
//	setsecond = Integer.parseInt(JOptionPane.showInputDialog("请输入秒："));
//	Alarm();
//}
//public void Alarm() {
//	GregorianCalendar calendar; 
//	calendar = new GregorianCalendar();
//	
//	System.out.println("进来了");
//
//	hour = calendar.get(Calendar.HOUR); 
//	minute = calendar.get(Calendar.MINUTE); 
//	second = calendar.get(Calendar.SECOND); 
//	 if(sethour == hour || setminute == minute || setsecond == second){ 
//		  System.out.println("进入闹着玩的闹钟");
//		Play0 play= new Play0();
//		play.playMusic();
//
//		   }
//}
//public void actionPerformed(ActionEvent e){
//if(e.getSource()==secondTime){
//date=new Date();
//String s=date.toString();    //转换成字符串输出
//hour=Integer.parseInt(s.substring(11,13));   //从11到12，不包括13  xxxx/xx/xx/ 正好11位
//minute=Integer.parseInt(s.substring(14,16));   // Integer.parseInt转换成引用类型的整数类型
//second=Integer.parseInt(s.substring(17,19)); //获取时间中的秒
//int h=hour%12;
//a=second; //秒针端点的坐标
//b=minute; //分针端点的坐标
//c=h*5+minute/12; //时针端点的坐标
//
//secondLine.setLine(width/2,height/2,(int)pointSX[a],(int)pointSY[a]);   //起始结束
//minuteLine.setLine(width/2,height/2,(int)pointMX[b],(int)pointMY[b]);
//hourLine.setLine(width/2,height/2,(int)pointHX[c],(int)pointHY[c]);
//repaint();  //己有的图形发生变化后不会立刻显示，须使用repaint方法
//
//}
//}
//
//}
