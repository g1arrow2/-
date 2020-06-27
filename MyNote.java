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
		super("无标题-文本编辑器");
		actionListener =new MenuListener(this);
		this.setJMenuBar(createMenuBar());
	
		Container container = this.getContentPane();
		editFrame = new TextEditFrame();
		this.textArea = editFrame.getTextEdit();
		//这里很关键！！！
		
		container.setLayout(new BorderLayout());
		container.add(createToolBar(),BorderLayout.NORTH);
		container.add(editFrame,BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setVisible(true);
	}
	private JToolBar createToolBar() {
		String[] fileTitle = {"新建","打开","保存","另存为"};
		String[] editTitle = {"剪切","拷贝","粘贴"};
			
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
		String[] fileMenuTiele = {"新建","打开","Separator","保存","另存为","Separator","退出"};
		String[] editMenuTitle = {"剪切","拷贝","粘贴"};
		String[] helpMenuTitle = {"关于"};
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = null;
		
		menu = createMenu(fileMenuTiele, "文件(E)");
		menuBar.add(menu);
		menu=createMenu(editMenuTitle, "编辑(F)");
		menuBar.add(menu);
		menu=createMenu(helpMenuTitle, "帮助(H)");
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
