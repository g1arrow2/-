package arrow2;


import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MenuListener implements ActionListener {
	private MyNote frame=null;
	private String path = null;
	
	public MenuListener(MyNote frame) {
		this.frame= frame;
	}
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("新建"))
		{
			frame.setTitle("新建-文本编辑器");
			frame.getTextArea().setText("");
			frame.getTextArea().setCaretPosition(0);
			path= null;
		}
		else if (e.getActionCommand().equals("打开")) 
		{
			FileDialog fileDialog = new FileDialog(frame,"打开",FileDialog.LOAD);
			fileDialog.setVisible(true);
			String directory =fileDialog.getDirectory();
			String fileName = fileDialog.getFile();
			
			if (directory!=null && fileName!=null) {
				path=directory+fileName;
			}
			if (path!=null) {
				frame.setTitle(fileName+"-文本编辑器");
				frame.getTextArea().setText(openFile(path));
				frame.getTextArea().setCaretPosition(0);
		
			
			}
		}else if (e.getActionCommand().equals("保存")) {
			savaFile();
		}
		else if (e.getActionCommand().equals("另存为")) {
			savaAsFile();
		}
		else if (e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
		else if (e.getActionCommand().equals("剪切")) 
		{
			JTextArea textArea = frame.getTextArea();
			ClipBoard.setCilpBoard(textArea.getSelectedText());
			
			int start = textArea.getSelectionStart();
			int end = textArea.getSelectionEnd();
			String textAreaContent = textArea.getText();
			
			textAreaContent= textAreaContent.substring(0,start)+textAreaContent.substring(end);
			textArea.setText(textAreaContent);
			frame.getTextArea().setCaretPosition(start);
		}
		else if (e.getActionCommand().equals("拷贝"))
		{
			JTextArea textArea=frame.getTextArea();
			
			ClipBoard.setCilpBoard(textArea.getSelectedText());
			
			frame.getTextArea().setCaretPosition(textArea.getSelectionStart());
		
		}
		else if (e.getActionCommand().equals("粘贴"))
		{	
			if (ClipBoard.getCilpBoard()!=null)
			{
				JTextArea textArea= frame.getTextArea();
				
				int start = textArea.getSelectionStart();
				int end = textArea.getSelectionEnd();
				String textAreaContent = textArea.getText();
				
				textAreaContent= textAreaContent.substring(0,start)+ClipBoard.getCilpBoard()+textAreaContent.substring(end);
				textArea.setText(textAreaContent);
				
				frame.getTextArea().setCaretPosition(start);
			
			}
		}
			else if (e.getActionCommand().equals("关于")) {
				String showMessage = "建建文本编辑器v1.0";
				JOptionPane.showMessageDialog(frame,showMessage,"消息",JOptionPane.INFORMATION_MESSAGE);
			}
		}

	private void savaAsFile() {
		FileDialog fileDialog = new FileDialog(frame,"保存",FileDialog.SAVE);
		fileDialog.setVisible(true);
		
		String dString = fileDialog.getDirectory();
		String fiString = fileDialog.getFile();
		
		if (dString!= null&&fiString!= null) {
			path = dString+fiString;
			writerFile(path);
			frame.setTitle(fiString+"-文本编辑器");
		}
		
	}
	private void savaFile() {
		if (path!=null) {
			writerFile(path);
		}else {
			savaAsFile();
		}
	}
	private void writerFile(String path) {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(path));
			bWriter.append(frame.getTextArea().getText());
			bWriter.flush();
			bWriter.close();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	private String openFile(String path) {
		BufferedReader bReader= null;
		String aLine = null;
		StringBuffer sBuffer = null;
		
		try {
			bReader = new BufferedReader(new FileReader(path));
			sBuffer = new StringBuffer();
			
			while ((aLine=bReader.readLine())!=null) {
			sBuffer.append(aLine);
			sBuffer.append("\n");
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		return sBuffer.toString();
	}
	
}
