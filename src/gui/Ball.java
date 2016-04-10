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
	private static final int IMAGE_WIDTH = 10;
	private static final int TIMER_INTERVAL = 10;

	private static int x = 250;
	private static int y = 150;

	private static int directionX = 1;
	private static int directionY = 1;

	public static void animate(Canvas canvas) {
		x += directionX;
		y += directionY;

		// Determine out of bounds
		Rectangle rect = canvas.getClientArea();
		if (x < 0) {
			x = 0;
			directionX = 1;
		} else if (x > rect.width - IMAGE_WIDTH) {
			x = rect.width - IMAGE_WIDTH;
			directionX = -1;
		}
		if (y < 0) {
			y = 0;
			directionY = 1;
		} else if (y > rect.height - IMAGE_WIDTH) {
			y = rect.height - IMAGE_WIDTH;
			directionY = -1;
		}
		if (x == 10 && y >= PaddleLeft.getPos() && y <= (PaddleLeft.getPos() + 40))
			directionX = 1;
		if (x == 480 && y >= PaddleRight.getPos() && y <= (PaddleRight.getPos() + 40))
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

		PaddleLeft Paddleleft = new PaddleLeft();
		PaddleRight Paddleright = new PaddleRight(y);
		shell.setLayout(new FillLayout());
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
			int up = 16777217, down = 16777218, w = 119, s = 115;

			public void handleEvent(Event e) {
				if (e.keyCode == s) {
					PaddleLeft.setPos(PaddleLeft.getPos() + 10);
					canvas.redraw(); // Force a redraw
				}
				if (e.keyCode == w) {
					PaddleLeft.setPos(PaddleLeft.getPos() - 10);
					canvas.redraw();// Force a redraw
				}
				if (e.keyCode == down) {
					PaddleRight.setPos(PaddleRight.getPos() + 10);
					canvas.redraw(); // Force a redraw
				}
				if (e.keyCode == up) {
					PaddleRight.setPos(PaddleRight.getPos() - 10);
					canvas.redraw();// Force a redraw
				}

			}
		};
		Display.getCurrent().addFilter(SWT.KeyDown, listener);

		shell.open();
		// shell.addListener(SWT.KeyDown, listener);
		// shell.addListener(SWT.KeyUp, listener);
		Runnable runnable = new Runnable() {
			public void run() {
				animate(canvas);
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