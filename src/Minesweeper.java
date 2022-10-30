import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Minesweeper implements MouseListener {

	private final JFrame frame;
	private final Board board;
	private static Minesweeper instance;
	private boolean gameOver;
	private final int scale = 60;
	
	public Minesweeper(GameSetting gs) {
		instance = this;
		frame = new JFrame("Minesweeper");
		board = new Board(gs.getX(), gs.getY(), gs.getBombs());
		
		frame.setSize(scale * gs.getX(), scale * gs.getY());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		GridLayout layout = new GridLayout(gs.getX(), gs.getY());
		frame.setLayout(layout);
	}

	public int getScale() {
		return scale;
	}

	public Board getBoard() {
		return board;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton b) {
			if (!gameOver) {
				int x = Integer.parseInt(b.getActionCommand().split("\\|")[0]) / scale;
				int y = Integer.parseInt(b.getActionCommand().split("\\|")[1]) / scale;

				Tile t = board.getTile(x, y);
				if (e.getButton() == 1) {
					if (t.getValueBypass().equals("X")) {
						showBombs(t);
						showFalseMarks();
						gameOver = true;
					} else if (t.getValueBypass().equals("0")) {
						b.setIcon(board.getIcon("clicked"));
						t.setClicked(true);
						removeChained(x, y, true);
					} else {
						t.setClicked(true);
						b.setIcon(board.getIcon("clicked_" + t.getValue()));
					}
				} else if (e.getButton() == 3) {
					if (!t.isClicked()) {
						if (t.isMarked()) {
							t.setMarked(false);
							b.setIcon(board.getIcon("unclicked"));
						} else {
							t.setMarked(true);
							b.setIcon(board.getIcon("flag"));
						}
					}
				}
				if (solved()) {
					gameOver = true;
					System.exit(0);
				}
			}
		}
	}

	public void showBombs(Tile tile) {
		for (Tile t : board.getBombs()) {
			t.setClicked(true);
			t.getButton().setIcon(board.getIcon("bomb"));
		}
		tile.setClicked(true);
		tile.getButton().setIcon(board.getIcon("clicked_x"));
	}

	public void showFalseMarks() {
		for (Tile t : board.getTiles()) {
			if (t.isMarked() && !t.isBomb()) {
				t.setClicked(true);
				t.getButton().setIcon(board.getIcon("flag_false"));
			}
		}
	}

	public boolean solved() {
		for (Tile tile : board.getTiles()) {
			if (!tile.isClicked() && !tile.isBomb()) {
				return false;
			}
		}
		return true;
	}

	public void removeChained(int x, int y, boolean recursive) {
		if (board.tileExists(x + 1, y + 1)) {
			Tile t = board.getTile(x + 1, y + 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x + 1, y + 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x + 1, y)) {
			Tile t = board.getTile(x + 1, y);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x + 1, y, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x, y + 1)) {
			Tile t = board.getTile(x, y + 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x, y + 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x - 1, y - 1)) {
			Tile t = board.getTile(x - 1, y - 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x - 1, y - 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x - 1, y)) {
			Tile t = board.getTile(x - 1, y);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x - 1, y, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x, y - 1)) {
			Tile t = board.getTile(x, y - 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x, y - 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x - 1, y + 1)) {
			Tile t = board.getTile(x - 1, y + 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x - 1, y + 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
		if (board.tileExists(x + 1, y - 1)) {
			Tile t = board.getTile(x + 1, y - 1);
			if (t.getValueBypass().equalsIgnoreCase("0") && !t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked"));
				if (recursive)
					removeChained(x + 1, y - 1, true);
			} else if (!t.isClicked()) {
				t.setClicked(true);
				t.getButton().setIcon(getBoard().getIcon("clicked_" + t.getValue()));
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public static Minesweeper getInstance() {
		return instance;
	}
}
