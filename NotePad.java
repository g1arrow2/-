package practice4;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
		class NotePad extends JPanel implements ActionListener,MouseListener,ItemListener {  //������
		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		JPopupMenu popup;   //�����˵���
		JMenuItem cut,copy,paste;       //�˵���
		JTextField ShowMessage,time;
		JTextArea ta;
		JPanel p1,p2,p3;
		Choice list1,list2;   //ѡ����
		JButton btn;
		JLabel labl;
		String Size[]={"10","12","14","16","18","20","22","24","26","28","30","32","34","36"};        //�����С
		
		
		@SuppressWarnings("deprecation")
		NotePad()    //������
		{
		popup=new JPopupMenu();  
		
		ShowMessage=new JTextField();
		ShowMessage.setEditable(false);
		ta=new JTextArea(5,20);    //5������20����
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		btn=new JButton("��ɫ");
		labl=new JLabel("�¼�����ʱ�䣨hh�� mm���� ");
		time=new JTextField(10);
		list1=new Choice();
		list2=new Choice();
		
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();   //ͼ�λ���
		String fontname[]=ge.getAvailableFontFamilyNames();//�������
		
		for(int i=0;i<fontname.length;i++)
		{
		list1.add(fontname[i]);
		}
		
		for(int i=0;i<Size.length;i++)
		{
		list2.add(Size[i]);
		}
		cut=new JMenuItem("����");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		copy=new JMenuItem("����");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		paste=new JMenuItem("ճ��");
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
		ShowMessage.setText(year+"��"+month+"�� "+day+"�� ");
		ShowMessage.setForeground(Color.blue);
		ShowMessage.setFont(new Font("����",Font.BOLD,15));
		}
		
		
		public void actionPerformed(ActionEvent e)
		{
			
		if(e.getSource()==cut)
		{
		cut();
		}
		
		if(e.getSource()==copy)     //�������������Event�Ķ����¼�Դ��  
		{
		copy();
		}
		
		if(e.getSource()==paste)
		{
		paste();
		}
		
		if(e.getSource()==btn)
		{
		Color newColor=JColorChooser.showDialog(this, "ѡ����ɫ", ta.getForeground());
		
		if(newColor!=null)  //����ǰ��ɫ
		{
		ta.setForeground(newColor);
		}
		}
		}
		
		
		public void itemStateChanged(ItemEvent e)    //����ѡ���¼�
		{
		String n1=list2.getSelectedItem();    //ѡ�е���Ŀ
		String name=list1.getSelectedItem();
		int n2=Integer.parseInt(n1);     //��n1ת��������
		Font f=new Font(name,Font.PLAIN,n2);  // String ���壬int ���int �ֺ� Font.PLAIN��ͨ
		//��ʽ
		ta.setFont(f);
		}
		
		
		public void mouseReleased(MouseEvent e)
		{
			
		if(e.getButton()==MouseEvent.BUTTON3)   //�Ҽ�
		{
		popup.show(ta,e.getX(),e.getY());  //  ta - �����˵���ʾ����ռ��е����x  y  λ��
		 }   // .show������ʾ�б�
		
		if(e.getButton()==MouseEvent.BUTTON1)    //���
		{
		popup.setVisible(false);    //���ɼ�
		}
		}
		
		
		public void mousePressed(MouseEvent e){}   //�ͷ�
		public void mouseEntered(MouseEvent e){}  //����
		public void mouseExited(MouseEvent e){}   //�뿪
		public void mouseClicked(MouseEvent e){}   //���
		
		
		public void cut()
		{
		ta.cut();
		popup.setVisible(false);      //����֮�����ؿؼ�
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
		
		
		public void savefile(File dir,int year,int month,int day)   //�������ݲ���ָ�����ڱ�����־
		{
		String dailyRecord=time.getText()+"#"+ta.getText()+"#";   //ʱ����ı��������
		String fileName=""+year+""+month+""+day+".txt";      //�ļ���
		String key=""+year+""+month+""+day;     
		String dialyFile[]=dir.list();    //����Ŀ¼
		boolean b=false;
		
		for(int i=0;i<dialyFile.length;i++)    //С���ļ�����ĳ���
		{
			
		if(dialyFile[i].startsWith(key))   //����ַ����Ƿ���ָ����ǰ׺��ʼ��
		
		{
		b=true;
		break;
		}
		}
		
		if(b)
		{
		int n=JOptionPane.showConfirmDialog(this,""+year+"��"+month+"�� "+day+"�� "+"�Ѿ�����־���ڣ� �Ƿ������־�� ","ȷ�϶Ի���",JOptionPane.YES_NO_OPTION);
		
		if(n==JOptionPane.YES_OPTION)      //////��ʾȷ�Ͽ�
		
		{
		try
		{
		File file=new File(dir,fileName);
		RandomAccessFile out=new RandomAccessFile(file,"rw");   //�����ı� //rw��������д����//���������
		long end=out.length();      
		byte[]bb=dailyRecord.getBytes();   //�õ�һ������ϵͳĬ�ϵı����ʽ���ֽ�����
		out.seek(end);    ///�����ƶ��ļ���ȡָ�뵽ָ��λ��
		out.write(bb);      //д�������ݷŵ����������
		out.close();         //�������
		}
		catch(IOException e){}
		ta.setText("");    
		}
		else
		{
		ta.setText("");
		}
		}
		else     //���������keyǰ׺��ʼ�Ļ���ִ������
		{
		
			try
		{
		File file=new File(dir,fileName);     //ת���ɳ���·����������һ���µ� File ʵ��
		FileWriter fw=new FileWriter(file);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(dailyRecord);    //д���ı�������
		bw.close();         //����ı�������
		fw.close();
		}
		catch(IOException e){}
		JOptionPane.showMessageDialog(this," �� �� �� ־ �� �� "," �� Ϣ �� �� ��",JOptionPane.INFORMATION_MESSAGE);
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
		int n=JOptionPane.showConfirmDialog(this," �� �� ɾ �� "+year+" �� "+month+" ��"+day+"�յ���־�� ","ȷ�϶Ի���",JOptionPane.YES_NO_OPTION);
		
		if(n==JOptionPane.YES_OPTION)
		{
		
			try
		{
		String fileName=""+year+""+month+""+day+".txt";
		File file=new File(dir,fileName);     //ͬ�ϣ�����
		file.delete();
		}
		catch(Exception e){}
		ta.setText("");
		}
		}
		else
		{
		JOptionPane.showMessageDialog(this,""+year+"��"+month+"�� "+day+"�� ���� ־��","��Ϣ�Ի���",JOptionPane.INFORMATION_MESSAGE);
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
		FileReader inOne=new FileReader(file);   //��ȡ�ļ����ı�����
		BufferedReader inTwo=new BufferedReader(inOne);   //����ʹ��Ĭ�ϴ�С�����뻺�����Ļ����ַ���������
		String s;
		
		while((s=inTwo.readLine())!=null)     //��s����bfr �Ҳ����ڿ�
		{
		ta.append(s+"\n");     //���ı����м���s
		}
		inOne.close();    //���������������������
		inTwo.close();    //�������
		}
		catch(IOException e){}
		}
		else
		{
		JOptionPane.showMessageDialog(this,""+year+"��"+month+"�� "+day+"�� ���� ־��","��Ϣ�Ի���",JOptionPane.INFORMATION_MESSAGE);
		}
		}
		}
