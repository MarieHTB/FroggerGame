

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui implemente ActionListener qui comporte un event handler
 * qui met a jour le jeu
 * @author MHTB
 *
 */
public class ActorMoveListener implements ActionListener {

	private World game;
	
	public ActorMoveListener(World game) {
		
		this.game = game;
	}
	/**
	 * Methode qui met a jour le jeu, appelle la methode moveActors()
	 * pour faire bouger les acteurs
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.game.moveActors();
		
		
	}

}
