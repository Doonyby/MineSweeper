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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	public int getBombTouchCount() {
		return bombTouchCount;
	}

	public void setBombTouchCount(int bombTouchCount) {
		this.bombTouchCount = bombTouchCount;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}

	public String toString() {
		return "ID: " + this.id + " Bomb: " + this.isBomb + ", Touch: " + this.bombTouchCount + ", Flagged: " + isFlagged;
	}

}
