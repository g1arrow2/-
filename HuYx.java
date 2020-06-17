package practice6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HuYx implements ActionListener {
JTextField jtf1 = new JTextField(12);  //所有蓝字都可以改
JTextField jtf2 = new JTextField(12);  //改完注意下面的也要一致
JTextField jtf3 = new JTextField(12);
JTextField jtf4 = new JTextField(12);
JTextField jtf5 = new JTextField(12);
JFrame jf;


public HuYx(){
createGUI();
}


public static void main(String[] args) throws Exception {

	new HuYx();
}


public void actionPerformed(ActionEvent e) {
String str = e.getActionCommand();
if ("确认".equals(str)) {
User user = new User();
user.setUserName(jtf1.getText());
user.setPassWord(jtf2.getText());
user.setEmail(jtf4.getText());

}
}


public void createGUI() {
jf = new JFrame("QQ注册");

jf.addWindowListener(new WindowAdapter() {

});
jf.setSize(320,390);  //设置大小宽\高
jf.setLocation(500,200);  // 这是在窗体里的坐标
JPanel jPanel = new JPanel();

JLabel jl0 = new JLabel("QQ注册窗体");

jPanel.setLayout(null);  //不可动

jl0.setLocation(90, 0);  //同上
jl0.setSize(90,70);
jPanel.add(jl0);

JLabel jl1 = new JLabel("账号:");
jl1.setLocation(10, 35);
jl1.setSize(50,40);
jtf1.setLocation(55, 45);
jtf1.setSize(220, 25);   //同
jPanel.add(jl1);jPanel.add(jtf1);

JLabel jl2 = new JLabel("密码:");
jl2.setLocation(10, 75);
jl2.setSize(50,40);
jtf2.setLocation(55, 85);
jtf2.setSize(220, 25);
jPanel.add(jl2);jPanel.add(jtf2);

JLabel jl3 = new JLabel("昵称:");
jl3.setLocation(10, 125);
jl3.setSize(50,40);
jtf3.setLocation(55, 135);
jtf3.setSize(220, 25);
jPanel.add(jl3);jPanel.add(jtf3);

JLabel jl5 = new JLabel("性别");
JComboBox<String> comboBox=new JComboBox<String>();
String items[] = {"女", "男"};
ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(items);
comboBox.setModel(comboBoxModel);   //不用动

jl5.setLocation(10, 175);
jl5.setSize(50,40);
comboBox.setLocation(55, 175);
comboBox.setSize(222, 30);
jPanel.add(jl5);jPanel.add(comboBox);

JLabel jl4 = new JLabel("E-mail:");
jl4.setLocation(10, 215);
jl4.setSize(50,40);
jtf4.setLocation(55, 225);
jtf4.setSize(220, 25);
jPanel.add(jl4);jPanel.add(jtf4);


JLabel jl6 = new JLabel("地址:");
jl6.setLocation(10, 255);
jl6.setSize(50,40);
jtf5.setLocation(55, 265);
jtf5.setSize(220, 25);
jl6.setForeground(Color.green);  //可以用来改字体颜色
jPanel.add(jl6);jPanel.add(jtf5);

JButton jb51 = new JButton("确认");
jb51.addActionListener(this);
jb51.setLocation(30, 305);
jb51.setSize(60,35);
jb51.setBackground(Color.green);   //设置按钮的背景色
jb51.setForeground(Color.WHITE);   //设置按钮的前置色，也可以说是字体
jb51.setBorderPainted(false);

jPanel.add(jl3);jPanel.add(jtf3);

JButton jb52 = new JButton("取消");
jb52.addActionListener(this);
jb52.setLocation(160, 305);
jb52.setSize(60,35);
jb52.setBackground(Color.RED);
jb52.setForeground(Color.white);
jb52.setBorderPainted(false);
jPanel.add(jb51);jPanel.add(jb52);

jb51.addActionListener((event) -> {  //这里的数学逻辑是很有漏洞的
		
	if (jtf2.getText().trim().length() != 0) {
			JOptionPane("成功");
				jtf2.setText("********");
			}else if(jtf2.getText().trim().length() == 0){
				jtf2.setText("密码不能为空，请重新输入密码");
	}
	if (jtf1.getText().trim().length() != 0) {
		JOptionPane("成功");
	}else if(jtf1.getText().trim().length() == 0){
		jtf1.setText("账号不能为空，请重新输入账号");
}});
jb52.addActionListener((event) -> {
	System.exit(0);   //点击后退出不要改
	});

jf.setContentPane(jPanel);
jf.setVisible(true);
}


public static void JOptionPane(String message) {
	JOptionPane.showConfirmDialog(null,message,"成功",JOptionPane.DEFAULT_OPTION);  //弹出对话框
}
}