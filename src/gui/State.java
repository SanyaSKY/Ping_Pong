package gui;

import java.io.Serializable;

/**
 * Class stores current game state (one frame of the game)
 */
public class State implements Serializable {
	private int posYPadLeft;
	private int posYPadRight;
	private int posXBall;
	private int posYBall;
	private int scorePadLeft;
	private int scorePadRight;

	public State(int posYPadLeft, int posYPadRight, int posXBall, int posYBall, int scorePadLeft, int scorePadRight) {
		this.posYPadLeft = posYPadLeft;
		this.posYPadRight = posYPadRight;
		this.posXBall = posXBall;
		this.posYBall = posYBall;
		this.scorePadLeft = scorePadLeft;
		this.scorePadRight = scorePadRight;
	}

	public State() {
		this.posYPadLeft = 0;
		this.posYPadRight = 0;
		this.posXBall = 0;
		this.posYBall = 0;
		this.scorePadLeft = 0;
		this.scorePadRight = 0;
	}

	public int getposYPadLeft() {
		return posYPadLeft;
	}

	public void setposYPadLeft(int posYPadLeft) {
		this.posYPadLeft = posYPadLeft;
	}

	public int getposYPadRight() {
		return posYPadRight;
	}

	public void setposYPadRight(int posYPadRight) {
		this.posYPadRight = posYPadRight;
	}

	public int getPosXBall() {
		return posXBall;
	}

	public void setPosXBall(int posXBall) {
		this.posXBall = posXBall;
	}

	public int getPosYBall() {
		return posYBall;
	}

	public void setPosYBall(int posYBall) {
		this.posYBall = posYBall;
	}

	public int getscorePadLeft() {
		return scorePadLeft;
	}

	public void setscorePadLeft(int scorePadLeft) {
		this.scorePadLeft = scorePadLeft;
	}

	public int getscorePadRight() {
		return scorePadRight;
	}

	public void setscorePadRight(int scorePadRight) {
		this.scorePadRight = scorePadRight;
	}
}