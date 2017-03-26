package Frogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Scores {

	private String filePath;
	
	public Scores(){
		filePath = "froggerScores.txt";
		
	}
	
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
				 scores.add(score);
			}
			 	bufferedReader.close();
				fileReader.close();				
				
		}
		
		catch (IOException e) {
				System.out.println("Probleme durant la lecture du fichier");
		}
		
		 Collections.sort(scores, Comparator.comparing(Score::getScore).reversed());
		 
		 
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
