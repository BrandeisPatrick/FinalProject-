package finalproject;

public class Player {
	public boolean color; //true = x false = o..
	Checker[] checkers;
	
	public Player(boolean color) {
		int checkerSpotCounter = 0;
		this.checkers = new Checker[12];
		
		if(color == true) { //player x
			this.color = true;
			for(int i = 1; i < 4; i++) {
				if(i%2 != 0) {
					for(int j = 1; j<= 7; j+=2) {
						checkers[checkerSpotCounter] = new Checker(i-1, j, true, checkerSpotCounter+1);
						checkerSpotCounter++;
					}
				}else {
					for(int j = 0; j<= 6; j+=2) {
						checkers[checkerSpotCounter] = new Checker(i-1, j, true, checkerSpotCounter+1);
						checkerSpotCounter++;
					}
				}
			}
		}else{
			this.color = false;
			for(int i = 5; i <= 7; i++) {
				if(i%2 == 0) {
					for(int j = 0; j<=6; j+=2) {
						checkers[checkerSpotCounter] = new Checker(i,j, true, checkerSpotCounter+1);
						checkerSpotCounter++;
					}
				}else {
					for(int j = 1; j<=7; j+=2) {
						checkers[checkerSpotCounter] = new Checker(i,j, true, checkerSpotCounter+1);
						checkerSpotCounter++;
					}
				}
			}
			
		}

			
		
	}
	
	public void printPlayer() {
		for(Checker c : checkers) {
			System.out.println( "(" + c.getX() + "," + c.getY() + ")");
		}
	}
	
	public static void main(String[] args) {
		Player pTrue = new Player(true);
		Player pFalse = new Player(false);
		pTrue.printPlayer();
		System.out.println();
		pFalse.printPlayer();
		
	}
	}

