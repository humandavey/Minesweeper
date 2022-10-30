public enum GameSetting {
	BEGINNER(8, 8, 10),
	INTERMEDIATE(16, 16, 40),
	EXPERT(30, 16, 99);

	private final int x;
	private final int y;
	private final int bombs;

	GameSetting(int x, int y, int bombs) {
		this.x = x;
		this.y = y;
		this.bombs = bombs;
	}

	public int getBombs() {
		return bombs;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
