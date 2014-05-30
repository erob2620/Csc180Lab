package Chess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChessBoard {

	private final int BOARD_SIZE = 8;
	private FileIO fio = new FileIO();
	private int lineNumber = 0;
	
	private boolean isWhiteTurn = true;
	
	private Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];
	public static final Pattern PLACEMENT_PATTERN = Pattern.compile("(?<piece>[KQBNRP][ld1-8])(?<position>[a-h][1-8])");
	public static final Pattern MOVEMENT_PATTERN = Pattern.compile("(?<position>[a-h][1-8]\\s)(?<positionTwo>[a-h][1-8])");
	public static final Pattern ATTACK_PATTERN = Pattern.compile("(?<position>[a-h][1-8]\\s)(?<positionTwo>[a-h][1-8]*)");
	public static final Pattern MULTIPIECE_PATTERN = Pattern.compile("(?<position>[a-h][1-8]\\s)(?<positionTwo>[a-h][1-8]\\s)(?<pos3>[a-h][1-8]\\s)(?<pos4>[a-h][1-8])");
	
	public void parse(String newLine) {
		Matcher placementMatcher = PLACEMENT_PATTERN.matcher(newLine);
		Matcher movementMatcher = MOVEMENT_PATTERN.matcher(newLine);
		Matcher attackMatcher = ATTACK_PATTERN.matcher(newLine);
		Matcher multiMatcher = MULTIPIECE_PATTERN.matcher(newLine);
		
		lineNumber++;
		
		if(multiMatcher.find()) {
			String position = multiMatcher.group("position");
			String positionTwo = multiMatcher.group("positionTwo");
			String positionThree = multiMatcher.group("pos3");
			String positionFour = multiMatcher.group("pos4");
			
			multiPieceMovement(position, positionTwo, positionThree, positionFour);
			fio.multiPieceMovement(position, positionFour, positionTwo, positionThree);
		}else if (movementMatcher.find()) {
			String position = movementMatcher.group("position");
			String positionTwo = movementMatcher.group("positionTwo");
			
			pieceMovement(position, positionTwo);
			
		}else if (attackMatcher.find()) {
			String position = attackMatcher.group("position");
			String positionTwo = attackMatcher.group("positionTwo");
			
			pieceMovement(position, positionTwo);
			
		}else if (placementMatcher.find()) {
			String piece = placementMatcher.group("piece");
			String position = placementMatcher.group("position");
			
			piecePlacement(piece, position);
		}else {
			System.out.println("There was an error on line: " + lineNumber + ". This was the string recieved: " + newLine);
		}
	}

	private void multiPieceMovement(String position, String positionTwo, String positionThree, String positionFour) {
		pieceMovement(position, positionTwo);
		pieceMovement(positionThree, positionFour);
			
	}
	private void pieceMovement(String position, String positionTwo) {
		int col = charToNumber(position.charAt(0));
		int row = invertRow(stringToNum(position));
		
		int newRow = invertRow(stringToNum(positionTwo));
		int newCol = charToNumber(positionTwo.charAt(0));
	
		 if(board[col][row] != null){
			Piece tmpPiece = board[col][row];
			
			if(correctTurn(tmpPiece)) {
				if(tmpPiece.isValidMove(col, row, newCol, newRow, board)) {
					board[col][row] = null;
					board[newCol][newRow] = tmpPiece;
				
					printBoard();
					System.out.println("Moved the " + tmpPiece.toString() + " from " + position + "to " + positionTwo + "\n");
					
					isWhiteTurn = (isWhiteTurn)? false: true;
				}
			} else {
				System.out.println("Please wait for your turn, and let the other player move.\n");
			}
		}else{
			System.out.println("There was no piece to move at the location: " + position);
		}
	}

	private void piecePlacement(String piece, String position) {
		
		int col = charToNumber(position.charAt(0));
		int row = stringToNum(position);
		Piece p = new Piece(row, col, piece.substring(1), piece.substring(0,1));
				
		System.out.println("Placed the " + p.toString() + " at the location" + position);
		placePiece(p);
			
	}
	public void initializeBoard() {
		String color = "";
		Piece p = null;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board.length; col++) {
				color = (row < 2)? "l" : "d";
				if(row == 1 || row == 6) {
					p = new Pawn(row, col, color, "P");
				}
				
				if(row == 0 || row == 7) {
					switch(col) {
						
						case 0:
						case 7:
							p = new Rook(row, col, color, "R");
							break;
						case 1:
						case 6:
							p = new Knight(row, col, color, "N");
							break;
						case 2: 
						case 5:
							p = new Bishop(row, col, color, "B");
							break;
						case 3: 
							p = new Queen(row, col, color, "Q");
							break;
						case 4:
							p = new King(row, col, color, "K");
							break;
					}
				}
				placePiece(p);

			}
		}
	}
	public void printBoard() {
		for(int col = 0; col < board.length; col++) {
			for(int row = -1 ; row < board.length; row++){
				if(row == -1) {
					System.out.print(board.length - col + "");
				}else {
					if(board[row][col] == null) {
						System.out.print(" " + "-" + " ");
					}else {
						System.out.print(" " + board[row][col].getName() + " ");
					}
				}
			}
			System.out.println("");
		}
		System.out.println("  " + printLetters());
	}
	
	private String printLetters() {
		String result = "";
		for(char ch = 'A'; ch < (char)(board.length + 'A'); ch++) {
			result += ch + "  ";
		}
		return result;
	}

	private boolean correctTurn(Piece tmpPiece) {
		return (isWhiteTurn == tmpPiece.isWhitePiece())? true: false;
	}
	private int charToNumber(char c) {
		return (int)(c - 'a');
	}
	
	private void placePiece(Piece p) {
		board[p.getCol()][invertRow(p.getRow())] = p;
	}
	private int invertRow(int row) {
		return  7 - row;
	}
	private int stringToNum(String position) {
		return Integer.parseInt(position.substring(1,2)) - 1;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
