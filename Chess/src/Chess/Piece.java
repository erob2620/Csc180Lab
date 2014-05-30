package Chess;

public class Piece {

	private int row;
	private int col;
	
	private String color;
	private String name;
	
	public Piece(int row, int col, String color, String name) {
		this.row = row;
		this.col = col;
		
		this.color = color;
		
		this.name = name;
	}

	
	public int getRow() {
		return row;
	}


	public int getCol() {
		return col;
	}


	public String getName() {
		return (color.equals("l"))? name.toUpperCase() : name.toLowerCase();
	}
	public String toString() {
		return FileIO.pieces.get(color) + " " + FileIO.pieces.get(name);

	}
	public boolean isValidMove(int col, int row, int newCol, int newRow, Piece[][] board) {
		return false;
	}
	
	public boolean isWhitePiece() {
		return (color.equals("l"))? true: false;
	}
	public String getColor() {
		return color;
	}
	public void blockedPiece(Piece p) {
		System.out.println("The " + p.toString() + " was in your way. Please try another move.\n");
	}

}
