package Chess;

import Chess.Piece;

public class King extends Piece{

	public King(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		int deltaY = newRow - row;
		int deltaX = newCol - col;
		
		if(vertical(deltaY) && horizontal(deltaX)) {
			if(!isBlocked(newRow, newCol, board)) {
				return true;
			}
		}
		return false;
		
	}
	private boolean vertical(int deltaY) {
		return deltaY < 2 && deltaY > -2;
	}
	private boolean horizontal(int deltaX) {
		return deltaX < 2 && deltaX > -2;
	}
	private boolean isBlocked(int newRow, int newCol, Piece[][] board) {
		Piece tmpPiece = board[newCol][newRow];
		
		return (tmpPiece != null && sameColor(tmpPiece))? true: false;
	}
	private boolean sameColor(Piece boardCheck) {
		return (boardCheck.getColor() == this.getColor())? true: false;
	}

}
