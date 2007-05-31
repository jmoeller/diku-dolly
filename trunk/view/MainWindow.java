/**
 * 
 */
package view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import controller.DifficultyAction;
import controller.HelpAction;

/**
 * @author Julian
 *
 */
public class MainWindow extends JFrame {
	/**
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 3172688540921699213L;
	
	private Background backgroundPanel;
	private Board board;
	private Dimension boardDimension;
	private model.Game game;
	private IngameControls controls;
	private SheepSpeak sheepSpeak;
	private Header header;
	
	public MainWindow() {
		super("Sudoku");
		this.getLayeredPane().setSize(700, 500);
		this.getLayeredPane().setPreferredSize(this.getLayeredPane().getSize());
		this.getRootPane().setSize(this.getLayeredPane().getSize());
		this.getRootPane().setPreferredSize(this.getLayeredPane().getSize());
	}
	
	public void createHeader() {
		this.header = new Header();
		this.add(header, 50, 150, 0);
	}
	
	public IngameControls getControls() {
		return controls;
	}

	/**
	 * Create the SheepSpeak-object.
	 */
	public void createSheepSpeak() {
		this.sheepSpeak = new SheepSpeak();
		this.add(sheepSpeak, 50, 360, 210);
	}
	
	/**
	 * Gets the current SheepSpeak - the
	 * box in which the wise words of the sheep
	 * are.
	 * @return The SheepSpeak-object.
	 */
	public SheepSpeak getSheepSpeak() {
		return this.sheepSpeak;
	}
	
	/**
	 * Gets the background.
	 * @return The background.
	 */
	public Background getBackgroundPanel() {
		return backgroundPanel;
	}

	/**
	 * Creates a new background with the supplied image.
	 * @param backgroundImage The image to use as a background.
	 */
	public void createBackgroundPanel(String backgroundImage) {
		this.backgroundPanel = new Background(backgroundImage);
		/*
		 * Place the background at the back (d'uh) and at 0x0.
		 */
		this.add(backgroundPanel, 100, 0, 0);
	}

	/**
	 * Gets the graphical representation of the board.
	 * @return The board.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Creates a new board.
	 * setGame() must have been called before.
	 */
	public void createBoard() {
		this.board = new Board(this, boardDimension);
		this.add(board, 2, 50, 100);
	}

	/**
	 * Gets the current instance of the game used.
	 * @return The game.
	 */
	public model.Game getGame() {
		return game;
	}

	/**
	 * Sets the current game instance.
	 * Must be called before createBoard().
	 * @param game The game to set.
	 */
	public void setGame(model.Game game) {
		this.game = game;
		this.boardDimension = Calculator.getBoardDimensions(game.getCurrentBoard());
	}

	/**
	 * Gets the current dimensions of the board.
	 * @return The dimensions of the board.
	 */
	public Dimension getBoardDimension() {
		return boardDimension;
	}
	
	/**
	 * Adds a component to the window.
	 * @param component The component to add.
	 * @param zindex The Z-Index of the position.
	 * @param x The X-coordinate.
	 * @param y The Y-coordinate.
	 * @return The added component.
	 */
	public Component add(Component component, int zindex, int x, int y) {
		component.setLocation(x, y);
		return this.getLayeredPane().add(component, zindex);
	}

	/**
	 * Creates the ingamecontrols.
	 * @param difficultyAction The action to perform when the "New game" button is pressed.
	 * @param helpAction The action to perform when the "Help" button is pressed.
	 */
	public void createIngameControls(DifficultyAction difficultyAction, HelpAction helpAction) {
		this.controls = new IngameControls(this, difficultyAction, helpAction);
		this.add(controls, 10, 375, 100);
	}

	/**
	 * Performs some standard operations on the window.
	 */
	public void setup() {		
		/*
		 * Lay out the subcomponents correctly.
		 */
		this.validate();
		/*
		 * Disable resize of the window.
		 */
		//this.setResizable(false);
		
		this.pack();
		/*
		 * Place on the center of the screen.
		 */
		PlaceCenter.placeCenter(this);
		
		/*
		 * Show the window.
		 */
		this.setVisible(true);
	}
}
