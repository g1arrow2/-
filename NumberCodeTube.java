package arrow3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class NumberCodeTube {
	private static int [][] displayCodes= {
			{1,1,1,1,1,1,0},
			{0,1,1,0,0,0,0},
			{1,1,0,1,1,0,1},
			{1,1,1,1,0,0,1},
			{0,1,1,0,0,1,1},
			{1,0,1,1,0,1,1},
			{1,0,1,1,1,1,1},
			{1,1,1,0,0,0,0,},
			{1,1,1,1,1,1,1},
			{1,1,1,1,0,1,1},	
	};
	private int displayedNumber;
	private int lineLength;
	private int posX;
	private int posY;
	private Line2D.Double[]lineArray;
	
	
	public NumberCodeTube() {
	this(0,10,0,0);
}


	public NumberCodeTube(int posX,int posY) {
		this(0,10,posX,posY);
	}
	
	
	public NumberCodeTube(int lineLength,int posX,int posY) {
		this(0,lineLength,posX,posY);
	}

	
	public NumberCodeTube(int displayedNumber, int lineLength, int posX, int posY) {
		this.displayedNumber=displayedNumber;
		this.lineLength=lineLength;
		this.posX=posX+20;
		this.posY=posY+20;
		makeLineaArray();
	}

	
public void setDisplayNumber(int displayedNumber) {
	this.displayedNumber=displayedNumber;
}
	

	private void makeLineaArray() {
		this.lineArray = new Line2D.Double[7];
		
		lineArray[0]=new Line2D.Double(posX,posY,posX+lineLength,posY);
		lineArray[1]= new Line2D.Double(posX+lineLength,posY,posX+lineLength,posY+lineLength);
		lineArray[2]= new Line2D.Double(posX+ lineLength, posY+ lineLength, posX+lineLength, posY+ 2* lineLength);
		lineArray[3]= new Line2D. Double (posX+ lineLength, posY+ 2* lineLength, posX, posY+ 2* lineLength);
		lineArray[4]= new Line2D.Double(posX,posY+2*lineLength,posX,posY+lineLength);
		lineArray[5]=new Line2D.Double(posX,posY+lineLength,posX,posY);
		lineArray[6]= new Line2D. Double (posX, posY+ lineLength, posX+ lineLength, posY+ lineLength);
		}
	
	public void draw(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		
		for (int i = 0; i < displayCodes[displayedNumber].length;i++) {
			if (displayCodes[displayedNumber][i]==1) {  //等于1才画线懂了
				g2.draw(lineArray[i]);
			}
		}
	}
}


