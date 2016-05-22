package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This is the main menu class
 * 
 * @author sanyasky
 */

public class Menu {

	private static Shell shell;
	private Shell main_shell;

	private Display display;

	private static int gameMode = 0;

	public Menu(Display display, Shell main_shell) {
		this.display = display;
		this.main_shell = main_shell;
		shell = new Shell(display);
		shell.setText("Main Menu");
		shell.open();
		shell.setSize(438, 350);

	}

	public void Show() {
		Image backgroundImage = new Image(display, Menu.class.getResourceAsStream("pingpong.jpg"));
		Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
		shell.setBackgroundImage(backgroundImage);
		GridLayout mainlayout = new GridLayout();
		mainlayout.numColumns = 1;
		shell.setLayout(mainlayout);
		mainlayout.marginLeft = 105;
		mainlayout.marginTop = 70;
		GridData griddataButtonRules = new GridData();
		griddataButtonRules.horizontalAlignment = GridData.CENTER;
		griddataButtonRules.heightHint = 35;
		griddataButtonRules.widthHint = 200;
		Button ShowButton = new Button(shell, SWT.PUSH);
		ShowButton.setFont(buttonFont);
		ShowButton.setText("Show");
		ShowButton.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_GREEN));
		ShowButton.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		ShowButton.setLayoutData(griddataButtonRules);
		GridData griddataButtonComputer = new GridData();
		griddataButtonComputer.horizontalAlignment = GridData.CENTER;
		griddataButtonComputer.heightHint = 35;
		griddataButtonComputer.widthHint = 200;
		Button buttonComputer = new Button(shell, SWT.PUSH);
		buttonComputer.setText("Single player");
		buttonComputer.setFont(buttonFont);
		buttonComputer.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		buttonComputer.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		buttonComputer.setLayoutData(griddataButtonComputer);
		GridData griddataButtonPlayers = new GridData();
		griddataButtonPlayers.horizontalAlignment = GridData.CENTER;
		griddataButtonPlayers.heightHint = 35;
		griddataButtonPlayers.widthHint = 200;
		Button buttonPlayers = new Button(shell, SWT.PUSH);
		buttonPlayers.setText("PVP");
		buttonPlayers.setFont(buttonFont);
		buttonPlayers.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_YELLOW));
		buttonPlayers.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		buttonPlayers.setLayoutData(griddataButtonPlayers);
		GridData griddataButtonReplay = new GridData();
		griddataButtonReplay.horizontalAlignment = GridData.CENTER;
		griddataButtonReplay.heightHint = 35;
		griddataButtonReplay.widthHint = 200;
		Button buttonReplay = new Button(shell, SWT.PUSH);
		buttonReplay.setText("Replay");
		buttonReplay.setFont(buttonFont);
		buttonReplay.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		buttonReplay.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		buttonReplay.setLayoutData(griddataButtonReplay);

		Listener listener = new Listener() {
			/**
			 * PushButton handler
			 * 
			 */
			public void handleEvent(Event event) {
				if (event.widget == ShowButton) {
					gameMode = 1;
					SkillMenu skillmenu = new SkillMenu(display, main_shell, shell);
					skillmenu.Show();
					shell.setVisible(false);
				} else if (event.widget == buttonComputer) {
					gameMode = 2;
					SkillMenu skillmenu = new SkillMenu(display, main_shell, shell);
					skillmenu.Show();
					shell.setVisible(false);
				} else if (event.widget == buttonPlayers) {
					gameMode = 3;
					SkillMenu skillmenu = new SkillMenu(display, main_shell, shell);
					skillmenu.Show();
					shell.setVisible(false);
				} else if (event.widget == buttonReplay) {
					gameMode = 4;
					SkillMenu skillmenu = new SkillMenu(display, main_shell, shell);
					skillmenu.Show();
					shell.setVisible(false);
				}

			}
		};
		/**
		 * ShowButton listener
		 */
		ShowButton.addListener(SWT.Selection, listener);
		/**
		 * buttonComputer listener
		 */
		buttonComputer.addListener(SWT.Selection, listener);
		/**
		 * buttonPlayers listener
		 */
		buttonPlayers.addListener(SWT.Selection, listener);
		/**
		 * buttonReplay listener
		 */
		buttonReplay.addListener(SWT.Selection, listener);

	}

	/**
	 * function of getting mode
	 * 
	 * @return
	 */
	public static int getMode() {
		return gameMode;

	}

	/**
	 * function to set game mode
	 * 
	 * @param mode
	 */
	public static void setMode(int mode) {
		gameMode = mode;
	}

	/**
	 * function to get main menu shell
	 * 
	 * @return
	 */
	public Shell getShell() {
		return shell;

	}
}