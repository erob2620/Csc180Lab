package Chess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
	public static void main(String[] args) {
		Controller c = new Controller();
		ChessBoard cb = new ChessBoard();
		File movements = new File(args[1]);		
		
		cb.initializeBoard();
		c.fileReader(cb, movements);
	}

	private void fileReader(ChessBoard cb, File initialization) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(initialization);
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
		} catch (FileNotFoundException e1) {
			System.out.println("Whoops something is wrong with the file.");
			e1.printStackTrace();
			
		}finally {
			cb.printBoard();
			cb.setLineNumber(0);
			
			System.out.println("Everything was read from the file.");
		}
	}
}
