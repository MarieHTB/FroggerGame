import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * Classe pour sauvegarder les scores, les trier et afficher les 10 meilleurs
 * @author MHTB
 *
 */
public class Scores {

	private String filePath;
	//DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//Calendar date1 = Calendar.getInstance();
	
	/**
	 * Constructeur qui crer le fichier text pour sauvegarder les scores
	 */
	public Scores(){
		filePath = "froggerScores.txt";
		
	}
	
	/**
	 * Methode qui permet de sauvegarder les scores sur le fichier text
	 * @param score
	 */
	public void saveScores(Score score){
		try{
			FileWriter fw = new FileWriter(this.filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			out.println(score);
			out.close();
			bw.close();
			fw.close();
		}
		
		catch (IOException e) {
			   System.out.println("Impossible d'ecrire dans le fichier"); 
		}
	}
	/**
	 * Methode pour faire le tri des scores du plus grand au plus petit
	 * @param scores
	 */
	public void bubbleSort(ArrayList<Score> scores){
		for(int i = 0; i < scores.size(); i++){
			for(int j = 0; j < scores.size() - 1; j++){
				if(scores.get(j).getScore() < scores.get(j + 1).getScore()){
					Score temp = scores.get(j);
					scores.set(j, scores.get(j + 1));
					scores.set(j + 1, temp);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<Score> checkTopScores(){
		 ArrayList<Score> scores = new ArrayList<Score>();
		 try {
			 File file = new File(this.filePath);
			 file.createNewFile();			 
			 FileReader fileReader = new FileReader(this.filePath);
			 BufferedReader bufferedReader = new BufferedReader(fileReader);
			 
			 String line;
			 while ((line = bufferedReader.readLine()) != null) {
				 String[] temp = line.split(";");
				 Score score = new Score(temp[0], Integer.parseInt(temp[1]),  new Date(temp[2]));
				 //Score score = new Score(temp[0], Integer.parseInt(temp[1]),  df.format(date1.getInstance()(temp[2])));
				 scores.add(score);
			}
			 	bufferedReader.close();
				fileReader.close();				
				
		}
		
		catch (IOException e) {
				System.out.println("Probleme durant la lecture du fichier");
		}
		 
		 bubbleSort(scores);
				 
		//aller chercher resultats du fichier filePath
		List<Score> tempScore;
		if(scores.size() > 10){
			tempScore = scores.subList(0, 10);
		}
		else{
			tempScore = scores.subList(0, scores.size());
		}
		return tempScore;
	}
	
}
