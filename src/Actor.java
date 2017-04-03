import javax.swing.*;

public class Actor extends JLabel {
	
	//width = 40 pxl
	//5 pxl between roads
	//width 0 to 600 pxl
	
	protected boolean dir;
	protected int lenght;
	protected final int HEIGHT = 40;
	protected double speed = 1;
	
	public boolean getDirection(){
		return this.dir;
	}
	
	public void setDirection(boolean d){
		this.dir = d;
		//dir = false means left
		//dir = true means right
	}
	
	public int[][] getLocat(){
		int[][] P = new int[4][2];
		
		//Ax
		P[0][0] = this.getX();
		
		//Ay
		P[0][1] = this.getY();
		
		//Bx
		P[1][0] = this.getX()+this.getWidth(); //+this.getWidth()?
		
		//By
		P[1][1] = this.getY();
		
		//Cx
		P[2][0] = this.getX();
		
		//Cy
		P[2][1] = this.getY()+40; //+this.getLenght()?
		
		//Dx
		P[3][0] = this.getX()+this.getWidth();
		
		//Dy
		P[3][1] = this.getY()+40;
		return P;
	
	}
	
	public void setLocat(int x, int y) {
		
	}
	
	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}
	
	public void moveTo(){
		if(dir)
			this.setLocation(0 - this.lenght,this.getY());
		else
			this.setLocation(600 + this.lenght,this.getY());
	}
	
	public void act(){

		if(dir){
			if(this.getX() >= 600)
				this.moveTo();
			else
				this.setLocation((int)(this.getX() + 5 * this.speed), this.getY());
		}else{
			if(this.getX() - lenght <= 0)
				this.moveTo();
			else
				this.setLocation((int)(this.getX() - 5 * this.speed), this.getY());
		}
	}
}
