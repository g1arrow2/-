package arrow1.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel username;
	private JTextField usernameField ;
	private JLabel password;
	private JTextField passwordField;
	private JLabel password2;
	private JTextField passwordField2;
	private JLabel sex;
	private String sexstr;
	private JRadioButton jrdman;
	private JRadioButton jrdwoman;
	private ButtonGroup btGroup;
	private JLabel email;
	private JTextField emailField;
	
	public RegisterPanel() {
	username = new JLabel("�û�����");
	usernameField= new JTextField(20);
	password=new JLabel("���룺");
	passwordField= new JTextField(20);
	password2=new JLabel("ȷ�����룺");
	passwordField2= new JTextField(20);
	email=new JLabel("�������䣺");
	emailField= new JTextField(20);
	sex=new JLabel("�Ա�");
	jrdman=new JRadioButton("��");
	jrdwoman=new JRadioButton("Ů");
	btGroup= new ButtonGroup();
	btGroup.add(this.jrdman);
	btGroup.add(this.jrdwoman);
	JPanel jrdPanel=new JPanel();
	jrdPanel.add(jrdman);
	jrdPanel.add(jrdwoman);
	jrdman.addActionListener(new ActionListener()
			{
			public void actionPerformed (ActionEvent arg0) {
		JRadioButton jrbt=(JRadioButton)arg0.getSource();
		if(jrbt.isSelected())
			sexstr= jrbt.getText();
			}
});
	jrdwoman.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent arg0){
		JRadioButton jrbt=(JRadioButton)arg0.getSource();
		if(jrbt.isSelected())

			sexstr= jrbt.getText();}
	});
	this.setLayout(new GridLayout(5,2));
	this.add(username);
	this.add(usernameField);
	this.add(password);
	this.add(passwordField);
	this.add(password2);
	this.add(passwordField2);
	this.add(sex);
	this.add(jrdPanel);
	this.add(email);
	this.add(emailField);
	}
	public String getUsername() {
	return usernameField.getText();
	}
	public String getPassword() 
	{
		return passwordField.getText();
		}
	public String getPassword2() {
	return passwordField2.getText();
	}
	public String getEmail() {
	return emailField.getText();
	}
	public String getSex() {
	return sexstr;
	}
	
	public void clearFields() {
	usernameField.setText("");
	passwordField.setText("");
	passwordField2.setText("");
	emailField.setText("");

	}

}
