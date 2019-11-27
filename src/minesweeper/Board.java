package minesweeper;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Board extends Game {
	
	ArrayList<Mine> mineArr = new ArrayList<Mine>();
	int difficultyNumber;
	int numberOfBombs;
	
	public Board(Difficulty d) {
		super(d);
		switch (d) {
		case EASY:
			this.difficultyNumber = 100;
			this.numberOfBombs = 5;
			break;
		case MEDIUM:
			this.difficultyNumber = 160;
			this.numberOfBombs = 10;
			break;
		case HARD:
			this.difficultyNumber = 200;
			this.numberOfBombs = 15;
			break;
		default:
			this.difficultyNumber = 100;
			this.numberOfBombs = 5;
			break;
		}
		for(int i=0; i < difficultyNumber; i++) {
			mineArr.add(new Mine(i, false, 0, false, false));
		}
	}
	
	public void buildBoard() {
		System.out.println("Building board");
		for(int i = 0; i < difficultyNumber; i++) {
			if(mineArr.get(i).isBomb()) {
				if(i % 10 == 0) {
					if((i+1) < difficultyNumber) {
						mineArr.get(i+1).setBombTouchCount(mineArr.get(i+1).getBombTouchCount() + 1);
					}
					if((i+10) < difficultyNumber) {
						mineArr.get(i+10).setBombTouchCount(mineArr.get(i+10).getBombTouchCount() + 1);
					}
					if((i-10) >= 0) {
						mineArr.get(i-10).setBombTouchCount(mineArr.get(i-10).getBombTouchCount() + 1);
					}
					if((i+10+1) < difficultyNumber) {
						mineArr.get(i+10+1).setBombTouchCount(mineArr.get(i+10+1).getBombTouchCount() + 1);
					}
					if((i-10+1) >= 0) {
						mineArr.get(i-10+1).setBombTouchCount(mineArr.get(i-10+1).getBombTouchCount() + 1);
					}
				} else if(i % 10 == 9) {
					if((i-1) >= 0) {
						mineArr.get(i-1).setBombTouchCount(mineArr.get(i-1).getBombTouchCount() + 1);
					}
					if((i+10) < difficultyNumber) {
						mineArr.get(i+10).setBombTouchCount(mineArr.get(i+10).getBombTouchCount() + 1);
					}
					if((i-10) >= 0) {
						mineArr.get(i-10).setBombTouchCount(mineArr.get(i-10).getBombTouchCount() + 1);
					}
					if((i+10-1) < difficultyNumber) {
						mineArr.get(i+10-1).setBombTouchCount(mineArr.get(i+10-1).getBombTouchCount() + 1);
					}
					if((i-10-1) >= 0) {
						mineArr.get(i-10-1).setBombTouchCount(mineArr.get(i-10-1).getBombTouchCount() + 1);
					}
				} else {
					if((i+1) < difficultyNumber) {
						mineArr.get(i+1).setBombTouchCount(mineArr.get(i+1).getBombTouchCount() + 1);
					}
					if((i-1) >= 0) {
						mineArr.get(i-1).setBombTouchCount(mineArr.get(i-1).getBombTouchCount() + 1);
					}
					if((i+10) < difficultyNumber) {
						mineArr.get(i+10).setBombTouchCount(mineArr.get(i+10).getBombTouchCount() + 1);
					}
					if((i-10) >= 0) {
						mineArr.get(i-10).setBombTouchCount(mineArr.get(i-10).getBombTouchCount() + 1);
					}
					if((i+10+1) < difficultyNumber) {
						mineArr.get(i+10+1).setBombTouchCount(mineArr.get(i+10+1).getBombTouchCount() + 1);
					}
					if((i+10-1) < difficultyNumber) {
						mineArr.get(i+10-1).setBombTouchCount(mineArr.get(i+10-1).getBombTouchCount() + 1);
					}
					if((i-10+1) >= 0) {
						mineArr.get(i-10+1).setBombTouchCount(mineArr.get(i-10+1).getBombTouchCount() + 1);
					}
					if((i-10-1) >= 0) {
						mineArr.get(i-10-1).setBombTouchCount(mineArr.get(i-10-1).getBombTouchCount() + 1);
					}
				}
			}
		}
	}
	
	public void plantBombs() {
		System.out.println("Planting bombs");
		for(int i = 0; i < numberOfBombs; i++) {
			int r;
			do {
				r = (int)(Math.random() * difficultyNumber-1) + 1;
			} while(mineArr.get(r).isBomb()); 
			mineArr.get(r).setBomb(true);
		}	
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
		this.plantBombs();
		this.buildBoard();
	}
	
	public void resetGame() {
		System.out.println("Reset game");
		this.plantBombs();
		this.buildBoard();
	}

}
