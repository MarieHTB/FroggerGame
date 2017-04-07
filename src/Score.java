import java.util.Date;

/**
 * Classe 
 * @author MHTB
 *Methode pour le score
 */
public class Score {

	private String name;
	private int score;
	private Date date;
	

	//constructeur
	public Score(String name, int score){
		this.name = name;
		this.score = score;
		this.date = new Date();
		
		
	}
	
	//constructeur
	public Score(String name, int score, Date date){
		this(name, score);
		this.date = date;
	}
	
	/**
	 * Methode pour obtenir le nom
	 * @return le nom du joueur
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Methode pour obtenir le score
	 * @return le score du joueur
	 */
	public int getScore(){
		return this.score;
	}
	/**
	 * Methode pour afficher le nom, le score et la date
	 */
	public String toString(){
		return this.name + ";" + this.score + ";" + this.date;
	}
	
}
