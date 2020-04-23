package finalproject;

public class KingChecker extends Checker {


	public KingChecker() {
		super();
	}
	public KingChecker(int x, int y, boolean color) {
		super(x,y,color);

	}
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}
	

}
