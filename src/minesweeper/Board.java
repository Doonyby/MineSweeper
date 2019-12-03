package minesweeper;

import java.awt.event.MouseEvent;
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
	}
	
	public void buildBoard() {
		System.out.println("Building board");
		mineArr.clear();
		for(int i=0; i < difficultyNumber; i++) {
			mineArr.add(new Mine(i, false, 0, false, false));
		}
		this.plantBombs();
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
	
	public void evaluateBoard(Mine mine) {
		mine.setOpen(true);
		mineArr.get(mine.getId() + 1).setOpen(true);
		System.out.println("Evaluating board");
		if(mine.isOpen || mine.isBomb) {
			return;
		}
		
		int i = mine.getId();
		if(i % 10 == 0) {
			if((i+1) < difficultyNumber) {
				if(mineArr.get(i+1).getBombTouchCount() == 0) {
					mineArr.get(i+1).setOpen(true);
					evaluateBoard(mineArr.get(i+1));
				} else if(mineArr.get(i+1).getBombTouchCount() > 0) {
					mineArr.get(i+1).setOpen(true);
				}
			}
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					mineArr.get(i+10).setOpen(true);
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					mineArr.get(i-10).setOpen(true);
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		} else if(i % 10 == 9) {
			if((i-1) >= 0) {
				if(mineArr.get(i-1).getBombTouchCount() == 0) {
					mineArr.get(i-1).setOpen(true);
					evaluateBoard(mineArr.get(i-1));
				} else if(mineArr.get(i-1).getBombTouchCount() > 0) {
					mineArr.get(i-1).setOpen(true);
				}
			}
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					mineArr.get(i+10).setOpen(true);
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					mineArr.get(i-10).setOpen(true);
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		} else {
			if((i+1) < difficultyNumber) {
				if(mineArr.get(i+1).getBombTouchCount() == 0) {
					mineArr.get(i+1).setOpen(true);
					evaluateBoard(mineArr.get(i+1));
				} else if(mineArr.get(i+1).getBombTouchCount() > 0) {
					mineArr.get(i+1).setOpen(true);
				}
			}
			if((i-1) >= 0) {
				if(mineArr.get(i-1).getBombTouchCount() == 0) {
					mineArr.get(i-1).setOpen(true);
					evaluateBoard(mineArr.get(i-1));
				} else if(mineArr.get(i-1).getBombTouchCount() > 0) {
					mineArr.get(i-1).setOpen(true);
				}
			}
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					mineArr.get(i+10).setOpen(true);
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					mineArr.get(i-10).setOpen(true);
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		}
	}
	
	public void onBoardChange(Mine mine) {
		System.out.println("Changing board");
		if(mine.isBomb) {
			System.out.println("You lose");
		} else if(mine.getBombTouchCount() > 0) {
			mine.setOpen(true);
			System.out.println("Should be opening mine to see number");
		} else if(mine.getBombTouchCount() == 0) {
			System.out.println("Evaluating board");
			this.evaluateBoard(mine);
		} else if(mine.isOpen) {
			System.out.println("Already chose this mine....");
		}
	}
	
	public void startGame() {
		System.out.println("Starting game");
		this.buildBoard();
	}
	
	public void resetGame() {
		System.out.println("Reset game");
		this.buildBoard();
	}

}
