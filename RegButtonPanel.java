package arrow1.UI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RegButtonPanel extends JPanel {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JButton submitButton;
 private JButton cancelButton;
 
 public RegButtonPanel() {
	submitButton = new JButton("�ύ");
	cancelButton = new JButton("ȡ��");
	this.setLayout(new FlowLayout());
	this.add(submitButton);
	this.add(cancelButton);
}
public JButton getSubmitButton() {
	return submitButton;
}
public JButton getCancelButton() {
	return cancelButton;
}
}

