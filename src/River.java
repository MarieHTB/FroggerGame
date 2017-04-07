/**
 * 
 * @author Jean-Michel
 *
 */
public class River extends Actor {
	/**
	 * Constructeur de la classe.
	 */
	public River() {
		this.setBounds(0, 0, 2 * HEIGHT, HEIGHT);
	}

	/**
	 * Constructeur de la classe qui spécifie les coordonées, la vitesse et la
	 * direction initiales.
	 * 
	 * @param x
	 *            Emplacement horizontal.
	 * @param y
	 *            Emplacement vertical.
	 * @param speed
	 *            Vitesse de déplacement.
	 * @param dir
	 *            Direction du déplacement.
	 */
	public River(int x, int y, double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x, y, 2 * HEIGHT, HEIGHT);
	}

	/**
	 * Constructeur de la classe qui spécifie la vitesse et la direction
	 * initiales.
	 * 
	 * @param speed
	 *            Vitesse de déplacement.
	 * @param dir
	 *            Direction du déplacement.
	 */
	public River(double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
	}

}
