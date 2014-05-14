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
	HashMap pieces;
	public static final Pattern CHESS_PATTERN = Pattern.compile("([A-Za-z][\\w*])(\\D\\w*)");
	
	public FileIO() {
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
		fio.run(fio);
	}

	public void run(FileIO fio) throws IOException{
		File file = new File("src/ChessInput.txt");
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		
		String newLine = reader.readLine();
		
		while(newLine != null) {
			
			Matcher m = CHESS_PATTERN.matcher(newLine);
			
			while(m.find()) {
				String piece = m.group(1);
				String position = m.group(2);
				
				if(pieces.get(piece.substring(1)) != null) {
					fio.piecePlacement(piece, position);
				}else {
					fio.pieceMovement(piece, position);
				}
			}
			
			newLine = reader.readLine();
		}
	}
	
	public void pieceMovement(String piece, String position) {
		if(position.contains("*")) {
			position = position.substring(1,3);
			System.out.println("Moves the piece at " + piece.toUpperCase() + " to the square at " + position.toUpperCase() 
					+ " and captures the piece at " + position.toUpperCase());
		}else {
			position = position.substring(1,3);
			System.out.println("Moves the piece at " + piece.toUpperCase() + " to the square at " + position.toUpperCase());
		}
	}

	public void piecePlacement(String piece, String position) {
		String pieceName = (String) pieces.get(piece.substring(0,1));
		
		String pieceColor = (String) pieces.get(piece.substring(1));
		
		System.out.println("Place the " + pieceColor + " " + pieceName + " on " + position.toUpperCase());
		
	}
}
