package practice6;
import javax.swing.*;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Calculator extends JFrame implements ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] KEYS={"C","/","%","+","7","8","9","-","4","5","6","*","1","2","3",".","(","0",")","="};
    private JButton keys[]=new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");
    private String b="";
    public Calculator()
    {
        super("计算器");
        setIconImage(Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("喜爱.png")));
        this.setLayout(null);
        resultText.setBounds(20, 15, 260, 46);
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setBackground(Color.WHITE);
        resultText.setEditable(false);
        this.add(resultText);
        int x=20,y=72;
        for (int i=0;i<KEYS.length;i++)
        {
            keys[i] = new JButton();
            keys[i].setText(KEYS[i]);
            keys[i].setBounds(x, y, 62, 42);
        if(x<215)
            {
                x+=65;
            }
        else
            {
                x = 20;
                y+=45;
            }
            this.add(keys[i]);
        }
        for (int i = 0; i <KEYS.length; i++) 
        {
            keys[i].addActionListener(this);
            keys[i].setBackground(Color.white);
            keys[i].setBorderPainted(false);
        }
        keys[0].setForeground(Color.ORANGE);
        keys[0].setBackground(Color.WHITE);
        keys[1].setForeground(Color.RED);
        keys[2].setForeground(Color.RED);
        keys[3].setForeground(Color.RED);
        keys[7].setForeground(Color.RED);
        keys[11].setForeground(Color.RED);
        keys[15].setForeground(Color.RED);
        keys[16].setForeground(Color.BLUE);
        keys[18].setForeground(Color.BLUE);
        keys[19].setForeground(Color.white);
        keys[19].setBackground(Color.orange);
     
 
        this.setResizable(false);
        this.setBounds(500, 200, 310, 360);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        String label = e.getActionCommand(); 
        if (label=="C"||label=="=")
        {
            if(label=="=")
            {
                String s[]=yunsuan(this.b);
                String result=Result(s);
                this.b=result+"";
                resultText.setText(this.b);
            }
            else
            {
                this.b="";
                resultText.setText("0");
            }
        }
     
        else
        {
            this.b=this.b+label;
            resultText.setText(this.b);
        }
    }
    private String[] yunsuan(String str)
	{
		String s="";
		char a[]=new char[100];
		String jieguo[]=new String[100];
		int top=-1,j=0;
		for (int i=0;i<str.length();i++)
		{
			if ("0123456789.".indexOf(str.charAt(i))>=0)
			{
				s="";
                for (;i<str.length()&&"0123456789.".indexOf(str.charAt(i))>=0;i++)
                {
                    s=s+str.charAt(i);
                }
                i--;
                jieguo[j]=s;
                j++;
			}
			else if ("(".indexOf(str.charAt(i))>=0)
			{
				top++;
				a[top]=str.charAt(i);
			}
			else if (")".indexOf(str.charAt(i))>=0)
			{
				for (;;)
				{
					if (a[top]!='(')
					{
						jieguo[j]=a[top]+"";
						j++;
						top--;
					}
					else
					{
						top--;
						break;
					}
				}
			}
			else if ("*%÷".indexOf(str.charAt(i))>=0)
			{
				if (top==-1)
				{
					top++;
					a[top]=str.charAt(i);
				}
				else
				{
					if ("*%÷".indexOf(a[top])>=0)
					{
						jieguo[j]=a[top]+"";
						j++;
						a[top]=str.charAt(i);
					}
					else if ("(".indexOf(a[top])>=0)
					{
						top++;
						a[top]=str.charAt(i);
					}
					else if ("+-".indexOf(a[top])>=0)
					{
						top++;
						a[top]=str.charAt(i);
					}
				}
			}
			else if ("+-".indexOf(str.charAt(i))>=0)
			{
				if (top==-1)
				{
					top++;
					a[top]=str.charAt(i);
				}
				else
				{
					if ("%*÷".indexOf(a[top])>=0)
					{
						jieguo[j]=a[top]+"";
						j++;
						a[top]=str.charAt(i);
					}
					else if ("(".indexOf(a[top])>=0)
					{
						top++;
						a[top]=str.charAt(i);
					}
					else if ("+-".indexOf(a[top])>=0)
					{
						jieguo[j]=a[top]+"";
						j++;
						a[top]=str.charAt(i);
					}
				}
			}
		}
		for (;top!=-1;)
		{
			jieguo[j]=a[top]+"";
			j++;
			top--;
		}
		return jieguo;
	}
    public String yunsuan2(String str)
    {
        String result="";
        double a=Double.parseDouble(str),b=0;
        b=Math.sqrt(a);
        result=String.valueOf(b);
        return result;
    }
    public String yunsuan3(String str)
    {
        String result="";
        double a=Double.parseDouble(str),b=0;
        b=Math.pow(a, 2);
        result=String.valueOf(b);
        return result;
    }
    public String Result(String str[])
    {
        String Result[]=new String[100];
        int Top=-1;
        for (int i=0;str[i]!=null;i++)
        {
            if ("+-*%÷".indexOf(str[i])<0)
            {
                Top++;
                Result[Top]=str[i];
            }
            if ("+-*%÷".indexOf(str[i])>=0)
            {
                double x,y,n;
                x=Double.parseDouble(Result[Top]);
                Top--;
                y=Double.parseDouble(Result[Top]);
                Top--;
                if ("-".indexOf(str[i])>=0)
                {
                    n=y-x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }
                if ("+".indexOf(str[i])>=0)
                {
                    n=y+x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }
                if ("*".indexOf(str[i])>=0)
                {
                    n=y*x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }
                if ("÷".indexOf(str[i])>=0)
                {
                    if (x==0)
                    {
                        String s="ERROR";
                        return s;
                    }
                    else
                    {
                        n=y/x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                }
                if ("%".indexOf(str[i])>=0)
                {
                    if (x==0)
                    {
                        String s="ERROR";
                        return s;
                    }
                    else
 
                    {
                        n=y%x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                }
            }
        }
        return Result[Top];
    }
    public static void main(String arg[])
    {
       new Calculator();
    }
}
