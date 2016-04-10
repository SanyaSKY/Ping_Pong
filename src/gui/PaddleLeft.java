package gui;

/*This is the class for the human-controlled paddle

	 */
public class PaddleLeft {
	// declaration for variables needed 
	private static int yPos = 0;
	final int XPOS = 5;

	public PaddleLeft() {
		// sets up the paddle to be at a y position of 120
		setPos(120);
	}

	public static void setPos(int pos) {
		yPos = pos;
		// if the y position upper left hand corner is
		// 40 pixels of the bottom of the applet window
		if (yPos > 255) {
			// set it back to 70 pixels away
			setPos(255);
		}
		// if the y position upper left hand corner is
		// less than zero(outside the applet window)
		else if (yPos < 0) {
			// set the y position to 0
			setPos(0);
		}
	}

	public static int getPos() {
		return yPos;

	}
}