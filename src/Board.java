import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Board {
	
	private final int x;
	private final int y;
	private final int maxBombs;
	private Tile[][] board;
	private ArrayList<Tile> bombs;
	
	public Board(int x, int y, int bombs) {
		this.x = x;
		this.y = y;
		this.maxBombs = bombs;
		this.bombs = new ArrayList<>();
		
		this.board = new Tile[x][y];
		
		setupBoard();
	}

	public ArrayList<Tile> getTiles() {
		ArrayList<Tile> tiles = new ArrayList<>();
		for (Tile[] r : board) {
			tiles.addAll(Arrays.asList(r));
		}
		return tiles;
	}

	public ImageIcon getIcon(String resource) {
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource("resources/" + resource + ".png")));
			return icon;
		} catch (IOException e) {
			return null;
		}
	}
	
	public void setupBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				JButton l = new JButton();
				l.setActionCommand((i * Minesweeper.getInstance().getScale()) + "|" + (j * Minesweeper.getInstance().getScale()));
				l.setLocation(i * Minesweeper.getInstance().getScale(), j * Minesweeper.getInstance().getScale());
				l.setIcon(getIcon("unclicked"));
				Minesweeper.getInstance().getFrame().getContentPane().add(l);
				
				l.addMouseListener(Minesweeper.getInstance());
				board[i][j] = new Tile(this, false, i, j, l);
			}
		}
		
		for (int b = 0; b <= maxBombs; b++) {
			Tile t = getTile(new Random().nextInt(0, x), new Random().nextInt(0, y));
			if (t.isBomb()) {
				continue;
			}
			t.setBomb(true);
			bombs.add(t);
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].getValue();
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return board[x][y];
	}
	
	public boolean tileExists(int x, int y) {
		return x >= 0 && x < this.x && y >= 0 && y < this.y;
	}
	
	public String toString() {
		String output = "";
		for (Tile[] m : board) {
			for (Tile c : m) {
				output += c.getValueBypass() + " ";
			}
			output += "\n";
		}
		return output;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ArrayList<Tile> getBombs() {
		return bombs;
	}
}