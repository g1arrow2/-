package arrow1.UI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private JLabel usernameJLabel;
	private JTextField usernameField;
	private JLabel passwordJLabel;
	private JPasswordField passwordField;
	
	public  LoginPanel() {
		usernameJLabel = new JLabel("”√ªß√˚");
		usernameField = new JTextField(15);
		passwordJLabel = new JLabel("√‹¬Î£∫");
		passwordField = new JPasswordField(15);
		passwordField.setEchoChar('*');
		this.setLayout(new GridLayout(3,2));
		this.add(usernameJLabel);
		this.add(usernameField);
		this.add(passwordJLabel);
		this.add(passwordField);
	}
	
	public String getUsername() 
	{return usernameField.getText();}
	public String getPassword() 
	{return new String(passwordField.getPassword());}
	


}
