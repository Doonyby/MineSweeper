package minesweeper;

public class MineSweeper {

	public static void main(String[] args) {
		Board myBoard = new Board(Difficulty.EASY);
		
		System.out.println(myBoard);
		
		myBoard.mineArr.forEach((n) -> System.out.println(n));

	}

}
