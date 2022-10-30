import javax.swing.*;

public class Tile {
	private Board board;
	private boolean isBomb;
	private boolean isClicked;
	private boolean marked;
	private JButton button;
	
	private int x;
	private int y;
	
	public Tile(Board board, boolean isBomb, int x, int y, JButton button) {
		this.board = board;
		this.isBomb = isBomb;
		this.isClicked = false;
		this.marked = false;
		this.button = button;
		
		this.x = x;
		this.y = y;
	}
	
	public String getValue() {
		if (!isClicked) {
			return "";
		}
		if (isBomb) {
			return "X";
		}
		int bombcount = 0;
		if (board.tileExists(x + 1, y) && board.getTile(x + 1, y).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y) && board.getTile(x - 1, y).isBomb)
			bombcount++;
		if (board.tileExists(x + 1, y + 1) && board.getTile(x + 1, y + 1).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y - 1) && board.getTile(x - 1, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x, y + 1) && board.getTile(x, y + 1).isBomb)
			bombcount++;
		if (board.tileExists(x, y - 1) && board.getTile(x, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x + 1, y - 1) && board.getTile(x + 1, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y + 1) && board.getTile(x - 1, y + 1).isBomb)
			bombcount++;
		return bombcount + "";
	}
	
	public String getValueBypass() {
		if (isBomb) {
			return "X";
		}
		int bombcount = 0;
		if (board.tileExists(x + 1, y) && board.getTile(x + 1, y).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y) && board.getTile(x - 1, y).isBomb)
			bombcount++;
		if (board.tileExists(x + 1, y + 1) && board.getTile(x + 1, y + 1).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y - 1) && board.getTile(x - 1, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x, y + 1) && board.getTile(x, y + 1).isBomb)
			bombcount++;
		if (board.tileExists(x, y - 1) && board.getTile(x, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x + 1, y - 1) && board.getTile(x + 1, y - 1).isBomb)
			bombcount++;
		if (board.tileExists(x - 1, y + 1) && board.getTile(x - 1, y + 1).isBomb)
			bombcount++;
		return bombcount + "";
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	public void setClicked(boolean clicked) {
		isClicked = clicked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public boolean isClicked() {
		return isClicked;
	}
	
	public JButton getButton() {
		return button;
	}
}
