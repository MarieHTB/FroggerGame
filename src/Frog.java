import javax.swing.*;

/**
 * 
 * @author Jean-Michel
 *
 */
public class Frog extends Actor {
	/**
	 * Constructeur de la classe.
	 */
	public Frog() {
		this.setBounds(0, 0, HEIGHT, HEIGHT);
		this.setIcon(new ImageIcon("frogger_frog.png"));
	}

	/**
	 * Çette méthode déplace la Frog vers la droite.
	 */
	public void moveRight() {
		if ((this.getX() + 60) > 600) {

		}

		else {
			this.setLocation(this.getX() + 20, this.getY());
		}
	}

	/**
	 * Çette méthode déplace la Frog vers la gauche.
	 */
	public void moveLeft() {
		if ((this.getX() - 20) < 0) {

		}

		else {
			this.setLocation(this.getX() - 20, this.getY());
		}
	}

	/**
	 * Çette méthode déplace la Frog vers le haut.
	 */
	public void moveUp() {
		this.setLocation(this.getX(), this.getY() - 45);
	}

	/**
	 * Çette méthode déplace la Frog vers le bas.
	 */
	public void moveDown() {
		if ((this.getY() + 85) > 580) {

		} else {
			this.setLocation(this.getX(), this.getY() + 45);
		}
	}
}
