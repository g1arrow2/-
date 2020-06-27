package arrow2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class MyNote extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea = null;
	private ActionListener actionListener=null;
	TextEditFrame editFrame;
	public MyNote() 
	{
		super("�ޱ���-�ı��༭��");
		actionListener =new MenuListener(this);
		this.setJMenuBar(createMenuBar());
	
		Container container = this.getContentPane();
		editFrame = new TextEditFrame();
		this.textArea = editFrame.getTextEdit();
		//����ܹؼ�������
		
		container.setLayout(new BorderLayout());
		container.add(createToolBar(),BorderLayout.NORTH);
		container.add(editFrame,BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setVisible(true);
	}
	private JToolBar createToolBar() {
		String[] fileTitle = {"�½�","��","����","���Ϊ"};
		String[] editTitle = {"����","����","ճ��"};
			
		JToolBar toolBar = new JToolBar();
		JButton button = null;
		for(int i = 0;i<fileTitle.length;i++) {
			button = new JButton(fileTitle[i]);
			button.addActionListener(actionListener);
			toolBar.add(button);
}toolBar.addSeparator();
for (int i = 0; i < editTitle.length; i++) {
	button = new JButton(editTitle[i]);
	button.addActionListener(actionListener);
	toolBar.add(button);
}toolBar.setVisible(true);
		return toolBar;
	}
	private JMenuBar createMenuBar() {
		String[] fileMenuTiele = {"�½�","��","Separator","����","���Ϊ","Separator","�˳�"};
		String[] editMenuTitle = {"����","����","ճ��"};
		String[] helpMenuTitle = {"����"};
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = null;
		
		menu = createMenu(fileMenuTiele, "�ļ�(E)");
		menuBar.add(menu);
		menu=createMenu(editMenuTitle, "�༭(F)");
		menuBar.add(menu);
		menu=createMenu(helpMenuTitle, "����(H)");
		menuBar.add(menu);
		
		return menuBar;

	}

	private JMenu createMenu(String[] menuItems,String menuTitle) 
	{   JMenu menu = new JMenu();
		JMenuItem menuItem = null;
		
		for (int i = 0; i < menuItems.length; i++) {
			if(menuItems[i].equals("Separator"))
			{menu.addSeparator();
		}else {
			menuItem = new JMenuItem();
			menuItem.setText(menuItems[i]);
			menuItem.addActionListener(actionListener);
			menu.add(menuItem);
		}
		}
		menu.setText(menuTitle);
		return menu;
	}

	public static void main(String[] args) {
	new MyNote();
	}

	public JTextArea getTextArea() {
		
		return this.textArea;
	}

}
