package practice5;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LayoutDemo extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tf;
	private JButton [] buttonArray;
	JTextField showYear,showMonth;
	JButton nextYear,previousYear,nextMonth,previousMonth;   
	JButton saveDailyRecord,deleteDailyRecord,readDailyRecord;
	public LayoutDemo(String str) {
		super(str);
		Container c=getContentPane();
		c.setLayout(new BorderLayout());
		tf=new JTextField();
		c.add(tf,BorderLayout.NORTH);
		JPanel panel =new JPanel();
		panel.setLayout(new GridLayout(5,5,4,4));
		

		buttonArray=new JButton[16];
		String buttonName[]= {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		for(int i=0;i<buttonArray.length;i++) {
			buttonArray[i]=new JButton(buttonName[i]);
			buttonArray[i].addActionListener(this);
			panel.add(buttonArray[i]);
		}
		nextYear = new JButton("����");
		previousYear = new JButton("����");
		nextMonth = new JButton("���� ");
		previousMonth = new JButton("���� ");
		showYear = new JTextField("",6);
		showYear.setFont(new Font("TimesRoman",Font.BOLD,12));
		showYear.setHorizontalAlignment(JTextField.CENTER);
		showMonth = new JTextField("",4);
		showMonth.setFont(new Font("TimesRoman",Font.BOLD,12));
		showMonth.setHorizontalAlignment(JTextField.CENTER);
		
		saveDailyRecord = new JButton("������־");
		deleteDailyRecord = new JButton("ɾ���� ־");
		readDailyRecord = new JButton("��ȡ��־");

		JPanel north = new JPanel();
		north.add(previousYear);
		north.add(showYear);
		north.add(nextYear);
		north.add(previousMonth);
		north.add(showMonth);
		north.add(nextMonth);
			add(north,BorderLayout.NORTH);

		JPanel pSouth = new JPanel();
			pSouth.add(saveDailyRecord);
			pSouth.add(deleteDailyRecord);
			pSouth.add(readDailyRecord);
			add(pSouth,BorderLayout.SOUTH);
		JSplitPane sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JButton("���Ϸ�"),new JButton("���·�"));
		sp.setDividerLocation(100);
		JSplitPane splitPane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JButton("���Ϸ�"),new JButton("���·�"));
		splitPane.setDividerLocation(100);
		JSplitPane splitH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,sp,splitPane);
		add(splitH,BorderLayout.CENTER);
		
		setVisible(true);
		setBounds(70,70,750,680);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new LayoutDemo("jj����");
		}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		String s=e.getActionCommand();
		tf.setText(s);
}
}