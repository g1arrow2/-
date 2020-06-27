package arrow2;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textEdit =null;
	private JScrollPane pane = null;
	
	public TextEditFrame() {
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		textEdit = new JTextArea();
		//textEdit.setLineWrap(true);  //自动回行功能
		
		pane = new JScrollPane(textEdit,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		container.add(pane,BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(TextEditFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public JTextArea getTextEdit() {
		return this.textEdit;
		
	}
}
