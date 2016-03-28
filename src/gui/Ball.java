package gui;
/* 
 * This is the Ball class
*/
/*public class Ball {

	    //variables for the x and y position
	    private int xPos,yPos;
	    //the direction of the x and y position. Dy is set to -5 because,
	    //remeber that the applet "grid" has it's (0, 0) orgin at the
	    //top lefthand corner of the applet window. For the mathimatically
	    //minded among us, the applet is essentially quadrant IV
	    public int dx = 5, dy = -5;     
	    public Ball(){
	       //sets the initial ball position to near the center of the
	       //screen
	        setPos(250, 140);
	    }
	   
	    public void setPos(int x, int y){
	    	this.xPos = x;
	        this.yPos = y;
	    }
	     
	    public int getX(){
	        return xPos;
	    }
	    
	    public int getY(){
	        return yPos;
	    }
	    //this is the method used to move the ball
	    public void move(){
	        //it takes the current x and y position, then adds their current
	        //direction of movement to them, giving us a shift in the ball's
	        //position in the applet
	        setPos(this.getX() + dx, this.getY() + dy);
	    }  
	    //reset method for when the computer scores
	    public void reset(){
	        //the same initial setup as before
	        setPos(250,140);
	        dx = 5;
	        dy = -5;
	    }
	}*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Ball {
  private static final int IMAGE_WIDTH = 10;
  private static final int TIMER_INTERVAL = 1;

  private static int x = 0;
  private static int y = 0;

  private static int directionX = 3;
  private static int directionY = 2;

  private static Canvas canvas;

  public static void animate() {
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

    // Force a redraw
    canvas.redraw();
  }

  public static void main(String[] args) {
    final Display display = new Display();
    final Shell shell = new Shell(display);
    shell.setText("Ball");

    shell.setLayout(new FillLayout());
   /* Canvas canvas1 = new Canvas(shell, SWT.NONE);

    /*canvas1.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_RED));
        e.gc.fillRectangle(5, 20, 5, 40);
      }
    });*/
    canvas = new Canvas(shell, SWT.NO_BACKGROUND);
    canvas.addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent event) {
        // Draw the background
        event.gc.fillRectangle(canvas.getBounds());
        event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
        event.gc.fillOval(x, y, IMAGE_WIDTH, IMAGE_WIDTH);
      }
    });

    
    shell.open();
    Runnable runnable = new Runnable() {
      public void run() {
        animate();
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