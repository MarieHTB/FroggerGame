public class River extends Actor {
		
	public River(){
		this.setBounds(0,0,2*HEIGHT,HEIGHT);
	}
	public River(int x, int y, double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x,y,2*HEIGHT,HEIGHT);
	}
        public River(double speed, boolean dir){
		this.dir = dir;
		this.speed = speed;
	}
	


}
