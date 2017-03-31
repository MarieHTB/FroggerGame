import javax.swing.ImageIcon;

public class Car extends Actor {

	public Car(){
		this.setBounds(0,0,HEIGHT,HEIGHT);
	}
	public Car(int x, int y, double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x,y,HEIGHT,HEIGHT);
		if (dir)
			this.setIcon(new ImageIcon("frogger_car_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_car_L.png"));
	}
        
        public Car(double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
		if (dir)
			this.setIcon(new ImageIcon("frogger_car_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_car_L.png"));
	}
	
	
}