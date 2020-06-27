package arrow1.Handler;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import arrow1.Domain.User;
import arrow1.UI.RegButtonPanel;
import arrow1.UI.RegisterPanel;
import arrow1.file.FileOperate;


public class RegTransHandler
{
private RegisterPanel regPanel;
private RegButtonPanel regButPanel;

public RegTransHandler (RegisterPanel regPanell, RegButtonPanel regButPanell) 
{
	this.regPanel=regPanell;
	this.regButPanel=regButPanell;
	
	regButPanel.getCancelButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
	regPanel.clearFields();
	}
			});
	regButPanel.getSubmitButton().addActionListener(new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) 
		{
			
		FileOperate fileoper = new FileOperate ( );
		
		String username = regPanel.getUsername();
		String password = regPanel.getPassword () ;
		String password2 = regPanel.getPassword2 () ;
		System.out.println(password+"/"+password2);
		String sex = regPanel.getSex() ;
		String email = regPanel.getEmail().trim ( ) ;
		if(!password.equals(password2))
		{
		JOptionPane.showMessageDialog(null,"两次输入的密码不一致");
		return;
		}
		User user = new User (username, password, sex, email);
		
		ArrayList<User>users=fileoper.readFile();
		
		if(users!=null)
		{
		System.out.println(2);
		for(int i=0;i< users.size();i++)
		{
		User temp = users . get (i) ;
		if(temp.getUsername().equals(username))
		{
		JOptionPane.showMessageDialog(null,"用户名已存在");
		return;
		}
		}
		}
		if (users== null)
		{
		users = new ArrayList< User>();
		}
		users.add (user);
		fileoper.writeFile(users);
		JOptionPane.showMessageDialog(null,"注册成功");
		}
		});
	}
}
