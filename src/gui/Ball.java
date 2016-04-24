package gui;
/* 
 * This is the Ball class
*/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Ball {
	private static int IMAGE_WIDTH = 10;
	private static int TIMER_INTERVAL = 5;

	private static int x = 250;
	private static int y = 150;

	private static int directionX = 1;
	private static int directionY = 1;

	public static void setSkillLevel(int skill) {
		TIMER_INTERVAL = skill;
		if (skill == 10)
			IMAGE_WIDTH = 15;
		if (skill == 1)
			IMAGE_WIDTH = 5;
	}

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
		final Shell shell = new Shell(display);
		shell.setText("Ping_pong");
		shell.setSize(505, 325);

		Image bg_Image = new Image(display, Ball.class.getResourceAsStream("table-tennis-new.png"));
		shell.setBackgroundImage(bg_Image);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		Canvas canvas;
		Menu menu = new Menu(display, shell);

		PaddleLeft Paddleleft = new PaddleLeft();
		PaddleRight Paddleright = new PaddleRight(y);
		shell.setLayout(new FillLayout());// FillLayout()
		canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				// Draw the background
				// event.gc.fillRectangle(canvas.getBounds());
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

				}

			}

		};
		Display.getCurrent().addFilter(SWT.KeyDown, listener);

		shell.open();
		shell.setVisible(false);
		menu.Show();

		Label label = new Label(shell, SWT.NONE);
		String str = "Score: " + PaddleLeft.getScore() + ":" + PaddleRight.getScore();
		label.setText(str);
		label.setBounds(shell.getClientArea());

		Runnable runnable = new Runnable() {
			public void run() {
				if (Menu.getMode() > 0) {
					animate(canvas);// don't start the game if mode hasn't
									// selected
					String str = "Score: " + PaddleLeft.getScore() + ":" + PaddleRight.getScore();
					label.setText(str);
				}
				if (Menu.getMode() == 1) {
					PaddleRight.setPos(y); // bot right(computer's)
					PaddleLeft.setPos(y); // bot left(player's)
				} else if (Menu.getMode() == 2)
					PaddleRight.setPos(y); // bot right(computer's)
				if (PaddleLeft.getScore() == 11 || PaddleRight.getScore() == 11) {
					shell.setVisible(false);
					Menu.setMode(0);
					PaddleRight.setScore(0);
					PaddleLeft.setScore(0);
					menu.getShell().setVisible(true);
				}

				display.timerExec(TIMER_INTERVAL, this);
			}
		};

		display.timerExec(TIMER_INTERVAL, runnable);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		// Kill the timer
		display.timerExec(-1, runnable);
		display.dispose();

	}
}