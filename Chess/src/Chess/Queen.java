package Chess;

import Chess.Piece;

public class Queen extends Piece{

	public Queen(int row, int col, String color, String name) {
		super(row, col, color, name);
	}
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
//		if(isValidLateralMove(col, row , newCol, newRow)) {
//			if(!isBlocked(col, row, newCol, newRow, board)) {
//				return true;
//			}
//		}else if(super.isValidMove(col, row, newCol, newRow, board)) {
//			if(!super.isBlocked(col, row, newCol, newRow, board)) {
//			return true;
//			}
//		}
//		
//		return false;
		
		return (new Rook(col, row, "l", "R").isValidMove(col, row, newCol, newRow, board) || 
				new Bishop(col, row, "l", "B").isValidMove(col, row, newCol, newRow, board));
				
		
	}
//	public boolean isBlocked(int col, int row, int newCol, int newRow, Piece[][] board) {
//		if(isLateralBlocked(col, row, newCol, newRow, board)) {
//			return true;
//		}
//		return false;
//	}
//	private boolean isLateralBlocked(int col, int row, int newCol, int newRow,Piece[][] board) {
//		int deltaX = newCol - col;
//		int deltaY = newRow - row;
//		
//		if(deltaX == 0) return verticalBlock(col, deltaY, newRow, row, board);
//		
//		if(deltaY == 0) return horizontalBlock(row, deltaX, newCol, col, board);
//		
//		return false;
//	}
//	private boolean horizontalBlock(int row, int deltaX, int newCol, int col, Piece[][] board) {
//		if(deltaX != 0 && deltaX < 0) {
//			for(int i = col - 1; i > newCol; i--) {
//				Piece boardCheck = board[i][row];
//				
//				if(boardCheck != null && (sameColor(boardCheck))) {
//					blockedPiece(boardCheck);
//					return true;
//				}else if(boardCheck != null && (!sameColor(boardCheck))) {
//					return false;
//				}
//			}
//		}
//		if(deltaX != 0 && deltaX > 0) {
//			for(int i = col + 1; i < newCol; i++) {
//				Piece boardCheck = board[i][row];
//				
//				if(boardCheck != null && (sameColor(boardCheck))) {
//					blockedPiece(boardCheck);
//					return true;
//				}else if(boardCheck != null && (!sameColor(boardCheck))) {
//					return false;
//				}
//			}
//		}
//		return false;
//	}
//	private boolean verticalBlock(int col, int deltaY, int newRow, int row, Piece[][] board) {
//		if(deltaY != 0 && deltaY < 0) {
//			for(row = row - 1; row > newRow; row--) {
//				Piece boardCheck = board[col][row];
//				
//				if(boardCheck != null && (sameColor(boardCheck))) {
//					blockedPiece(boardCheck);
//					return true;
//				}else if(boardCheck != null && (!sameColor(boardCheck))) {
//					return false;
//				}
//			}
//		}
//		if(deltaY != 0 && deltaY > 0) {
//			for(row = row + 1; row < newRow; row++) {
//				Piece boardCheck = board[col][row];
//				
//				if(boardCheck != null && (sameColor(boardCheck))) {
//					blockedPiece(boardCheck);
//					return true;
//				}else if(boardCheck != null && (!sameColor(boardCheck))) {
//					return false;
//				}
//			}
//		}	
//		return false;	
//	}
//	private boolean isValidLateralMove(int col, int row, int newCol, int newRow) {
//		boolean verical = col == newCol && row != newRow;
//		boolean horizontal = row == newRow & col != newCol;
//		
//		if(verical || horizontal) {
//			return true;
//		}
//		return false;
//	}
}
