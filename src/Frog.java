import javax.swing.*;

public class Frog extends Actor {
	
	public Frog(){
		this.setBounds(0,0,HEIGHT,HEIGHT);
		this.setIcon(new ImageIcon("frogger_frog.png"));
	}
	
	public void moveRight(){
		if ((this.getX() + 60) > 600) {

		} 
		
		else {
			this.setLocation(this.getX() + 20, this.getY());
		}
	}
	
	public void moveLeft(){
		if ((this.getX() - 20) < 0) {

		} 
		
		else {
			this.setLocation(this.getX() - 20, this.getY());
		}
	}
	
	public void moveUp(){
		this.setLocation(this.getX(), this.getY() - 45);
	}

	public void moveDown(){
		if ((this.getY() +85) > 580){
			
		} else {
		this.setLocation(this.getX(), this.getY() + 45);
                }
	}
}
