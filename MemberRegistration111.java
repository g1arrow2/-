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

		Label name = new Label("����");  //����һ����ǩ
		name.setBackground(Color.pink); //��ǩ����ɫ����Ϊ�ۺ�ɫ
		Label pwd = new Label("����");
		Label pwdAgain = new Label("�ٴ�����\n����");
		pwdAgain.setBackground(Color.pink);
		pwd.setBackground(Color.pink);
		JTextField username = new JTextField(10);//����һ����ͨ�ı������
		JPasswordField userPwd = new JPasswordField(10);//����һ�����������,�������Զ���������
		JPasswordField userPwdAgain = new JPasswordField(15);//�����ٴ����������
		JPanel one = new JPanel(new FlowLayout(6, 10, 10)); //�������,�����ò���
		
		one.add(name);//����������������
		one.add(username);
		one.add(pwd);
		one.add(userPwd);
		one.add(pwdAgain);
		one.add(userPwdAgain);

		Label sex = new Label("�Ա�");
		sex.setBackground(Color.pink);
		JCheckBox man = new JCheckBox("��"); //���ø�ѡ��
		JCheckBox women = new JCheckBox("Ů"); 
		ButtonGroup group = new ButtonGroup(); //���ð�ť��
		group.add(man); //��man��women������ť������һ��,������ֻ��ѡ�����е�һ��
		group.add(women);
		
		one.add(sex);
		one.add(man);
		one.add(women);

		Label agree = new Label("ͬ��ע��Э��");
		agree.setBackground(Color.pink);
		JRadioButton rb1 = new JRadioButton("��"); //������ѡ��
		JRadioButton rb2 = new JRadioButton("��");
		ButtonGroup rbgroup = new ButtonGroup();
		rbgroup.add(rb1);
		rbgroup.add(rb2);
		one.add(agree);
		one.add(rb1);
		one.add(rb2);

		Label Grade = new Label("�꼶");
		Grade.setBackground(Color.pink);
		String[] grade = { "��һ", "���", "����", "����" };
		JComboBox<String> jcb = new JComboBox<String>(grade);
		jcb.setPreferredSize(new Dimension(100, 20));
		one.add(Grade);
		one.add(jcb);
		JButton ok = new JButton("ȷ��");
		one.add(ok);
		ok.addActionListener((event) -> {
			if (username.getText().trim().length() == 0) {
				errorDialog("�û�������Ϊ��,����д�û���");
				return;

			}
			String reg = "^[\u4E00-\u9FA5A-Za-z]+$"; // ������ʽ�ж�������û����Ƿ�Ϸ�
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(username.getText().trim());
			if (!m.matches()) {
				errorDialog("�û������Ϸ�,����������");
				return;
			}
			if (new String(userPwd.getPassword()).trim().length() == 0) {
				errorDialog("���벻��Ϊ��,����������");
				return;
			}
			if (new String(userPwdAgain.getPassword()).trim().length() == 0) 			{
				errorDialog("���ٴ�������������");
				return;
			}
			if (!new String(userPwd.getPassword()).trim().toString()
					.equals(new String(userPwdAgain.getPassword()).trim().toString())) {
				errorDialog("�����������벻һ��,����������");
				userPwd.setText("");
				userPwdAgain.setText("");
				return;
			}
			if (!man.isSelected() && !women.isSelected()) {
				errorDialog("��ѡ���Ա�");
				return;
			}
			if (!rb1.isSelected()) {
				errorDialog("��ͬ���û�Э��,�����޷����ע��");
				return;
			}
			this.dispose();
			JFrame frame1 = new JFrame("ע��ɹ�");

			frame1.setLocation(500, 200);
			frame1.setSize(300, 275);
			Container C = frame1.getContentPane();
			JPanel success = new Mypanel("icon2.png");
			Label info = new Label("ע����Ϣ");
			JTextArea content = new JTextArea();
			content.setBackground(new Color(225, 255, 255)); //
			content.setAutoscrolls(true);
			content.setPreferredSize(new Dimension(215, 75));
			content.setLineWrap(true);
			content.append("����:" + username.getText() + "\n");
			content.append("�Ա�:" + (man.isSelected() == true ? "��" : "Ů") + "\n");
			content.append(grade[jcb.getSelectedIndex()]);
			content.setEditable(false);
			JButton confirm = new JButton("ȷ��");
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
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jpanel, one); //ʹ�÷ָ����
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

	private void setIcon() { //�������ô����ͼ��
		BufferedImage image = null;
		try {
			System.out.println("��"+this.getClass().getResource("")+"��ȡͼƬ");
			image = ImageIO.read(this.getClass().getResource("frame.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setIconImage(image);
	}
public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "����", JOptionPane.ERROR_MESSAGE); //�����Ի���
	}

	public static void main(String[] args) {
		new MemberRegistration111();

	}
}

