package finalproject;

public class Checker {
	
	public int x;
	public int y;
	public boolean color; // true = x; false = o
	public int number;
	public boolean terminated = false;

	public Checker(int x,int y, boolean color, int number) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.number = number;
		this.terminated = false;
	}
	
	//default constructor
	public Checker(boolean color) {
		this.x = 0;
		this.y = 0;
		this.color = true; //x
		this.number = 1;
		this.terminated = false;
	}


	
	public void Move(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
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
}
