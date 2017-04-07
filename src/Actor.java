import javax.swing.*;

/**
 * 
 * @author Jean-Michel
 *
 */

public class Actor extends JLabel {

	protected boolean dir;
	protected final int HEIGHT = 40;
	protected double speed = 1;

	/**
	 * Cette méthode permet d'obtenir la direction d'un acteur.
	 * 
	 * @return la valeur booléenne de la direction où true signifie que l'acteur
	 *         se dirige vers la droite et faux signifie que l'acteur se dirige
	 *         vers la gauche.
	 */
	public boolean getDirection() {
		return this.dir;
	}

	/**
	 * Cette méhtode permet de changer la direction d'un acteur.
	 * 
	 * @param d
	 *            la valeur booléenne de la direction où true signifie que
	 *            l'acteur se dirige vers la droite et faux signifie que
	 *            l'acteur se dirige vers la gauche.
	 */
	public void setDirection(boolean d) {
		this.dir = d;
	}

	/**
	 * Cette méthode d'obtenir un array représentant l'emplacement d'un acteur.
	 * 
	 * @return un tableau des quatres points délimitants un acteur.
	 */
	public int[][] getLocat() {
		int[][] P = new int[4][2];

		// Ax
		P[0][0] = this.getX();

		// Ay
		P[0][1] = this.getY();

		// Bx
		P[1][0] = this.getX() + this.getWidth();

		// By
		P[1][1] = this.getY();

		// Cx
		P[2][0] = this.getX();

		// Cy
		P[2][1] = this.getY() + 40;

		// Dx
		P[3][0] = this.getX() + this.getWidth();

		// Dy
		P[3][1] = this.getY() + 40;
		return P;
	}

	/**
	 * Cette méthode permet de modifier la vitesse d'un acteur.
	 * 
	 * @param newSpeed
	 *            une valeur double indicant la vitesse d'un acteur.
	 */
	public void setSpeed(double newSpeed) {
		this.speed = newSpeed;
	}

	/**
	 * Cette méthode permet de déplacer un acteur lorsqu'il sort des limites du
	 * JFrame.
	 */
	public void moveTo() {
		if (dir)
			this.setLocation(0 - this.getWidth(), this.getY());
		else
			this.setLocation(600, this.getY());
	}

	/**
	 * Cette méthode sert à définir les actions automatiques d'un acteur.
	 */
	public void act() {

		if (dir) {
			if (this.getX() >= 600)
				this.moveTo();
			else
				this.setLocation((int) (this.getX() + 5 * this.speed), this.getY());
		} else {
			if (this.getX() + this.getWidth() <= 0)
				this.moveTo();
			else
				this.setLocation((int) (this.getX() - 5 * this.speed), this.getY());
		}
	}
}
