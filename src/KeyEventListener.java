import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Classe qui comporte la methode pour faire bouger avec les fleches la grenouille
 * @author MHTB
 *
 */
public class KeyEventListener implements KeyListener {

	private Frog frog;
	private World world;
	
	public KeyEventListener(World world, Frog frog) {
		this.frog = frog;
		this.world = world;
	}
	//methode pour assigner les fleches avec les mouvements de la grenouille
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			this.frog.moveUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.frog.moveDown();
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.frog.moveRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.frog.moveLeft();
		}
		
		this.world.validateFrog();		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Do nothing
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//Do nothing
		
	}
	
	

}
