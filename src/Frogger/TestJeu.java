
import java.io.IOException;

public class TestJeu {

	public static void main(String[] args){
		/*Scores scores = new Scores();
		Score score1 = new Score("Marie", 35);
		Score score2 = new Score("Seb", 25);
		scores.saveScores(score1);
		scores.saveScores(score2);
		List<Score> topScores = scores.checkTopScores();
		for(int i = 0; i<topScores.size(); i++){
			System.out.println(topScores.get(i));
		}*/
		
		Game game1 = new Game();
		game1.start();
		
		
		try {
			System.in.read();
			game1.levelUp();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
}
