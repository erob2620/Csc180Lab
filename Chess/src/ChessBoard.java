import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChessBoard {

	private final int BOARD_SIZE = 8;
	private FileIO fio = new FileIO();
	private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private File file;
	public static final Pattern CHESS_PATTERN = Pattern.compile("(?<piece>[A-Za-z][ld0-9]\\s*)(?<position>\\w\\d\\W*\\s*)(?<pos2>\\w*\\d*\\s*)(?<pos3>\\w*\\d*)");

	public static void main(String[] args) throws FileNotFoundException {
		ChessBoard cb = new ChessBoard();
		File file = new File(args[0]);
		
		cb.resetBoard();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		
		try {
			while (true) {
				String newLine = reader.readLine();
				if (newLine == null) break;
				cb.parse(newLine);
			}
			
		}catch(IOException e) {
			System.out.println("A fatal error has occured.");
			e.printStackTrace();
		}

		cb.printBoard();
	}
	
	private void parse(String newLine) {
		Matcher m = CHESS_PATTERN.matcher(newLine);
		
		while(m.find()) {
			String piece = m.group("piece");
			String position = m.group("position");
			String positionTwo = m.group("pos2");
			String positionThree = m.group("pos3");
			
			if (fio.pieces.get(piece.substring(1)) != null && positionTwo.equals("")) {
				piecePlacement(piece, position);
				
			} else if (!positionTwo.equals("")) {
				multiPieceMovement(piece, position, positionTwo,positionThree);
				
			} else {
				pieceMovement(piece, position);
			}
		}
	}

	private void multiPieceMovement(String position, String positionTwo, String positionThree, String positionFour) {
		pieceMovement(position, positionTwo);
		pieceMovement(positionThree, positionFour);
		
		
	}
	private void pieceMovement(String position, String positionTwo) {
		int col = charToNumber(position.charAt(0));
		int row = stringToNum(position);
		
		int newRow = stringToNum(positionTwo);
		int newCol = charToNumber(positionTwo.charAt(0));
		
		if(!boundsCheck(newCol, newRow) || !boundsCheck(col,row)) {
			System.out.println("The move you tried to make was outside the bounds of the board.");
			
		}else if(board[col][row] == '-'){
			System.out.println("There was no piece to move at the location: " + position);
			
		}else{
			char piece = board[col][row];
			board[col][row] = '-';
			
			placePiece(newRow, newCol, piece);
		}
	}

	private void piecePlacement(String piece, String position) {
		char pieceName = determineSide(piece);
		
		int col = charToNumber(position.charAt(0));
		int row = stringToNum(position);
		
		if(boundsCheck(col, row)) {
			placePiece(row, col, pieceName);
			fio.piecePlacement(piece, position);
			
		}else {
			System.out.println("The move you tried to make was outside the bounds of the board.");
		}
	}

	private void resetBoard() {
		for(int col = 0; col < board.length; col++) {
			for(int row = 0; row < board.length; row++){
				board[row][col] =  '-';
			}
		}
	}
	
	private void printBoard() {
		for(int col = 0; col < board.length; col++) {
			for(int row = 0; row < board.length; row++){
				System.out.print(" " + board[row][col] + " ");
			}
			System.out.println("");
		}
	}
	
	private boolean boundsCheck(int col, int row) {
		return (col < board.length && col >= 0) && (row < board.length && row >= 0);
	}
	
	private int charToNumber(char c) {
		return (int)(c - 'a');
	}
	
	private void placePiece(int row, int col, char name) {
		board[col][invertRow(row)] = name;
	}
	private int invertRow(int row) {
		return (row != 4)? 7 - row: 4;
	}
	private int stringToNum(String position) {
		return Integer.parseInt(position.substring(1,2)) - 1;
	}
	/**
	 * This method returns an upper case version of the piece if it is a white piece and lower case if it is a black piece.
	 * @param piece
	 * @return
	 */
	private char determineSide(String piece) {
		return (piece.substring(1).equals("l"))? Character.toUpperCase(piece.charAt(0)) : Character.toLowerCase(piece.charAt(0));
	}
}
