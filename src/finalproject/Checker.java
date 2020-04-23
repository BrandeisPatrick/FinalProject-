package finalproject;

public class Checker {
	
	public int x;
	public int y;
	public boolean color; // true = x; false = o

	public Checker(int x,int y, boolean color) {
		this.x = x;
		this.y = y;
		this.color = color;
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
}
