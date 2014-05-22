
public class Piece {

	private int col;
	private int row;
	
	private String color;
	private char name;
	
	public Piece(int col, int row, String color, char name) {
		this.col = col;
		this.row = row;
		this.color = color;
		
		this.name = determineSide(color, name);
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	private char determineSide(String color, char name) {
		return (color.equals("l"))? Character.toUpperCase(name) : Character.toLowerCase(name);
	}

}
