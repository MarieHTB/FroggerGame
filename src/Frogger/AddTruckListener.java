

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTruckListener implements ActionListener{
	
	private Game game;
	
	public AddTruckListener(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.addTruck();
	}

}
