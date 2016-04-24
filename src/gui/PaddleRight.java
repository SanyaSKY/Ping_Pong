package gui;

/*
 * PaddleRight has essentially the same set up as the human-controlled
 * paddle except that it uses the y position of the ball to determine
 * the paddle location instead of using a mouseMovement listener
 */
public class PaddleRight {

	private static int yPos = 0, score;
	final int XPOS = 490;

	// the constructor takes in an integer( which is, in our case,
	// the y position of the ball)
	public PaddleRight(int ballPos) {
		// sets the position and sets the score to 0
		setPos(ballPos);
		setScore(0);
	}

	public static void setPos(int pos) {
		// same set up as in Paddle left
		yPos = pos;
		if (yPos > 255) {
			setPos(250);
		} else if (yPos < 0) {
			setPos(0);
		}
	}

	public static int getPos() {
		return yPos;
	}

	// setters and getters for int score
	public static void setScore(int score1) {
		score = score1;
	}

	public static int getScore() {
		return score;
	}

}