package arrow1.UI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton loginButton;
	private JButton regButton;
	private JButton exitButton;
	
	public ButtonPanel() {
		loginButton = new JButton("��¼");
		regButton = new JButton("ע��");
		exitButton = new JButton("�˳�");
		this.setLayout(new FlowLayout());
		this.add(loginButton);
		this.add(regButton);
		this.add(exitButton);
	}
	public JButton getlogButton() {
		return loginButton;
	}
	public JButton getregButton() {
		return regButton;
	}
	public JButton getExitButton() {
		return exitButton;
	}

}
