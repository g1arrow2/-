package practice4;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
		class NotePad extends JPanel implements ActionListener,MouseListener,ItemListener {  //监听器
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		JPopupMenu popup;   //弹出菜单类
		JMenuItem cut,copy,paste;       //菜单项
		JTextField ShowMessage,time;
		JTextArea ta;
		JPanel p1,p2,p3;
		Choice list1,list2;   //选择项
		JButton btn;
		JLabel labl;
		String Size[]={"10","12","14","16","18","20","22","24","26","28","30","32","34","36"};        //字体大小
		
		
		@SuppressWarnings("deprecation")
		NotePad()    //构造器
		{
		popup=new JPopupMenu();  
		
		ShowMessage=new JTextField();
		ShowMessage.setEditable(false);
		ta=new JTextArea(5,20);    //5行数和20列数
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		btn=new JButton("颜色");
		labl=new JLabel("事件发生时间（hh： mm）： ");
		time=new JTextField(10);
		list1=new Choice();
		list2=new Choice();
		
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();   //图形环境
		String fontname[]=ge.getAvailableFontFamilyNames();//字体家族
		
		for(int i=0;i<fontname.length;i++)
		{
		list1.add(fontname[i]);
		}
		
		for(int i=0;i<Size.length;i++)
		{
		list2.add(Size[i]);
		}
		cut=new JMenuItem("剪切");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		copy=new JMenuItem("复制");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		paste=new JMenuItem("粘贴");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		popup.add(cut);
		popup.add(copy);
		popup.add(paste);
		p1.add(list1);
		p1.add(list2);
		//p1.add(btn);
		p3.add(labl);
		p3.add(time);
		setLayout(new BorderLayout());
		add(ShowMessage,BorderLayout.NORTH);
		p2.setLayout(new BorderLayout());
		p2.add(new JScrollPane(ta),BorderLayout.CENTER);
		//p2.add(p3,BorderLayout.SOUTH);
		add(p2,BorderLayout.CENTER);
		add(p1,BorderLayout.SOUTH);
		setBounds(0,0,600,600);
		setVisible(true);
		btn.addActionListener(this);
		list1.addItemListener(this);
		list2.addItemListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		ta.addMouseListener(this);
		}
		
		
		public void setMessage(int year,int month,int day)
		{
		ShowMessage.setText(year+"年"+month+"月 "+day+"日 ");
		ShowMessage.setForeground(Color.blue);
		ShowMessage.setFont(new Font("宋体",Font.BOLD,15));
		}
		
		
		public void actionPerformed(ActionEvent e)
		{
			
		if(e.getSource()==cut)
		{
		cut();
		}
		
		if(e.getSource()==copy)     //返回最初发生的Event的对象（事件源）  
		{
		copy();
		}
		
		if(e.getSource()==paste)
		{
		paste();
		}
		
		if(e.getSource()==btn)
		{
		Color newColor=JColorChooser.showDialog(this, "选择颜色", ta.getForeground());
		
		if(newColor!=null)  //设置前景色
		{
		ta.setForeground(newColor);
		}
		}
		}
		
		
		public void itemStateChanged(ItemEvent e)    //捕获选项事件
		{
		String n1=list2.getSelectedItem();    //选中的项目
		String name=list1.getSelectedItem();
		int n2=Integer.parseInt(n1);     //把n1转换成整数
		Font f=new Font(name,Font.PLAIN,n2);  // String 字体，int 风格，int 字号 Font.PLAIN普通
		//样式
		ta.setFont(f);
		}
		
		
		public void mouseReleased(MouseEvent e)
		{
			
		if(e.getButton()==MouseEvent.BUTTON3)   //右键
		{
		popup.show(ta,e.getX(),e.getY());  //  ta - 弹出菜单显示在其空间中的组件x  y  位置
		 }   // .show方法显示列表
		
		if(e.getButton()==MouseEvent.BUTTON1)    //左键
		{
		popup.setVisible(false);    //不可见
		}
		}
		
		
		public void mousePressed(MouseEvent e){}   //释放
		public void mouseEntered(MouseEvent e){}  //进入
		public void mouseExited(MouseEvent e){}   //离开
		public void mouseClicked(MouseEvent e){}   //点击
		
		
		public void cut()
		{
		ta.cut();
		popup.setVisible(false);      //调用之后隐藏控件
		}
		
		
		public void copy()
		{
		ta.copy();
		popup.setVisible(false);
		}
		
		
		public void paste()
		{
		ta.paste();
		popup.setVisible(false);
		}
		
		
		public void savefile(File dir,int year,int month,int day)   //方法根据参数指定日期保存日志
		{
		String dailyRecord=time.getText()+"#"+ta.getText()+"#";   //时间加文本域的内容
		String fileName=""+year+""+month+""+day+".txt";      //文件名
		String key=""+year+""+month+""+day;     
		String dialyFile[]=dir.list();    //遍历目录
		boolean b=false;
		
		for(int i=0;i<dialyFile.length;i++)    //小于文件数组的长度
		{
			
		if(dialyFile[i].startsWith(key))   //检测字符串是否以指定的前缀开始。
		
		{
		b=true;
		break;
		}
		}
		
		if(b)
		{
		int n=JOptionPane.showConfirmDialog(this,""+year+"年"+month+"月 "+day+"日 "+"已经有日志存在， 是否添加日志？ ","确认对话框",JOptionPane.YES_NO_OPTION);
		
		if(n==JOptionPane.YES_OPTION)      //////显示确认框
		
		{
		try
		{
		File file=new File(dir,fileName);
		RandomAccessFile out=new RandomAccessFile(file,"rw");   //处理文本 //rw读操作和写操作//都是允许的
		long end=out.length();      
		byte[]bb=dailyRecord.getBytes();   //得到一个操作系统默认的编码格式的字节数组
		out.seek(end);    ///用于移动文件读取指针到指定位置
		out.write(bb);      //写出的内容放到输出缓冲区
		out.close();         //真正输出
		}
		catch(IOException e){}
		ta.setText("");    
		}
		else
		{
		ta.setText("");
		}
		}
		else     //如果不是以key前缀开始的话就执行这里
		{
		
			try
		{
		File file=new File(dir,fileName);     //转换成抽象路径名来创建一个新的 File 实例
		FileWriter fw=new FileWriter(file);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(dailyRecord);    //写入文本域内容
		bw.close();         //输出文本域内容
		fw.close();
		}
		catch(IOException e){}
		JOptionPane.showMessageDialog(this," 添 加 日 志 成 功 "," 消 息 对 话 框",JOptionPane.INFORMATION_MESSAGE);
		ta.setText("");
		time.setText("");
		}
		}
		
		
		public void deletefile(File dir,int year,int month,int day)
		{
		String key=""+year+""+month+""+day;
		String dialyFile[]=dir.list();
		boolean b=false;
		
		
		for(int i=0;i<dialyFile.length;i++)
		{
		
			if(dialyFile[i].startsWith(key))
		{
		b=true;
		break;
		}
		}
		
		if(b)
		{
		int n=JOptionPane.showConfirmDialog(this," 是 否 删 除 "+year+" 年 "+month+" 月"+day+"日的日志？ ","确认对话框",JOptionPane.YES_NO_OPTION);
		
		if(n==JOptionPane.YES_OPTION)
		{
		
			try
		{
		String fileName=""+year+""+month+""+day+".txt";
		File file=new File(dir,fileName);     //同上！！！
		file.delete();
		}
		catch(Exception e){}
		ta.setText("");
		}
		}
		else
		{
		JOptionPane.showMessageDialog(this,""+year+"年"+month+"月 "+day+"日 无日 志！","消息对话框",JOptionPane.INFORMATION_MESSAGE);
		}
		}
		
		
		public void readfile(File dir,int year,int month,int day)     //crud
		{
		String fileName=""+year+""+month+""+day+".txt";
		String key=""+year+""+month+""+day;
		String dialyFile[]=dir.list();
		boolean b=false;
		
		for(int i=0;i<dialyFile.length;i++)
		{
		
			if(dialyFile[i].startsWith(key))
		{
		b=true;
		break;
		}
		}
		
		if(b)
		{
		ta.setText("");
		time.setText("");
		
		try
		{
		File file=new File(dir,fileName);
		FileReader inOne=new FileReader(file);   //读取文件的文本内容
		BufferedReader inTwo=new BufferedReader(inOne);   //创建使用默认大小的输入缓冲区的缓冲字符输入流。
		String s;
		
		while((s=inTwo.readLine())!=null)     //让s等于bfr 且不等于空
		{
		ta.append(s+"\n");     //则文本域中加入s
		}
		inOne.close();    //真正输出（跳出缓冲区）
		inTwo.close();    //真正输出
		}
		catch(IOException e){}
		}
		else
		{
		JOptionPane.showMessageDialog(this,""+year+"年"+month+"月 "+day+"日 无日 志！","消息对话框",JOptionPane.INFORMATION_MESSAGE);
		}
		}
		}
