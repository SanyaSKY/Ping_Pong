package gui;

/**
 * This is the class for the human-controlled paddle
 * 
 * @author sanyasky
 */
public class PaddleLeft {
	/** declaration for variables needed */
	private static int yPos = 0, score;
	final int XPOS = 5;

	public PaddleLeft() {
		/** sets up the paddle to be at a y position of 120 */
		setPos(120);
		setScore(0);
	}
	/**setter for position
	 * @param pos
	 */
	public static void setPos(int pos) {
		yPos = pos;
		/**
		 * if the y position upper left hand corner is 40 pixels of the bottom
		 * of the applet window
		 */
		if (yPos > 255) {
			/**
			 * set it back to 70 pixels away
			 */
			setPos(255);
		}
		/**
		 * if the y position upper left hand corner is less than zero(outside
		 * the applet window)
		 */
		else if (yPos < 0) {
			/** set the y position to 0 */
			setPos(0);
		}
	}
	/**setter for int paddle position*/
	public static int getPos() {
		return yPos;

	}

	/** setter for int score */
	public static void setScore(int score1) {
		score = score1;
	}

	/** getters for int score */
	public static int getScore() {
		return score;
	}
}