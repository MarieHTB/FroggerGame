import java.util.Date;

public class Score {

	private String name;
	private int score;
	private Date date;
	
	public Score(String name, int score){
		this.name = name;
		this.score = score;
		this.date = new Date();
	}
	
	public Score(String name, int score, Date date){
		this(name, score);
		this.date = date;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public String toString(){
		return this.name + ";" + this.score + ";" + this.date;
	}
	
}
