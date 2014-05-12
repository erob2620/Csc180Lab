import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class FileIO {
	HashMap pieces = new HashMap();
	
	public FileIO() {
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
		
		File file = new File("src/chessArgs");
		
		FileInputStream fis = new FileInputStream("file");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		
		String newLine = reader.readLine();
		
		while(newLine != null) {
			
			newLine = reader.readLine();
		}
	}
}
