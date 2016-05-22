package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import gui.Menu;

/**
 * This is menu to tell, who is the winner
 * 
 * @author sanyasky
 */

public class ResultMenu {
	private static Shell shell;
	private Shell main_shell;
	private Shell menu_shell;
	private Display display;

	/**
	 * SkillMenu Constructor
	 * 
	 * @param display
	 * @param main_shell
	 * @param menu_shell
	 */
	public ResultMenu(Display display, Shell main_shell, Shell menu_shell) {
		this.display = display;
		this.main_shell = main_shell;
		this.menu_shell = menu_shell;
		main_shell.setVisible(false);
	}

	/** show method */
	public void Show() {
		shell = new Shell(display);
		shell.open();
		shell.setText("Results");
		shell.setSize(700, 467);
		shell.setVisible(true);
		Image backgroundImage = new Image(display, Menu.class.getResourceAsStream("end_game.jpg"));
		Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
		shell.setBackgroundImage(backgroundImage);
		GridLayout mainlayout = new GridLayout();
		mainlayout.numColumns = 1;
		shell.setLayout(mainlayout);
		mainlayout.marginLeft = 175;
		mainlayout.marginTop = 210;
		GridData data = new GridData();
		data.horizontalAlignment = GridData.CENTER;
		data.heightHint = 50;
		data.widthHint = 350;
		Button button = new Button(shell, SWT.PUSH);
		button.setFont(buttonFont);
		button.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		button.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		button.setLayoutData(data);
		if (PaddleLeft.getScore() == 11 && Menu.getMode() != 2)
			button.setText("First player win.");

		if (PaddleRight.getScore() == 11 && Menu.getMode() != 2)
			button.setText("Second player win.");

		if (PaddleRight.getScore() == 11 && Menu.getMode() == 2) {
			button.setText("You loose");

		}

		Listener listener = new Listener() {
			/**
			 * PushButton handler
			 */
			public void handleEvent(Event event) {
				if (event.widget == button) {
					Menu.setMode(0);
					PaddleRight.setScore(0);
					PaddleLeft.setScore(0);
					menu_shell.setVisible(true);
					shell.dispose();
				} 
			}
		};
		button.addListener(SWT.Selection, listener);

	}

	public Shell getShell() {
		return shell;

	}
}