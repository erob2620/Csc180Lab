
public class ChessBoard {

	char[][] board = new char[8][8];
	

	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard();
		
		cb.resetBoard();
		
		//read in from a file, and parse out the piece
		//then separate the position from the piece itself, and use determine side method to make upper or lower case
		// after that return the first char as a num and the second char as a num
		// make a new piece object with the right row and column
		
		
		
		cb.printBoard();
	}
	
	public void resetBoard() {
		for(int col = 0; col < 8; col++) {
			for(int row = 0; row < 8; row++){
				board[row][col] =  '-';
			}
		}
	}
	
	public void printBoard() {
		for(int col = 0; col < 8; col++) {
			for(int row = 0; row < 8; row++){
				System.out.print(" " + board[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	public boolean boundsCheck(int col, int row) {
		return (col < board.length && col >= 0) && (row < board.length && row >= 0);
	}
	
	public int charToNumber(char c) {
		return (int)(c - 'A');
	}
	
	public void placePiece(int col, int row, char name) {
		if(boundsCheck(col, row)) {
			board[col][row] = name;
		}
	}
	/**
	 * This method returns an upper case version of the piece if it is a white piece and lower case if it is a black piece.
	 * @param piece
	 * @return
	 */
	public char determineSide(String piece) {
		return (piece.substring(1).equals("l"))? Character.toUpperCase(piece.charAt(0)) : Character.toLowerCase(piece.charAt(0));
	}
}
