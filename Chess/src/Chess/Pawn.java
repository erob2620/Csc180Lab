package Chess;
import Chess.Piece;

public class Pawn extends Piece{

	private boolean firstMove = true;
	
	public Pawn(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		int deltaX = newCol - col;
		int deltaY = newRow - row;
		
		if((deltaX == -1 || deltaX == 1) && (deltaY == 1 || deltaY == -1)) {
			return isValidAttack(newCol, newRow, board);
			
		}else if((deltaY == 2 || deltaY == -2) && firstMove) {
			firstMove = false;
			return true;
		}else if( (deltaY == 2 || deltaY == -2) && !firstMove) {
			return false;
		}else if( deltaY == 1 || deltaY == -1) {
			firstMove = false;
			return true;
		}else {
			return false;
		}		
	}
	private boolean isValidAttack(int newCol, int newRow, Piece[][] board) {
		return (board[newCol][newRow] != null && !sameColor(board[newCol][newRow]))? true: false;
	}
	
	private boolean sameColor(Piece boardCheck) {
		return (boardCheck.getColor() == this.getColor())? true: false;
	}

}
