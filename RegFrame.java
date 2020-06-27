package arrow1.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import arrow1.Handler.RegTransHandler;


public class RegFrame {
	
	public RegFrame(){
		JFrame frame = new JFrame("µÇÂ¼½çÃæ");
		frame.setSize(400,300);
		RegisterPanel registerPanel =new RegisterPanel();
		RegButtonPanel buttonPanel=new RegButtonPanel();
		new RegTransHandler(registerPanel,buttonPanel);
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(registerPanel,BorderLayout.CENTER);
		container.add(buttonPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		

}
