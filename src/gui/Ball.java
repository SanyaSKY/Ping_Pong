package gui;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This is the Ball class main() is also situated here
 * 
 * @author sanyasky
 */

public class Ball {
	private static int IMAGE_WIDTH = 10;
	private static int TIMER_INTERVAL = 5;

	private static int x = 250;
	private static int y = 150;

	private static int directionX = 1;
	private static int directionY = 1;

	/**
	 * method of setting skill level
	 * 
	 * @param skill
	 */
	public static void setSkillLevel(int skill) {
		TIMER_INTERVAL = skill;
		if (skill == 10)
			IMAGE_WIDTH = 15;
		if (skill == 1)
			IMAGE_WIDTH = 5;
		if (skill > 1 && skill < 10)
			IMAGE_WIDTH = 10;
	}

	/**
	 * method of setting position of the ball
	 * 
	 * @param xPos
	 * @param yPos
	 */
	public static void setPos(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}

	/**
	 * animation of ball's movment
	 * 
	 * @param canvas
	 */
	public static void animate(Canvas canvas) {
		x += directionX;
		y += directionY;

		// Determine out of bounds
		Rectangle rect = canvas.getClientArea();
		if (x < 0) {
			x = 480;
			y = rect.height / 2;
			PaddleRight.setScore(PaddleRight.getScore() + 1);
			directionY = -directionY;

		} else if (x > rect.width - IMAGE_WIDTH) {
			x = 10;
			y = rect.height / 2;
			PaddleLeft.setScore(PaddleLeft.getScore() + 1);
			directionY = -directionY;
		}
		if (y < 0) {
			y = 0;
			directionY = -directionY;
			;
		} else if (y > rect.height - IMAGE_WIDTH) {
			y = rect.height - IMAGE_WIDTH;
			directionY = -directionY;
		}
		if (x == 10 && y >= PaddleLeft.getPos() - 5 && y <= (PaddleLeft.getPos() + 40))
			directionX = 1;
		if (x == 480 && y >= PaddleRight.getPos() - 5 && y <= (PaddleRight.getPos() + 40))
			directionX = -1;

		// Force a redraw
		canvas.redraw();
	}

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display, SWT.DIALOG_TRIM);
		shell.setText("Ping_pong");
		shell.setSize(505, 325);

		Image bg_Image = new Image(display, Ball.class.getResourceAsStream("table-tennis-new.png"));
		shell.setBackgroundImage(bg_Image);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Canvas canvas;
		Menu menu = new Menu(display, shell);

		PaddleLeft Paddleleft = new PaddleLeft();
		PaddleRight Paddleright = new PaddleRight(y);
		Replay replay = new Replay();
		shell.setLayout(new FillLayout());
		canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		canvas.addPaintListener(new PaintListener() {
			/** drawing objects (rockets and ball) */
			public void paintControl(PaintEvent event) {
				// Draw the background
				event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				event.gc.fillOval(x, y, IMAGE_WIDTH, IMAGE_WIDTH);
				event.gc.setBackground(event.display.getSystemColor(SWT.COLOR_RED));
				event.gc.fillRectangle(Paddleleft.XPOS, PaddleLeft.getPos(), 5, 40);
				event.gc.setBackground(event.display.getSystemColor(SWT.COLOR_YELLOW));
				event.gc.fillRectangle(Paddleright.XPOS, PaddleRight.getPos(), 5, 40);
			}
		});

		Listener listener = new Listener() {
			int up = 16777217, down = 16777218, w = 119, s = 115, mov = 30, esc = 27;

			public void handleEvent(Event e) {
				if (e.keyCode == s && (Menu.getMode() == 2 || Menu.getMode() == 3)) {
					PaddleLeft.setPos(PaddleLeft.getPos() + mov);
					canvas.redraw(); // Force a redraw
				}
				if (e.keyCode == w && (Menu.getMode() == 2 || Menu.getMode() == 3)) {
					PaddleLeft.setPos(PaddleLeft.getPos() - mov);
					canvas.redraw();// Force a redraw
				}
				if (e.keyCode == down && Menu.getMode() == 3) {
					PaddleRight.setPos(PaddleRight.getPos() + mov);
					canvas.redraw(); // Force a redraw
				}
				if (e.keyCode == up && Menu.getMode() == 3) {
					PaddleRight.setPos(PaddleRight.getPos() - mov);
					canvas.redraw();// Force a redraw
				}
				if (e.keyCode == esc) {
					shell.setVisible(false);
					Menu.setMode(0);
					PaddleRight.setScore(0);
					PaddleLeft.setScore(0);
					menu.getShell().setVisible(true);
					try {
						replay.writeToFile();
					} catch (IOException event) {
						event.printStackTrace();
					}

				}
			}
		};
		Display.getCurrent().addFilter(SWT.KeyDown, listener);

		shell.open();
		shell.setVisible(false);
		/** Open menu */
		menu.Show();

		/** Score label */
		Label label = new Label(shell, SWT.NONE);
		String str = "Score: " + PaddleLeft.getScore() + ":" + PaddleRight.getScore();
		label.setText(str);
		label.setBounds(shell.getClientArea());

		String replayname = "last";
		/** Open replay file */
		try {
			replay.readFromFile(replayname);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		Runnable runnable = new Runnable() {
			public void run() {
				/** If game mode selected */
				if (Menu.getMode() > 0) {// don't start the game if mode hasn't
											// selected
					if (Menu.getMode() != 4)
						animate(canvas);
					String str = "Score: " + PaddleLeft.getScore() + ":" + PaddleRight.getScore();
					label.setText(str);
					replay.addState(PaddleLeft.getPos(), PaddleRight.getPos(), x, y, PaddleLeft.getScore(),
							PaddleRight.getScore());
				}
				/** If Show mode */
				if (Menu.getMode() == 1) {
					PaddleRight.setPos(y); // bot right(computer's)
					PaddleLeft.setPos(y); // bot left(player's)
				} /** If Single Player mode */
				else if (Menu.getMode() == 2)
					PaddleRight.setPos(y); // bot right(computer's)
				/** If score = 11 */
				if ((PaddleLeft.getScore() == 11 || PaddleRight.getScore() == 11) && (Menu.getMode() > 0)) {
					shell.setVisible(false);
					Menu.setMode(0);
					PaddleRight.setScore(0);
					PaddleLeft.setScore(0);
					menu.getShell().setVisible(true);
					/** Write replay to the file */
					try {
						replay.writeToFile();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if (Menu.getMode() == 4)
					replay.startReplay(canvas);
				display.timerExec(TIMER_INTERVAL, this);
			}
		};

		display.timerExec(TIMER_INTERVAL, runnable);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		/** Kill the timer */
		display.timerExec(-1, runnable);
		display.dispose();

	}
}