package finalproject;

public class Checker {
	
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
			String s = "x ";
			return s;
		} else {
			String s = "o ";
			return s;
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
