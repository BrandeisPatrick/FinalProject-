package finalproject;

/*
 * This class represents a single checker in the game. The char fields are associated with how they appear
 * on the board when the board is printed. The checkers follow the rules of how checkers can move.
 * (x,y) is the coordinates on the board. the x coordinate is the horizontal axis and the y is the vertical axis
 * color tells which player the checker is associated
 * number is associated with how the checkers are set up and how many checkers each player gets
 */
public class Checker {
//	public static char player1 = '\u24CD';
	public static char player1 = 'x';
//	public static char player2 = '\u24C4';
	public static char player2 = 'o';
	
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
	
	public Checker() {
		this.x = 0;
		this.y = 0;
		this.color = false;
		this.number = 0;
		
	}

	
	//moves checker from one spot to another
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
	
	
	//checks if its a backwards move based on what the color field of the player is.
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
