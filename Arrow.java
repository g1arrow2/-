package arrow1.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;


import arrow1.Handler.*;

public class Arrow {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("µÇÂ¼½çÃæ");
		frame.setSize(250,150);
		LoginPanel loginPanel=new LoginPanel();
		ButtonPanel buttonPanel=new ButtonPanel();
		new TransHandler(loginPanel,buttonPanel);  ///!!!
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(loginPanel,BorderLayout.CENTER);
		container.add(buttonPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
