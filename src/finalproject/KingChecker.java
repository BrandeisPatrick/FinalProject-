package finalproject;

public class KingChecker extends Checker {


	public KingChecker(int row, int col, boolean color, int number) {
		super(row,col,color, number);

	}
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}
	

}
