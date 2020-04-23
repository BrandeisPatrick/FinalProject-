package finalproject;

public class Board {
	
	public void printBoard() {
		for(int i=0; i<8; i++) {
			for(int j=0; j<4; j++) {
				if(i%2 ==0) {
					String s = "| |_";
					System.out.printf("%s", s);
				}
				else {
					String s = "|_| ";
					System.out.printf("%s", s);
				}
				
			}
			System.out.println("|"+i);
		}
		System.out.printf("%s"," 0 1 2 3 4 5 6 7");
	}
	
}
