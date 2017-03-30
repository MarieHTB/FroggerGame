

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCarListener implements ActionListener {

	private Game game;
	
	public AddCarListener(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.addCar();
		
		
	}

}
