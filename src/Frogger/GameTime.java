package Frogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTime implements ActionListener {

	private Game game;
	
	public GameTime(Game game) {
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.timeOver();
		
	}


	
}
