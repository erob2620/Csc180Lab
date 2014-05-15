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
	private static HashMap pieces;
	public static final Pattern CHESS_PATTERN = Pattern.compile("(?<piece>[A-Za-z]\\w\\s*)(?<position>\\w\\d*\\W*\\s*)(?<pos2>\\w*\\w*\\s*)(?<pos3>\\w*\\w*)");
	
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
			String newLine = reader.readLine();
			
			while(newLine != null) {
				fio.run(fio, newLine);
				
				newLine = reader.readLine();
			}
		}catch(IOException e) {
			System.out.println("A fatal error has occured.");
			e.printStackTrace();
		}
	}

	public void run(FileIO fio, String newLine) {

		Matcher m = CHESS_PATTERN.matcher(newLine);

		while (m.find()) {
			
			String piece = m.group("piece");
			String position = m.group("position");
			String positionTwo = m.group("pos2");
			String positionThree = m.group("pos3");

			if (pieces.get(piece.substring(1)) != null && positionTwo.equals("")) {
				fio.piecePlacement(piece, position);
				
			} else if (!positionTwo.equals("")) {
				fio.multiPieceMovement(piece, position, positionTwo,positionThree);
				
			} else {
				fio.pieceMovement(piece, position);
			}
		}
	}
	public void multiPieceMovement(String piece, String position, String positionTwo, String positionThree) {
		piece = piece.substring(0,2);
		position = position.substring(0,2);
		positionTwo = positionTwo.substring(0,2);
		positionThree = positionThree.substring(0,2);
		
		System.out.println("Moves the king from " + piece + " to " + position + " and moves the rook from "  
				+ positionTwo + " to " + positionThree);
	}
	
	public void pieceMovement(String piece, String position) {
		if(position.contains("*")) {
			
			piece = piece.substring(0,2);
			position = position.substring(0,2);
			
			System.out.println("Moves the piece at " + piece.toUpperCase() + " to the square at " + position.toUpperCase() 
					+ " and captures the piece at " + position.toUpperCase());
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
