import javax.swing.ImageIcon;

/**
 * 
 * @author Jean-Michel
 *
 */
public class Car extends Actor {

	/**
	 * Constructeur de la classe
	 */
	public Car() {
		this.setBounds(0, 0, HEIGHT, HEIGHT);
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
	public Car(int x, int y, double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
		this.setBounds(x, y, HEIGHT, HEIGHT);
		if (dir)
			this.setIcon(new ImageIcon("frogger_car_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_car_L.png"));
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

	public Car(double speed, boolean dir) {
		this.dir = dir;
		this.speed = speed;
		if (dir)
			this.setIcon(new ImageIcon("frogger_car_R.png"));
		else
			this.setIcon(new ImageIcon("frogger_car_L.png"));
	}

}