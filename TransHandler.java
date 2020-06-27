package arrow1.Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import arrow1.Domain.User;
import arrow1.UI.ButtonPanel;
import arrow1.UI.LoginPanel;
import arrow1.UI.RegFrame;
import arrow1.file.FileOperate;

public class TransHandler {
	private LoginPanel login;
	private ButtonPanel but;
public  TransHandler(LoginPanel loginPanel,ButtonPanel butPanel){
	this.login=loginPanel;
	this.but=butPanel;
	
	but.getExitButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		System.exit(0);
		}
	});
	but.getregButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		new	RegFrame();
		}
	});
	but.getlogButton().addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			FileOperate fileOperate = new FileOperate();
			String username = login.getUsername().trim();
			String password = login.getPassword().trim();
			
			ArrayList<User> users = fileOperate.readFile();
			User user = null;
			int flag=0;
			
			if (users!=null) {
				System.out.println("Size="+users.size());
				for (int i = 0; i < users.size(); i++) {
					user=users.get(i);
					if (user.getUsername().trim().equals(username)) {
						flag++;
						if (user.getPassword().trim().equals(password)) {
							flag++;
							break;
						}
					}
				}
			}//
			System.out.println("flag="+flag);
			if (flag==2) {
				JOptionPane.showMessageDialog(null, "欢迎登录");
			}else if (flag==1) {
				JOptionPane.showMessageDialog(null, "密码错误");
			}else {
				JOptionPane.showMessageDialog(null, "用户名不正确");
			}
		}
	});
}

}
