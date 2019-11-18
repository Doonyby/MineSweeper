package minesweeper;

import java.util.ArrayList;

public class Board extends Game {

	ArrayList<Mine> mineArr = new ArrayList<Mine>();
	int difficultyNumber;
	
	public Board(Difficulty d) {
		super(d);
		switch (d) {
		case EASY:
			this.difficultyNumber = 100;
			break;
		case MEDIUM:
			this.difficultyNumber = 160;
			break;
		case HARD:
			this.difficultyNumber = 200;
			break;
		default:
			this.difficultyNumber = 100;
			break;
		}
		for(int i=0; i < difficultyNumber; i++) {
			mineArr.add(new Mine(i, false, 0, false));
		}
		
	}
	
	public void buildBoard() {
		System.out.println("Building board");
	}
	
	public void plantBombs() {
		System.out.println("Planting bombs");
	}
	
	public void evaluateBoard() {
		System.out.println("Evaluating board");
	}
	
	public void onBoardChange() {
		System.out.println("Changing board");
		this.evaluateBoard();
	}
	
	public void startGame() {
		System.out.println("Starting game");
	}
	
	public void resetGame() {
		System.out.println("Reset game");
	}

}
