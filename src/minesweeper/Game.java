package minesweeper;

public class Game {
	
	public Difficulty difficulty;

	public Game(Difficulty d) {
		this.difficulty = d;
	}
	
	public String toString() {
		return "This game is set at " + difficulty;
	}

}
