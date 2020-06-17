package practice5;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FrameDemo extends JFrame implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
	 * 
	 */
	private JTextField text;
	private JButton button;
public FrameDemo (String string) {
	super(string);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameDemo.class.getResource("电脑.png")));
		Container contains=getContentPane();
		contains.setLayout(new FlowLayout());
		text=new JTextField(20);
		button = new JButton("确定");
		button.addActionListener(this);
		contains.add(text);
		contains.add(button);
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	public static void main(String[] args) {
		FrameDemo test = new FrameDemo("窗体");
		test.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button) {
			JOptionPane.showMessageDialog(null, "确定退出吗？");
		}

	}
}
