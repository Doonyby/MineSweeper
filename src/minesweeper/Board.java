package minesweeper;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Board extends Game {
	
	ArrayList<Mine> mineArr = new ArrayList<Mine>();
	int difficultyNumber;
	int numberOfBombs;
	boolean youWon = false;
	boolean youLost = false;
	
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
		for(int i = 0; i < numberOfBombs; i++) {
			int r;
			do {
				r = (int)(Math.random() * difficultyNumber-1) + 1;
			} while(mineArr.get(r).isBomb()); 
			mineArr.get(r).setBomb(true);
		}	
	}
	
	public void evaluateBoard(Mine mine) {
		if(mine.isOpen) {
			return;
		}
		
		mine.setOpen(true);
		
		int i = mine.getId();
		if(i % 10 == 0) {
			if((i+1) < difficultyNumber) {
				if(mineArr.get(i+1).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i+1));
				} else if(mineArr.get(i+1).getBombTouchCount() > 0) {
					mineArr.get(i+1).setOpen(true);
				}
			}
			
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		} 
		else if(i % 10 == 9) {
			if((i-1) >= 0) {
				if(mineArr.get(i-1).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i-1));
				} else if(mineArr.get(i-1).getBombTouchCount() > 0) {
					mineArr.get(i-1).setOpen(true);
				}
			}
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		} else {
			if((i+1) < difficultyNumber) {
				if(mineArr.get(i+1).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i+1));
				} else if(mineArr.get(i+1).getBombTouchCount() > 0) {
					mineArr.get(i+1).setOpen(true);
				}
			}
			if((i-1) >= 0) {
				if(mineArr.get(i-1).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i-1));
				} else if(mineArr.get(i-1).getBombTouchCount() > 0) {
					mineArr.get(i-1).setOpen(true);
				}
			}
			if((i+10) < difficultyNumber) {
				if(mineArr.get(i+10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i+10));
				} else if(mineArr.get(i+10).getBombTouchCount() > 0) {
					mineArr.get(i+10).setOpen(true);
				}
			}
			if((i-10) >= 0) {
				if(mineArr.get(i-10).getBombTouchCount() == 0) {
					evaluateBoard(mineArr.get(i-10));
				} else if(mineArr.get(i-10).getBombTouchCount() > 0) {
					mineArr.get(i-10).setOpen(true);
				}
			}
		}
	}
	
	public void onBoardChange(Mine mine) {
		if(mine.isOpen) {
			return;
		} else if(mine.isBomb) {
			openBombs();
		} else if(mine.getBombTouchCount() > 0) {
			mine.setOpen(true);
			checkWin();
		} else if(mine.getBombTouchCount() == 0) {
			this.evaluateBoard(mine);
		} 
	}
	
	public void checkWin() {
		boolean somethingIsStillClosed = false;
		for (int j=0; j < mineArr.size(); j++) {
			if(!mineArr.get(j).isOpen && !mineArr.get(j).isBomb) {
				somethingIsStillClosed = true;
			};
		} 
		if(!somethingIsStillClosed) {
			System.out.println("You win!!!!");
			for (int j=0; j < mineArr.size(); j++) {
				if(mineArr.get(j).isBomb) {
					mineArr.get(j).setFlagged(true);
				};
			} 
			youWon = true;
		}
	}
	
	public void openBombs() {
		System.out.println("You lose");
		for (int j=0; j < mineArr.size(); j++) {
			if(mineArr.get(j).isBomb) {
				mineArr.get(j).setOpen(true);
			};
		}
		youLost = true;
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
