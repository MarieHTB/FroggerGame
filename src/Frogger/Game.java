

import javax.swing.Timer;

public class Game {

	private int life;
	private int score;
	private int level;
	private boolean frogStatus;
	private Timer timer;
	private Timer timerAddCar;
	private Timer timerAddRiver;
	private Timer timerAddTruck;
	private GameTime gameTimeListener;
	private AddCarListener addCarListener;
	private AddRiverListener addRiverListener;
	private AddTruckListener addTruckListener;
	private int gameDuration;
	
	public Game(){
		this.life = 3;
		this.score = 0;
		this.level = 1;
		this.gameDuration = 30000;
	}
	
	public void start(){
		System.out.println("Starting level" + this.level);
		//creer route
		//creer grenouille
		//creer roches
		//creer des char initiaux et riviere
		
		this.frogStatus = true;
		
		this.gameTimeListener = new GameTime(this);
		
		if(timer != null){
			timer.stop();
		}
		
		timer = new Timer(this.gameDuration, gameTimeListener);
		
		this.addCarListener = new AddCarListener(this);
		this.timerAddCar = new Timer(this.getObjectCreationTime(), this.addCarListener);
		this.addRiverListener = new AddRiverListener(this);
		this.timerAddRiver = new Timer(this.getObjectCreationTime(), this.addRiverListener);
		this.addTruckListener = new AddTruckListener(this);
		this.timerAddTruck = new Timer(this.getObjectCreationTime(), this.addTruckListener);
		//initialiser game
		
		timer.start();
		this.timerAddCar.start();
		this.timerAddRiver.start();
		this.timerAddTruck.start();
	}
	
	private int getObjectCreationTime(){
		return (int)(((Math.random() + 1.0) * 800.0) + 200.0);
		
	}
	
	private double getSpeedCoeff(){
		return Math.log(this.level) * 1.5 + 1;
	}
	
	private void cleanTimer(){
		this.timerAddCar.stop();
	}
	
	public void timeOver() {
		this.cleanTimer();
		this.life--;
		System.out.println("Level Time Over" + this.life + "life left");
		if(this.life == 0){
			this.gameOver();
		}
		else{
			this.start();
		}
		
		
	}	
	
	public void addCar(){
		//creer nouvel auto
		System.out.println("nouvelle auto");
		
		//new Car(direction, ligne vitesse)
		//interface.voiciAuto
		
		this.timerAddCar.setDelay(this.getObjectCreationTime());
		//reset timer pour prochain auto
		
	}
	
	public void addTruck(){
		System.out.println("nouveau truck");
		this.timerAddTruck.setDelay(this.getObjectCreationTime());
		
	}
	
	public void addRiver(){
		System.out.println("nouvelle riviere");
		this.timerAddRiver.setDelay(this.getObjectCreationTime());
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void levelUp(){
		this.level++;
		this.start();
	}
	
	public void gameOver(){
		System.out.println("GAME OVER");
	}

	public void gameCompleted(){
		this.cleanTimer();
		this.timer.stop();
		this.score += 100 / (this.gameDuration - this.timer.getDelay());
		this.levelUp();
	}
		
}
