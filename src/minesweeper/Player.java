package minesweeper;

public class Player extends Game {

	public Player(Difficulty d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public void mineClick() {
		System.out.println("Clicked mine");
	}
	
	public void changeDifficulty() {
		System.out.println("Changed difficulty to " + Difficulty.EASY + Difficulty.MEDIUM + Difficulty.HARD);
	}
	
	public void resetGameClick() {
		System.out.println("Reset click");
	}
	
	public void startGameClick() {
		System.out.println("Starting game click");
	}
	
	

}
