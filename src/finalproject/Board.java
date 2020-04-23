package finalproject;

public class Board {
	
	public void printBoard() {
		for(int i=0; i<16; i++) {
			for(int j=0; j<8; j++) {
				if(i%2 == 0) {
					String s = "|   ";
					System.out.printf("%s", s);
				}
				else {
					String s = "|___";
					System.out.printf("%s", s);
				}
				
			}
			if (i%2 == 0) {
				System.out.println("|"+i/2);
			}
			else {
				System.out.println("|");
			}
		}
		System.out.printf("%s","   0   1   2   3   4   5   6   7");
	}
	
}
