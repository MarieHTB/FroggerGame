import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	//DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//Calendar date1 = Calendar.getInstance();

	//constructeur
	public Score(String name, int score){
		this.name = name;
		this.score = score;
		this.date = new Date();
		//this.date1 = Calendar.getInstance();
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
