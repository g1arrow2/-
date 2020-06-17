package practice6;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class PhoneQuery extends JFrame implements ActionListener {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String phoneString;
JButton button = new JButton("查询运营商");
public PhoneQuery(String string) {
	super(string);
	setIconImage(Toolkit.getDefaultToolkit().getImage(PhoneQuery.class.getResource("喜爱.png")));
	setBounds(300,300,450,180);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	Container container =getContentPane();
	container.setLayout(new FlowLayout());

	JTextField jTextField=new JTextField();
	jTextField.setColumns(48);
	jTextField.setText("输入手机号码(输入后按回车键或点击查询按钮即可查询)");
	jTextField.setFont(new Font("楷体",Font.BOLD,15));
	container.add(jTextField);
	container.add(button);
	
	new PhoneQueryOperators();

	jTextField.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		phoneString = jTextField.getText().toString();
		String phonequery ="号码运营商为："+PhoneQueryOperators.checkOperator(phoneString);
		JOptionPane.showConfirmDialog(null,phonequery,"号码的运营商",JOptionPane.DEFAULT_OPTION); 
		}
	});jTextField.addMouseListener(new MouseAdapter()//鼠标监听
		    {
        public void mouseClicked(MouseEvent e)
        {
        	jTextField.getText();
   	        jTextField.setText("");
        }
		    });
	button.addActionListener(new ActionListener()//按钮监听
		    {
		        public void actionPerformed(ActionEvent e)
		        {

		        }
		    });
		    button.addMouseListener(new MouseAdapter()//鼠标监听
		    {
		        public void mouseClicked(MouseEvent e)
		        {
		        phoneString = jTextField.getText().toString();
		    	String phonequery ="号码运营商为："+PhoneQueryOperators.checkOperator(phoneString);
		    	JOptionPane.showConfirmDialog(null,phonequery,"号码的运营商",JOptionPane.DEFAULT_OPTION); 
		        }
		    });
	setVisible(true);
}
	public static void main(String[] args) {
	new PhoneQuery("j查询手机号码的运营商j");

	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
