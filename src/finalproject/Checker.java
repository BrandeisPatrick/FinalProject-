package finalproject;

public class Checker {
	
	public int row;
	public int col;
	public boolean color; // true = x; false = o
	public int number;

	public Checker(int row,int col, boolean color, int number) {
		this.row = col;
		this.row = col;
		this.color = color;
		this.number = number;
	}


	
	public void Move(int row,int col) {
		this.row = row;
		this.col = col;
	}
	
	public boolean canMove() {
		
	}
	
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public boolean getColor() {
		return this.color;
	}
}
