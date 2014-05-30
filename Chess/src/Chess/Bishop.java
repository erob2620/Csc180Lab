package Chess;

import Chess.Piece;

public class Bishop extends Piece{

	public Bishop(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		
		if(findSlope(row, col, newRow, newCol) == 1 || findSlope(row, col, newRow, newCol) == -1) {
			if(!isBlocked(col, row, newCol, newRow, board))
			return true;
		}
		
		return false;
	}
	public boolean isBlocked(int col, int row, int newCol, int newRow, Piece[][] board) {
		
		if(leftDiagonalCheck(row, col, newRow, newCol, board) || rightDiagonalCheck(row, col, newRow, newCol, board)
				|| leftDownDiagonalCheck(row, col, newRow, newCol, board) || rightDownDiagonalCheck(row, col, newRow, newCol, board)) {
			return true;
		}
		
		return false;
	}
	private double findSlope(int row, int col, int newRow, int newCol) {	
		double deltaX = newCol - col;
		
		if(deltaX != 0) {
			return ((newRow - row) / (newCol - col));
		}
		return 10;
	}
	private boolean leftDiagonalCheck(int row, int col, int newRow, int newCol, Piece[][] board) {
		for(int i = row - 1; i >= newRow; i--) {
			Piece tmpPiece = board[--col][i];
			
			if(tmpPiece != null && sameColor(tmpPiece)){
				blockedPiece(tmpPiece);
				return true;
			}
		}
		
		return false;
		
	}
	private boolean rightDiagonalCheck(int row, int col, int newRow, int newCol, Piece[][] board) {
		for(int i = row - 1; i >= newRow; i--) {
			Piece tmpPiece = board[++col][i];
			
			if(tmpPiece != null && sameColor(tmpPiece)){
				blockedPiece(tmpPiece);
				return true;
			}
		}
		
		return false;
		
	}
	private boolean leftDownDiagonalCheck(int row, int col, int newRow, int newCol, Piece[][] board) {
		for(int i = row + 1; i <= newRow; i++) {
			Piece tmpPiece = board[--col][i];
			
			if(tmpPiece != null && sameColor(tmpPiece)){
				blockedPiece(tmpPiece);
				return true;
			}
		}
		
		return false;
		
	}
	private boolean rightDownDiagonalCheck(int row, int col, int newRow, int newCol, Piece[][] board) {
		for(int i = row + 1; i <= newRow; i++) {
			Piece tmpPiece = board[++col][i];
			
			if(tmpPiece != null && sameColor(tmpPiece)){
				blockedPiece(tmpPiece);
				return true;
			}
		}
		
		return false;
		
	}
	public boolean sameColor(Piece boardCheck) {
		return (boardCheck.getColor() == this.getColor())? true: false;
	}

}
	