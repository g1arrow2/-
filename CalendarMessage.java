package practice4;

	import java.util.Calendar;   //����ģ���
	
	
	public class CalendarMessage{   //������
	int year=-1,month=-1,day=-1;    //��ʼ��������Ϊʲô����-1���¿�
	
	
	public void setYear(int year){    //getset��
	this.year=year;
	}
	
	
	public int getYear(){
	return year;
	}
	
	
	public void setMonth(int month){
	if(month<=12&&month>=1)
	this.month=month;
	else
	this.month=1;
	}
	
	
	public int getMonth(){
	return month;
	}
	
	
	public void setDay(int day){
	this.day=day;
	}
	
	
	public int getday()
	{
	return day;
	}
	
	
	public String [] getMonthCalendar(){   //6*7����  
	String [] day=new String[42];    //��ƪ���������  ��ȡֵ
	Calendar rili=Calendar.getInstance();  ////���౻abstract�����Σ�˵������ͨ��new�ķ�ʽ�����ʵ�����Դˣ�Calendar�ṩ��һ���෽��getInstance��
	//�Ի�ô����͵�һ��ͨ�õĶ���getInstance��������һ��Calendar���󣨸ö���ΪCalendar��������󣩣��������ֶ����ɵ�ǰ���ں�ʱ���ʼ����

	rili.set(year,month-1,1);            //���������� year �� month �� 1 ��
	int ���ڼ�=rili.get(Calendar.DAY_OF_WEEK)-1;   //������������˰�
	//// https://blog.csdn.net/djun100/article/details/9226507
	//����������Ϊһ�ܵĵ�һ��
	//week����ֵ��sunΪ1 
	//������������ѭ��
	int dayAmount=0;
	
	
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
	dayAmount=31;    //����
	
	
	if(month==4||month==6||month==9||month==11)
	dayAmount=30;  //С��
	if(month==2)     //2��
	
		
		if(((year%4==0)&&(year%100!=0))||year%400==0)    //����
	dayAmount=29;
	else   //��������û���أ���֪�������������ټ�
	dayAmount=28;
	
	for(int i=0;i<���ڼ�;i++)   
	day[i]=" ";  //���ַ�
	
	for(int i=���ڼ�,n=1;i<���ڼ�+dayAmount;i++){  //�㷨�߼���Ӧ���ǡ�����
	day[i]=String.valueOf(n);   //��һ���ʱ�������ڼ���Ȼ��ʼ��day�����֣�
	n++;     //    i<���ڼ���dayAmount   ���﷨��ء����Ķ�����
	}
	
	for (int i=���ڼ�+dayAmount;i<42;i++ )   //��ʣ�µ���Ϊ�� 
	day[i]=" ";
	return day;
	}
	}

