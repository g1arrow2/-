package practice4;

	import java.util.Calendar;   //日历模块包
	
	
	public class CalendarMessage{   //定义类
	int year=-1,month=-1,day=-1;    //初始化变量，为什么设置-1往下看
	
	
	public void setYear(int year){    //getset类
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
	
	
	public String [] getMonthCalendar(){   //6*7格子  
	String [] day=new String[42];    //下篇代码从这里  获取值
	Calendar rili=Calendar.getInstance();  ////该类被abstract所修饰，说明不能通过new的方式来获得实例，对此，Calendar提供了一个类方法getInstance，
	//以获得此类型的一个通用的对象，getInstance方法返回一个Calendar对象（该对象为Calendar的子类对象），其日历字段已由当前日期和时间初始化：

	rili.set(year,month-1,1);            //将日历翻到 year 年 month 月 1 日
	int 星期几=rili.get(Calendar.DAY_OF_WEEK)-1;   //这个变量犯规了吧
	//// https://blog.csdn.net/djun100/article/details/9226507
	//以星期日作为一周的第一天
	//week返回值中sun为1 
	//便于理解下面的循环
	int dayAmount=0;
	
	
	if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
	dayAmount=31;    //大月
	
	
	if(month==4||month==6||month==9||month==11)
	dayAmount=30;  //小月
	if(month==2)     //2月
	
		
		if(((year%4==0)&&(year%100!=0))||year%400==0)    //闰年
	dayAmount=29;
	else   //中括号少没少呢，不知道。反正报错再加
	dayAmount=28;
	
	for(int i=0;i<星期几;i++)   
	day[i]=" ";  //空字符
	
	for(int i=星期几,n=1;i<星期几+dayAmount;i++){  //算法逻辑呢应该是。。。
	day[i]=String.valueOf(n);   //第一天的时候是星期几，然后开始往day加数字，
	n++;     //    i<星期几加dayAmount   与语法相关。懂的都懂。
	}
	
	for (int i=星期几+dayAmount;i<42;i++ )   //把剩下的设为空 
	day[i]=" ";
	return day;
	}
	}

