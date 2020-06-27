package practice6;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
public class MemberRegistration111 extends JFrame {

	private static final long serialVersionUID = 1L;
	public MemberRegistration111() {

		Label name = new Label("姓名");  //创建一个标签
		name.setBackground(Color.pink); //标签的颜色设置为粉红色
		Label pwd = new Label("密码");
		Label pwdAgain = new Label("再次输入\n密码");
		pwdAgain.setBackground(Color.pink);
		pwd.setBackground(Color.pink);
		JTextField username = new JTextField(10);//创建一个普通文本输入框
		JPasswordField userPwd = new JPasswordField(10);//创建一个密码输入框,这个组件自动隐藏密码
		JPasswordField userPwdAgain = new JPasswordField(15);//创建再次输入密码框
		JPanel one = new JPanel(new FlowLayout(6, 10, 10)); //创建面板,并设置布局
		
		one.add(name);//将组件逐个添加至面板
		one.add(username);
		one.add(pwd);
		one.add(userPwd);
		one.add(pwdAgain);
		one.add(userPwdAgain);

		Label sex = new Label("性别");
		sex.setBackground(Color.pink);
		JCheckBox man = new JCheckBox("男"); //设置复选框
		JCheckBox women = new JCheckBox("女"); 
		ButtonGroup group = new ButtonGroup(); //设置按钮组
		group.add(man); //将man和women两个按钮捆绑在一起,这样就只能选择其中的一个
		group.add(women);
		
		one.add(sex);
		one.add(man);
		one.add(women);

		Label agree = new Label("同意注册协议");
		agree.setBackground(Color.pink);
		JRadioButton rb1 = new JRadioButton("是"); //创建单选框
		JRadioButton rb2 = new JRadioButton("否");
		ButtonGroup rbgroup = new ButtonGroup();
		rbgroup.add(rb1);
		rbgroup.add(rb2);
		one.add(agree);
		one.add(rb1);
		one.add(rb2);

		Label Grade = new Label("年级");
		Grade.setBackground(Color.pink);
		String[] grade = { "大一", "大二", "大三", "大四" };
		JComboBox<String> jcb = new JComboBox<String>(grade);
		jcb.setPreferredSize(new Dimension(100, 20));
		one.add(Grade);
		one.add(jcb);
		JButton ok = new JButton("确认");
		one.add(ok);
		ok.addActionListener((event) -> {
			if (username.getText().trim().length() == 0) {
				errorDialog("用户名不能为空,请填写用户名");
				return;

			}
			String reg = "^[\u4E00-\u9FA5A-Za-z]+$"; // 正则表达式判断输入的用户名是否合法
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(username.getText().trim());
			if (!m.matches()) {
				errorDialog("用户名不合法,请重新输入");
				return;
			}
			if (new String(userPwd.getPassword()).trim().length() == 0) {
				errorDialog("密码不能为空,请设置密码");
				return;
			}
			if (new String(userPwdAgain.getPassword()).trim().length() == 0) 			{
				errorDialog("请再次输入您的密码");
				return;
			}
			if (!new String(userPwd.getPassword()).trim().toString()
					.equals(new String(userPwdAgain.getPassword()).trim().toString())) {
				errorDialog("两次密码输入不一致,请重新输入");
				userPwd.setText("");
				userPwdAgain.setText("");
				return;
			}
			if (!man.isSelected() && !women.isSelected()) {
				errorDialog("请选择性别");
				return;
			}
			if (!rb1.isSelected()) {
				errorDialog("请同意用户协议,否则无法完成注册");
				return;
			}
			this.dispose();
			JFrame frame1 = new JFrame("注册成功");

			frame1.setLocation(500, 200);
			frame1.setSize(300, 275);
			Container C = frame1.getContentPane();
			JPanel success = new Mypanel("icon2.png");
			Label info = new Label("注册信息");
			JTextArea content = new JTextArea();
			content.setBackground(new Color(225, 255, 255)); //
			content.setAutoscrolls(true);
			content.setPreferredSize(new Dimension(215, 75));
			content.setLineWrap(true);
			content.append("姓名:" + username.getText() + "\n");
			content.append("性别:" + (man.isSelected() == true ? "男" : "女") + "\n");
			content.append(grade[jcb.getSelectedIndex()]);
			content.setEditable(false);
			JButton confirm = new JButton("确认");
			confirm.addActionListener((e) -> {
				confirm.setBackground(new Color(255, 218, 185));
				frame1.dispose();
			});
			success.add(info);
			success.add(content);
			success.add(confirm);
			C.add(success);
			frame1.setVisible(true);

		});
		JPanel jpanel = new Mypanel("icon.png");
		jpanel.setBackground(null);
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jpanel, one); //使用分隔面板
		jSplitPane.setOneTouchExpandable(true);
		jSplitPane.setDividerSize(1);
		Container c = getContentPane();
		c.add(jSplitPane);
		this.setLocation(500, 200);
		setSize(400, 350);
		jSplitPane.setDividerLocation(175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIcon();
		setVisible(true);
	}

	private void setIcon() { //重新设置窗体的图标
		BufferedImage image = null;
		try {
			System.out.println("从"+this.getClass().getResource("")+"获取图片");
			image = ImageIO.read(this.getClass().getResource("frame.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(image);
	}
public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "警告", JOptionPane.ERROR_MESSAGE); //弹出对话框
	}

	public static void main(String[] args) {
		new MemberRegistration111();

	}
}

