package practice6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.*;

public class MemberRegistration implements ActionListener {

JTextField jtf1 = new JTextField(12);
JTextField jtf2 = new JTextField(12);
JTextField jtf3 = new JTextField(12);
JTextField jtf4 = new JTextField(12);
JTextField jtf5 = new JTextField(12);
JFrame jf;

public MemberRegistration(){
createGUI();
}

public void actionPerformed(ActionEvent e) {
String str = e.getActionCommand();
if ("ȷ��".equals(str)) {
User user = new User();
user.setUserName(jtf1.getText());
user.setPassWord(jtf2.getText());
user.setEmail(jtf4.getText());

}
}
public void createGUI() {
jf = new JFrame("QQע��");

jf.addWindowListener(new WindowAdapter() {

});
jf.setSize(280,390);
jf.setLocation(500,200);
JPanel jPanel = new JPanel();

JLabel jl0 = new JLabel("QQע�ᴰ��");

jPanel.setLayout(null);

jl0.setLocation(90, 0);
jl0.setSize(90,40);
jPanel.add(jl0);

JLabel jl1 = new JLabel("��   ��:");
jl1.setLocation(10, 35);
jl1.setSize(50,40);
jtf1.setLocation(55, 45);
jtf1.setSize(170, 25);
jPanel.add(jl1);jPanel.add(jtf1);

JLabel jl2 = new JLabel("��   ��:");
jl2.setLocation(10, 75);
jl2.setSize(50,40);
jtf2.setLocation(55, 85);
jtf2.setSize(170, 25);
jPanel.add(jl2);jPanel.add(jtf2);

JLabel jl3 = new JLabel("��   ��:");
jl3.setLocation(10, 125);
jl3.setSize(50,40);
jtf3.setLocation(55, 135);
jtf3.setSize(170, 25);
jPanel.add(jl3);jPanel.add(jtf3);

JLabel jl5 = new JLabel("��    ��");
JComboBox<String> comboBox=new JComboBox<String>();
String items[] = {"Ů", "��"};
ComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(items);
comboBox.setModel(comboBoxModel);

jl5.setLocation(10, 175);
jl5.setSize(50,40);
comboBox.setLocation(55, 185);
comboBox.setSize(50, 30);
jPanel.add(jl5);jPanel.add(comboBox);

JLabel jl7 = new JLabel("��֤��:");
jl7.setLocation(115, 175);
jl7.setSize(50,40);
jPanel.add(jl7);
JLabel jl8 = new JLabel("��װ��ͼƬ");
jl8.setLocation(155, 165);
jl8.setSize(70,60);
jPanel.add(jl8);

JLabel jl4 = new JLabel("E-mail:");
jl4.setLocation(10, 215);
jl4.setSize(50,40);
jtf4.setLocation(55, 225);
jtf4.setSize(170, 25);
jPanel.add(jl4);jPanel.add(jtf4);



JLabel jl6 = new JLabel("��    ַ:");
jl6.setLocation(10, 255);
jl6.setSize(50,40);
jtf5.setLocation(55, 265);
jtf5.setSize(170, 25);
jPanel.add(jl6);jPanel.add(jtf5);


JButton jb51 = new JButton("ȷ��");
jb51.addActionListener(this);
jb51.setLocation(30, 305);
jb51.setSize(60,35);
jb51.setBackground(Color.green);
jb51.setForeground(Color.WHITE);
jb51.setBorderPainted(false);

jPanel.add(jl3);jPanel.add(jtf3);

JButton jb52 = new JButton("ȡ��");
jb52.addActionListener(this);
jb52.setLocation(160, 305);
jb52.setSize(60,35);
jb52.setBackground(Color.RED);
jb52.setForeground(Color.white);
jb52.setBorderPainted(false);
jPanel.add(jb51);jPanel.add(jb52);

jb51.addActionListener((event) -> {
		if (jtf2.getText().trim().length() != 0&&(jtf1.getText().trim().length() != 0)) {
			JOptionPane("ע��ɹ�");
				jtf2.setText("******");
				JOptionPane("ע��ɹ�");
				String string = jtf3.getText().toString();
				jf.setTitle("��ӭ�� ��" + string);
					return;
			}else if(jtf2.getText().trim().length() == 0||jtf1.getText().trim().length() == 0){
				jl2.setText("����");
				jtf2.setText("����������");
				jl1.setText("����");
				jf.setTitle("ע��ʧ��,������ " );
	}});
jb52.addActionListener((event) -> {
	System.exit(0);
	});
jf.setContentPane(jPanel);
jf.setVisible(true);
}
public static void JOptionPane(String message) {
	JOptionPane.showConfirmDialog(null,message,"��ϲ",JOptionPane.DEFAULT_OPTION);  //�����Ի���
}
public static void main(String[] args) throws Exception {

	new MemberRegistration();
}
}