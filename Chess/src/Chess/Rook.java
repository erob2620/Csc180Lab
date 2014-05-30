package Chess;

public class Rook extends Piece{

	public Rook(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	@Override
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		
		
		if((col == newCol && row != newRow) || (row == newRow && col != newCol)) {
			if(!isBlocked(col, row, newCol, newRow, board)) {
				return true;
			}
		}
		return false;
		
	}
	private boolean isBlocked(int col, int row, int newCol, int newRow, Piece[][] board) {
		int deltaY = newRow - row;
		int deltaX = newCol - col;
		
		if(deltaY == 0) return horizontalBlock(row, deltaX, newCol, col , board);
		
		if(deltaX == 0) return verticalBlock(col, deltaY, newRow, row, board);
		
		return false;
		
	}
	private boolean horizontalBlock(int row, int deltaX, int newCol, int col,Piece[][] board) {
		if(deltaX < 0) {
			for(int i = col - 1; i > newCol; i--) {
				Piece boardCheck = board[i][row];
				
				if(boardCheck != null && (sameColor(boardCheck))) {
					blockedPiece(boardCheck);
					return true;
				}else if(boardCheck != null && (!sameColor(boardCheck))) {
					return false;
				}
			}
		}
		if(deltaX > 0) {
			for(int i = col + 1; i < newCol; i++) {
				Piece boardCheck = board[i][row];
				
				if(boardCheck != null && (sameColor(boardCheck))) {
					blockedPiece(boardCheck);
					return true;
				}else if(boardCheck != null && (!sameColor(boardCheck))) {
					return false;
				}
			}
		}
		
		return false;
	}
	private boolean verticalBlock(int col, int deltaY, int newRow, int row, Piece[][] board) {
		if(deltaY < 0) {
			for(int i = row - 1 ; i >= newRow; i--) {
				Piece boardCheck = board[col][i];
				
				if(boardCheck != null && (sameColor(boardCheck))) {
					blockedPiece(boardCheck);
					return true;
				}else if(boardCheck != null && (!sameColor(boardCheck))) {
					return false;
				}
			}
		}else {
			for(int i = row + 1; i <= newRow; i++) {
				Piece boardCheck = board[col][i];
				
				if(boardCheck != null && (sameColor(boardCheck))) {
					blockedPiece(boardCheck);
					return true;
				}else if(boardCheck != null && (!sameColor(boardCheck))) {
					return false;
				}
			}
		}
		
		return false;
	}
	private boolean sameColor(Piece boardCheck) {
		return (boardCheck.getColor() == this.getColor())? true: false;
	}

}
