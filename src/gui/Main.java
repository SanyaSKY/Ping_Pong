package gui;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Ping pong");
		shell.setSize(505, 325);
		Image bg_Image = new Image(display, "/home/sanyasky/workspace/Ping pong/src/gui/table-tennis-new.png"); 
	       shell.setBackgroundImage(bg_Image);
	       shell.setBackgroundMode(SWT.INHERIT_FORCE);  
	      // shell.setFullScreen(true);
	       shell.setLayout(new FillLayout());

	       try{
	    	   Canvas canvas = new Canvas(shell, SWT.NONE);
	       

	       canvas.addPaintListener(new PaintListener() {
	         public void paintControl(PaintEvent e) {
	           e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_RED));
	           e.gc.fillRectangle(5, 20, 5, 40);
	           e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_YELLOW));
	           e.gc.fillRectangle(500, 20, 5, 40);
	         }
	       });
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
	       
		shell.open();
		while (!shell.isDisposed()) {
		if (!display.readAndDispatch()) {
		display.sleep();
		}
		}
		display.dispose();
	}

}
