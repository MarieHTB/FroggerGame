

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorMoveListener implements ActionListener {

	private World game;
	
	public ActorMoveListener(World game) {
		
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.moveActors();
		
		
	}

}
