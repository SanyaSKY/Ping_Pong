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

/*
 * package gui;
 * 
 * import java.io.IOException; import java.io.InputStream; import
 * java.nio.file.Files; import java.nio.file.Paths;
 * 
 * import org.eclipse.swt.SWT; import org.eclipse.swt.graphics.Font; import
 * org.eclipse.swt.graphics.FontData; import org.eclipse.swt.graphics.Image;
 * import org.eclipse.swt.layout.GridData; import
 * org.eclipse.swt.layout.GridLayout; import org.eclipse.swt.widgets.Button;
 * import org.eclipse.swt.widgets.Control; import
 * org.eclipse.swt.widgets.Display; import org.eclipse.swt.widgets.Event; import
 * org.eclipse.swt.widgets.Listener; import org.eclipse.swt.widgets.Shell;
 * 
 * public class Menu {
 * 
 * private Shell shell;
 * 
 * private Display display;
 * 
 * Menu(Display display, Shell shell) { this.shell=shell; this.display=display;
 * }
 * 
 * public void Show() { for (Control kid : shell.getChildren()) { kid.dispose();
 * } InputStream is=null; try { is =
 * Files.newInputStream(Paths.get("pingpong.jpg")); } catch (IOException e) {
 * e.printStackTrace(); } Image backgroundImage = new Image(display, is); try {
 * is.close(); } catch (IOException e) { e.printStackTrace(); } Font buttonFont
 * = new Font( shell.getDisplay(), new FontData( "Times New Roman", 14,
 * SWT.NORMAL ) ); shell.setBackgroundImage(backgroundImage); GridLayout
 * mainlayout=new GridLayout(); mainlayout.numColumns=1;
 * shell.setLayout(mainlayout); mainlayout.marginLeft=220;
 * mainlayout.marginTop=315; GridData griddataButtonRules=new GridData();
 * griddataButtonRules.horizontalAlignment=GridData.CENTER;
 * griddataButtonRules.heightHint=65; griddataButtonRules.widthHint=350; Button
 * buttonRules=new Button(shell, SWT.PUSH); buttonRules.setFont(buttonFont);
 * buttonRules.setText("Ïðàâèëà èãðû");
 * buttonRules.setBackground(shell.getDisplay().getSystemColor(SWT.
 * COLOR_DARK_MAGENTA)); buttonRules.setLayoutData(griddataButtonRules);
 * GridData griddataButtonComputer=new GridData();
 * griddataButtonComputer.horizontalAlignment=GridData.CENTER;
 * griddataButtonComputer.heightHint=65; griddataButtonComputer.widthHint=350;
 * Button buttonComputer=new Button(shell, SWT.PUSH); buttonComputer.setText(
 * "Èãðîê ïðîòèâ êîìïüþòåðà"); buttonComputer.setFont(buttonFont);
 * buttonComputer.setBackground(shell.getDisplay().getSystemColor(SWT.
 * COLOR_DARK_MAGENTA)); buttonComputer.setLayoutData(griddataButtonComputer);
 * GridData griddataButtonPlayers=new GridData();
 * griddataButtonPlayers.horizontalAlignment=GridData.CENTER;
 * griddataButtonPlayers.heightHint=65; griddataButtonPlayers.widthHint=350;
 * Button buttonPlayers=new Button(shell, SWT.PUSH); buttonPlayers.setText(
 * "Èãðîê ïðîòèâ èãðîêà"); buttonPlayers.setFont(buttonFont);
 * buttonPlayers.setBackground(shell.getDisplay().getSystemColor(SWT.
 * COLOR_DARK_MAGENTA)); buttonPlayers.setLayoutData(griddataButtonPlayers);
 * Listener listener = new Listener() { public void handleEvent(Event event) {
 * if (event.widget == buttonRules){ //Rules rules=new Rules(display, shell);
 * //rules.Show(); } else if(event.widget == buttonComputer){ //Computer
 * computer=new Computer(display,shell); //computer.Show(); } else
 * if(event.widget == buttonPlayers){ //Number number=new
 * Number(display,shell,0); //number.Show(); } } };
 * buttonRules.addListener(SWT.Selection,listener);
 * buttonComputer.addListener(SWT.Selection,listener);
 * buttonPlayers.addListener(SWT.Selection,listener); shell.pack();
 * shell.setSize(505, 325); } }
 */