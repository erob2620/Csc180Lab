package Chess;

import Chess.Piece;

public class Knight extends Piece{

	public Knight(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	@Override
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		int deltaY = newRow - row;
		int deltaX = newCol - col;
		
		return (verticalMovement(deltaY, deltaX) || horizontalMovement(deltaX, deltaY))? true: false;		
	}
	private boolean verticalMovement(int deltaY, int deltaX) {
		return((deltaY == 2 || deltaY == -2) && (deltaX == 1 || deltaX == -1))? true: false;
	}
	private boolean horizontalMovement(int deltaX, int deltaY) {
		return ((deltaX == 2 || deltaX == -2) && (deltaY == 1 || deltaY == -1))? true : false;
	}

}
