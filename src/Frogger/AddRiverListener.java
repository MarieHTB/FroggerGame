package Frogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRiverListener implements ActionListener{

	private Game game;
	
	public AddRiverListener(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.addRiver();
		
	}	
	
}
