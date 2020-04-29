package finalproject;

public class Checker {
	public static char player1 = '\u24CD';
//	public static char player1 = 'x';
	public static char player2 = '\u24C4';
//	public static char player2 = 'o';
	
	public int x;
	public int y;
	public boolean color; // true = x; false = o
	public int number;

	public Checker(int x,int y, boolean color, int number) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.number = number;
	}

	
	public void move(int x,int y) {
		this.x = x;
		this.y = y;
	}

		
	public String toString(){
		if (color) {
			return String.valueOf(player1);
		} else {
			return String.valueOf(player2);
		}
	}

	public String printCoordinates() {
		return("(" + this.x + ", "+ this.y + ")");
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	//checks if its a backwards move based on what the color field of the player is
	public boolean moveBackwards(int targetY, int originalY) {
		if (this.color) { //true = x, false = o
			if((originalY - targetY) > 0) {
				return true;
			}else {
				return false;
			}
		}else{
			if((originalY - targetY) < 0) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	
}
