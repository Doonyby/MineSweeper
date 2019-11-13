package minesweeper;

public class Mine {

	int id;
	boolean isBomb;
	int bombTouchCount;
	boolean isFlagged;
	
	public Mine(int id, boolean isBomb, int bombTouchCount, boolean isFlagged) {
		this.id = id;
		this.isBomb = isBomb;
		this.bombTouchCount = bombTouchCount;
		this.isFlagged = isFlagged;
	}
	
	public String toString() {
		return "ID: " + this.id + " Bomb: " + this.isBomb + ", Touch: " + this.bombTouchCount + ", Flagged: " + isFlagged;
	}

}
