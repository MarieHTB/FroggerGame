import javax.swing.*;

public class Frog extends Actor {
	
	public Frog(){
		this.setBounds(0,0,HEIGHT,HEIGHT);
		this.setIcon(new ImageIcon("frogger_frog.png"));
	}
	
	public void moveRight(){
		this.setLocation(this.getX() + 40, this.getY());
	}
	
	public void moveLeft(){
		this.setLocation(this.getX() - 40, this.getY());
	}
	
	public void moveUp(){
		this.setLocation(this.getX(), this.getY() - 45);
	}

	public void moveDown(){
		this.setLocation(this.getX(), this.getY() + 45);
	}
}
