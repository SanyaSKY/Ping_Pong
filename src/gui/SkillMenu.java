package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import gui.Menu;

/**
 * This is menu to choose level of hardness
 * 
 * @author sanyasky
 */

public class SkillMenu {
	private Shell shell;
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
	public SkillMenu(Display display, Shell main_shell, Shell menu_shell) {
		this.display = display;
		this.main_shell = main_shell;
		this.menu_shell = menu_shell;
		menu_shell.setVisible(false);
		this.shell = new Shell(display);
		shell.open();
		shell.setText("Skill level");
		shell.setSize(644, 408);

	}

	/** show method */
	public void Show() {
		Image backgroundImage = new Image(display, Menu.class.getResourceAsStream("ping-pong.jpeg"));
		Font buttonFont = new Font(shell.getDisplay(), new FontData("Times New Roman", 14, SWT.NORMAL));
		shell.setBackgroundImage(backgroundImage);
		GridLayout mainlayout = new GridLayout();
		mainlayout.numColumns = 1;
		shell.setLayout(mainlayout);
		mainlayout.marginLeft = 110;
		mainlayout.marginTop = 50;
		GridData griddatask_baby = new GridData();
		griddatask_baby.horizontalAlignment = GridData.CENTER;
		griddatask_baby.heightHint = 50;
		griddatask_baby.widthHint = 350;
		Button sk_baby = new Button(shell, SWT.PUSH);
		sk_baby.setFont(buttonFont);
		sk_baby.setText("I'm too young to die.");
		sk_baby.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		sk_baby.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		sk_baby.setLayoutData(griddatask_baby);
		GridData griddatask_easy = new GridData();
		griddatask_easy.horizontalAlignment = GridData.CENTER;
		griddatask_easy.heightHint = 50;
		griddatask_easy.widthHint = 350;
		Button sk_easy = new Button(shell, SWT.PUSH);
		sk_easy.setText("Hey,not too rough.");
		sk_easy.setFont(buttonFont);
		sk_easy.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		sk_easy.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		sk_easy.setLayoutData(griddatask_easy);
		GridData griddatask_medium = new GridData();
		griddatask_medium.horizontalAlignment = GridData.CENTER;
		griddatask_medium.heightHint = 50;
		griddatask_medium.widthHint = 350;
		Button sk_medium = new Button(shell, SWT.PUSH);
		sk_medium.setText("Hurt me plenty");
		sk_medium.setFont(buttonFont);
		sk_medium.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		sk_medium.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		sk_medium.setLayoutData(griddatask_medium);
		GridData griddatask_hard = new GridData();
		griddatask_hard.horizontalAlignment = GridData.CENTER;
		griddatask_hard.heightHint = 50;
		griddatask_hard.widthHint = 350;
		Button sk_hard = new Button(shell, SWT.PUSH);
		sk_hard.setText("Ultra-Violence.");
		sk_hard.setFont(buttonFont);
		sk_hard.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
		sk_hard.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		sk_hard.setLayoutData(griddatask_hard);
		GridData griddatask_nightmare = new GridData();
		griddatask_nightmare.horizontalAlignment = GridData.CENTER;
		griddatask_nightmare.heightHint = 50;
		griddatask_nightmare.widthHint = 350;
		Button sk_nightmare = new Button(shell, SWT.PUSH);
		sk_nightmare.setText("Nightmare!");
		sk_nightmare.setFont(buttonFont);
		sk_nightmare.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
		sk_nightmare.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		sk_nightmare.setLayoutData(griddatask_nightmare);

		Listener listener = new Listener() {
			/**
			 * PushButton handler
			 */
			public void handleEvent(Event event) {
				if (event.widget == sk_baby) {
					Ball.setSkillLevel(10);
					shell.setVisible(false);
					main_shell.setVisible(true);
				} else if (event.widget == sk_easy) {
					Ball.setSkillLevel(8);
					shell.setVisible(false);
					main_shell.setVisible(true);
				} else if (event.widget == sk_medium) {
					Ball.setSkillLevel(6);
					shell.setVisible(false);
					main_shell.setVisible(true);
				} else if (event.widget == sk_hard) {
					Ball.setSkillLevel(4);
					shell.setVisible(false);
					main_shell.setVisible(true);
				} else if (event.widget == sk_nightmare) {
					Ball.setSkillLevel(1);
					shell.setVisible(false);
					main_shell.setVisible(true);
				}
			}
		};
		sk_baby.addListener(SWT.Selection, listener);
		sk_easy.addListener(SWT.Selection, listener);
		sk_medium.addListener(SWT.Selection, listener);
		sk_hard.addListener(SWT.Selection, listener);
		sk_nightmare.addListener(SWT.Selection, listener);

	}
}