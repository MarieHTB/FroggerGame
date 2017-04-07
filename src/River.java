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
	 * Constructeur de la classe qui sp�cifie les coordon�es, la vitesse et la
	 * direction initiales.
	 * 
	 * @param x
	 *            Emplacement horizontal.
	 * @param y
	 *            Emplacement vertical.
	 * @param speed
	 *            Vitesse de d�placement.
	 * @param dir
	 *            Direction du d�placement.
	 */
	public River(int x, int y, double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x, y, 2 * HEIGHT, HEIGHT);
	}

	/**
	 * Constructeur de la classe qui sp�cifie la vitesse et la direction
	 * initiales.
	 * 
	 * @param speed
	 *            Vitesse de d�placement.
	 * @param dir
	 *            Direction du d�placement.
	 */
	public River(double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
	}

}
