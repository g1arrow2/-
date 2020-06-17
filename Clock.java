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
    private Font f = new Font("΢���ź�",Font.PLAIN,15);
    private Font f2 = new Font("΢���ź�",Font.BOLD,15);
    private JLabel l = new JLabel("��ǰʱ�䣺");
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
         //����ݿ��ؿ�        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       
         //������
        g.setPaint(new GradientPaint(5,40,Color.blue,15,50,Color.yellow,true));
        g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
        g.drawOval(75, 40, 250, 250);
        g.fillOval(195, 160, 10, 10);
        g.setColor(Color.black);
                 //��60����
     
        for(int i = 0;i < 60;i++)
         {
             double[] co = new double[2];
            co = paint_Dot(i * 2 * PI / 60);
            x = co[0];
            y = co[1];
            if(i == 0 || i == 15 || i == 30 || i == 45)//��3,6,9,12�ĸ����
             {
                g.fillOval((int)(x - 5 + 200),(int)(y - 5 + 165),10,10);
            }
            else//����С��
            {
                 g.fillOval((int)(x - 2.5 + 200),(int)(y - 2.5 + 165),5,5);
             }
         }
         
         //���ĸ�����
         g.setFont(f2);
         g.drawString("3", 300, 171);
         g.drawString("6", 195, 273);
         g.drawString("9", 91, 171);
         g.drawString("12", 190, 68);
         
         //��ʱ�룬���룬����
         paint_HourPointer(hour*3600 + min*60 + sec,g);//ʱ���߹�������
         paint_MinutePointer(min*60 + sec,g);//�����߹�������
         paint_SecondPointer(sec,g);//�����߹�������
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
                  		  System.out.println("���������������");
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
 	sethour = Integer.parseInt(JOptionPane.showInputDialog("������Сʱ��")); 
 	setminute = Integer.parseInt(JOptionPane.showInputDialog("��������ӣ�")); 
 	setsecond = Integer.parseInt(JOptionPane.showInputDialog("�������룺"));
 }
   
 
     public void paint_HourPointer(int second,Graphics2D g){//second��ʾ��ǰʱ���ʱ�����00:00:00���˶�����
         double x,y,angle; 
         angle = second * PI / 21600;//ʱ����ٶ�ΪPI/21600 (rad/s)
        x = 200 + 60 * Math.sin(angle);
         y = 165 - 60 * Math.cos(angle);
         g.setStroke( new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
         g.setPaint(new GradientPaint(200,165,Color.red,260,165,Color.blue,true));
         g.drawLine(200, 165, (int)x, (int)y);
    }
     
     
     public void paint_MinutePointer(int second,Graphics2D g){//second��ʾ��ǰʱ��ķ������00:00:00���˶�����
        double x,y,angle;
        angle = second * PI / 1800;//������ٶ�ΪPI/1800 (rad/s)
      x = 200 + 80 * Math.sin(angle);
         y = 165 - 80 * Math.cos(angle);
         g.setStroke( new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
       g.setPaint(new GradientPaint(200,165,Color.magenta,280,165,Color.blue,true));
         g.drawLine(200, 165, (int)x, (int)y);
     } 
     
     public void paint_SecondPointer(int second,Graphics2D g){//second��ʾ��ǰʱ����������00:00:00���˶�����
    	 double x,y,x1,y1,x2,y2,x3,y3,angle;
    	 double cos = 90 / Math.sqrt(8125);//90*90+5*5
        double sin = 5 / Math.sqrt(8125);
        angle = second * PI / 30;//ʱ����ٶ�ΪPI/30 (rad/s)
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
         co[0] = 115 * Math.cos(angle);//������
         co[1] = 115 * Math.sin(angle);//������
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
         //�ڴ�����������ʾʱ��
         date = " " + (now.getYear()+1900) + "��" + (now.getMonth()+1) + "��" + now.getDate() + "��   " + "����" ;
        
         switch (now.getDay()) {
         case 1:
             date += "һ";
             break;
         case 2:
             date += "��";
             break;
        case 3:
             date += "��";
            break;
        case 4:
             date += "��";
            break;
         case 5:
             date += "��";
             break;
         case 6:
             date += "��";
             break;
         case 7:
             date += "��";
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
//javax.swing.Timer secondTime;   //����������һ��ÿ�뼤��һ�β����¼��ļ�ʱ��
//int hour,minute,second;
//Line2D secondLine,minuteLine,hourLine;
//int a,b,c,width,height;
//static int sethour; 
//static int setminute; 
//static int setsecond; 
//
//double [] pointSX=new double[60], //������ʾ����˵����������
//pointSY=new double[60],
//pointMX=new double[60], //������ʾ����˵����������
//pointMY=new double[60],
//pointHX=new double[60], //������ʾʱ��˵����������
//pointHY=new double[60];
//Clock(){
//setBackground(Color.cyan);
//initPoint();
//secondTime=new javax.swing.Timer(1000,this);   //�ӳ�1000ms
//secondLine=new Line2D.Double(0,0,0,0);   //��ʼ���� ��ʼ����
//minuteLine=new Line2D.Double(0,0,0,0);
//hourLine=new Line2D.Double(0,0,0,0);
//secondTime.start(); //���뿪ʼ��ʱ
//
//}
//private void initPoint(){
//width=getBounds().width;
//height=getBounds().height;
//pointSX[0]=0; //12 ������λ��
//pointSY[0]=-height/2*5/6;
//pointMX[0]=0; //12 �����λ��
//pointMY[0]=-(height/2*4/5);
//pointHX[0]=0; //12 ��ʱ��λ��
//pointHY[0]=-(height/2*2/3);
//double angle=6*Math.PI/180; //�̶�Ϊ 6 ��
//for(int i=0;i<59;i++) { //���������е�����
//pointSX[i+1]=pointSX[i]*Math.cos(angle)-Math.sin(angle)*pointSY[i];
//pointSY[i+1]=pointSY[i]*Math.cos(angle)+pointSX[i]*Math.sin(angle);
//pointMX[i+1]=pointMX[i]*Math.cos(angle)-Math.sin(angle)*pointMY[i];
//pointMY[i+1]=pointMY[i]*Math.cos(angle)+pointMX[i]*Math.sin(angle);
//pointHX[i+1]=pointHX[i]*Math.cos(angle)-Math.sin(angle)*pointHY[i];
//pointHY[i+1]=pointHY[i]*Math.cos(angle)+pointHX[i]*Math.sin(angle);
//}     //��Ҫ���Ƕ��Ķ���    
//for(int i=0;i<60;i++){     //���ﻹҪ�����һ��  ����forѭ�����Ϊʲô��
//pointSX[i]=pointSX[i]+width/2; //����ƽ��
//pointSY[i]=pointSY[i]+height/2;
//pointMX[i]=pointMX[i]+width/2; //����ƽ��
//pointMY[i]=pointMY[i]+height/2;
//pointHX[i]=pointHX[i]+width/2; //����ƽ��
//pointHY[i]=pointHY[i]+height/2;
//}
//}
//@SuppressWarnings("deprecation")
//public void paintComponent(Graphics g){
//super.paintComponent(g);
//initPoint();
//for(int i=0;i<60;i++){ //���Ʊ����ϵ�С�̶Ⱥʹ�̶�
//int m=(int)pointSX[i];
//int n=(int)pointSY[i];
//if(i%5==0){
//if(i==0||i==15||i==30||i==45){
//int k=10;
//g.setColor(Color.orange);
//g.fillOval(m-k/2,n-k/2,k,k);   //��Բ������
//}
//else{
//int k=7;
//g.setColor(Color.orange);
//g.fillOval(m-k/2,n-k/2,k,k);
//}
//}
//else{    //�����5����0
//int k=2;
//g.setColor(Color.black);
//g.fillOval(m-k/2,n-k/2,k,k);
//}
//}
//g.fillOval(width/2-5,height/2-5,10,10); //�ӱ����ĵ�ʵ��Բ
//Graphics2D g_2d=(Graphics2D)g;
//g_2d.setColor(Color.red);
//g_2d.draw(secondLine);
//BasicStroke bs=      //������
//new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
//g_2d.setStroke(bs);
//g_2d.setColor(Color.blue);
//g_2d.draw(minuteLine);
//bs=new BasicStroke(4f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);  
////4f ���
//// BasicStroke.CAP_BUTT,����δ�պϵ���·�������߶Σ�û�����װ�Ρ�
//// JOIN_MITERͨ����չ���ǵ����Եֱ����������������·����
//g_2d.setStroke(bs);
//g_2d.setColor(Color.orange);
//g_2d.draw(hourLine);
//if((minute==59)&&(second==50))
//{
////����ָ����Ƶ��
//try {
//File f = new File("BGM.wav");//��Ƶ�ļ����Լ���
//URI uri=f.toURI();
//URL url=uri.toURL();
//AudioClip aau;
////// AudioClip��һ������Ƭ�Σ��磺��ť������൱����һ�����֣�AudioSource��һ��Դ������AudioClip����������һЩ���ԣ��磺�Ƿ����ȣ��൱����һ�����ֲ�����
//aau = Applet.newAudioClip(url); //���� AudioClip ����   //���������Ϊ�������������ĳЩ�����Ŀ�������Ҫ���⣬���������ǲ���������ж����ģ��Ͼ������̭�ˣ�Ϊɶʹ�ã�Ϊ�˿��ԡ� �Ѿ���̭�Ķ���  ����֪���ܲ����á�
//aau.play();//������Ƶ��
//} catch (MalformedURLException e) {
//e.printStackTrace();
//}
//}
//}
//public void AClock() {
//	sethour = Integer.parseInt(JOptionPane.showInputDialog("������Сʱ��")); 
//	setminute = Integer.parseInt(JOptionPane.showInputDialog("��������ӣ�")); 
//	setsecond = Integer.parseInt(JOptionPane.showInputDialog("�������룺"));
//	Alarm();
//}
//public void Alarm() {
//	GregorianCalendar calendar; 
//	calendar = new GregorianCalendar();
//	
//	System.out.println("������");
//
//	hour = calendar.get(Calendar.HOUR); 
//	minute = calendar.get(Calendar.MINUTE); 
//	second = calendar.get(Calendar.SECOND); 
//	 if(sethour == hour || setminute == minute || setsecond == second){ 
//		  System.out.println("���������������");
//		Play0 play= new Play0();
//		play.playMusic();
//
//		   }
//}
//public void actionPerformed(ActionEvent e){
//if(e.getSource()==secondTime){
//date=new Date();
//String s=date.toString();    //ת�����ַ������
//hour=Integer.parseInt(s.substring(11,13));   //��11��12��������13  xxxx/xx/xx/ ����11λ
//minute=Integer.parseInt(s.substring(14,16));   // Integer.parseIntת�����������͵���������
//second=Integer.parseInt(s.substring(17,19)); //��ȡʱ���е���
//int h=hour%12;
//a=second; //����˵������
//b=minute; //����˵������
//c=h*5+minute/12; //ʱ��˵������
//
//secondLine.setLine(width/2,height/2,(int)pointSX[a],(int)pointSY[a]);   //��ʼ����
//minuteLine.setLine(width/2,height/2,(int)pointMX[b],(int)pointMY[b]);
//hourLine.setLine(width/2,height/2,(int)pointHX[c],(int)pointHY[c]);
//repaint();  //���е�ͼ�η����仯�󲻻�������ʾ����ʹ��repaint����
//
//}
//}
//
//}
