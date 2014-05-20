import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileIO {
	public static HashMap pieces;
	public static final Pattern CHESS_PATTERN = Pattern.compile("(?<piece>[A-Za-z][ld0-9]\\s*)(?<position>\\w\\d\\W*\\s*)(?<pos2>\\w*\\d*\\s*)(?<pos3>\\w*\\d*)");
	
	static {
		pieces = new HashMap();
		
		pieces.put("K", "King");
		pieces.put("Q", "Queen");
		pieces.put("B", "Bishop");
		pieces.put("N", "Knight");
		pieces.put("R", "Rook");
		pieces.put("P", "Pawn");
		pieces.put("l", "white");
		pieces.put("d", "black");
	}
	
	public static void main(String[] args) throws IOException {
		FileIO fio = new FileIO();
		File file = new File(args[0]);
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		
		try {
			while (true) {
				String newLine = reader.readLine();
				if (newLine == null) break;
				fio.parse(newLine);
			}
			
		}catch(IOException e) {
			System.out.println("A fatal error has occured.");
			e.printStackTrace();
		}
	}

	public void parse( String newLine) {

		Matcher m = CHESS_PATTERN.matcher(newLine);

		while (m.find()) {
			
			String piece = m.group("piece");
			String position = m.group("position");
			String positionTwo = m.group("pos2");
			String positionThree = m.group("pos3");

			if (pieces.get(piece.substring(1)) != null && positionTwo.equals("")) {
				piecePlacement(piece, position);
				
			} else if (!positionTwo.equals("")) {
				multiPieceMovement(piece, position, positionTwo,positionThree);
				
			} else {
				pieceMovement(piece, position);
			}
		}
	}
	public void multiPieceMovement(String piece, String position, String positionTwo, String positionThree) {
		String firstPosition = piece.substring(0,2);
		String secondPosition = position.substring(0,2);
		String thirdPosition = positionTwo.substring(0,2);
		String fourthPosition = positionThree.substring(0,2);
		
		System.out.println("Moves the king from " + firstPosition + " to " + secondPosition + " and moves the rook from "  
				+ thirdPosition + " to " + fourthPosition);
	}
	
	public void pieceMovement(String piece, String position) {
		if(position.contains("*")) {
			
			String initialPosition = piece.substring(0,2).toUpperCase();
			String finalPosition = position.substring(0,2).toUpperCase();
			
			System.out.println("Moves the piece at " + initialPosition + " to the square at " + finalPosition
					+ " and captures the piece at " + finalPosition);
		}else {
			piece = piece.substring(0,2);
			System.out.println("Moves the piece at " + piece.toUpperCase() + " to the square at " + position.toUpperCase());
		}
	}

	public void piecePlacement(String piece, String position) {
		String pieceName = (String) pieces.get(piece.substring(0,1));	
		String pieceColor = (String) pieces.get(piece.substring(1));
		
		System.out.println("Place the " + pieceColor + " " + pieceName + " on " + position.toUpperCase());
		
	}
}
