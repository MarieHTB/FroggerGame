import javax.swing.ImageIcon;

public class Truck extends Actor {
	
	public Truck(){
		this.setBounds(0,0,2*HEIGHT,HEIGHT);
	}
	public Truck(int x, int y, double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x,y,2*HEIGHT,HEIGHT);
		if (dir)
			this.setIcon(new ImageIcon("frogger_truck_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_truck_L.png"));
	}
        public Truck(double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
		if (dir)
			this.setIcon(new ImageIcon("frogger_truck_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_truck_L.png"));
	}
	
	
}
