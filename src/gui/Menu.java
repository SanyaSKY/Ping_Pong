package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

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
		mainlayout.marginLeft = 100;
		mainlayout.marginTop = 90;
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
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				if (event.widget == ShowButton) {
					gameMode = 1;
					main_shell.setVisible(true);
					shell.setVisible(false);
				} else if (event.widget == buttonComputer) {
					gameMode = 2;
					SkillMenu skillmenu = new SkillMenu(display, main_shell, shell);
					skillmenu.Show();
					shell.setVisible(false);
				} else if (event.widget == buttonPlayers) {
					gameMode = 3;
					main_shell.setVisible(true);
					shell.setVisible(false);
				}
			}
		};
		ShowButton.addListener(SWT.Selection, listener);
		buttonComputer.addListener(SWT.Selection, listener);
		buttonPlayers.addListener(SWT.Selection, listener);
	}

	public static int getMode() {
		return gameMode;

	}

	public static void setMode(int mode) {
		gameMode = mode;
	}

	public static Shell getShell() {
		return shell;

	}
}