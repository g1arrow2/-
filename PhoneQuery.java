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
JButton button = new JButton("��ѯ��Ӫ��");
public PhoneQuery(String string) {
	super(string);
	setIconImage(Toolkit.getDefaultToolkit().getImage(PhoneQuery.class.getResource("ϲ��.png")));
	setBounds(300,300,450,180);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	Container container =getContentPane();
	container.setLayout(new FlowLayout());

	JTextField jTextField=new JTextField();
	jTextField.setColumns(48);
	jTextField.setText("�����ֻ�����(����󰴻س���������ѯ��ť���ɲ�ѯ)");
	jTextField.setFont(new Font("����",Font.BOLD,15));
	container.add(jTextField);
	container.add(button);
	
	new PhoneQueryOperators();

	jTextField.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		phoneString = jTextField.getText().toString();
		String phonequery ="������Ӫ��Ϊ��"+PhoneQueryOperators.checkOperator(phoneString);
		JOptionPane.showConfirmDialog(null,phonequery,"�������Ӫ��",JOptionPane.DEFAULT_OPTION); 
		}
	});jTextField.addMouseListener(new MouseAdapter()//������
		    {
        public void mouseClicked(MouseEvent e)
        {
        	jTextField.getText();
   	        jTextField.setText("");
        }
		    });
	button.addActionListener(new ActionListener()//��ť����
		    {
		        public void actionPerformed(ActionEvent e)
		        {

		        }
		    });
		    button.addMouseListener(new MouseAdapter()//������
		    {
		        public void mouseClicked(MouseEvent e)
		        {
		        phoneString = jTextField.getText().toString();
		    	String phonequery ="������Ӫ��Ϊ��"+PhoneQueryOperators.checkOperator(phoneString);
		    	JOptionPane.showConfirmDialog(null,phonequery,"�������Ӫ��",JOptionPane.DEFAULT_OPTION); 
		        }
		    });
	setVisible(true);
}
	public static void main(String[] args) {
	new PhoneQuery("j��ѯ�ֻ��������Ӫ��j");

	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
