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
		loginButton = new JButton("µÇÂ¼");
		regButton = new JButton("×¢²á");
		exitButton = new JButton("ÍË³ö");
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
