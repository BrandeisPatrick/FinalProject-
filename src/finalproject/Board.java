package finalproject;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class Board {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};

	public Square[][] board;
	public ArrayList<Checker> checkersInUse;

	public Board() {
		this.checkersInUse = new ArrayList<Checker>();
		setBoard();

	}

	//create board and give each Square coordinates
	public void setBoard() {
		this.board = new Square[8][8];
		for(int i = 1; i <= 8; i++){  	  // vertical
			for(int j = 1; j <= 8; j++){  // horizontal
				this.board[i-1][j-1] = new Square();
				this.board[i-1][j-1].setCoordinates(j,i);
			}
		}
		setCheckers(true, checkersInUse, 12);
		setCheckers(false, checkersInUse, 12);
		fillBoard();
	}

	public void setCheckers(boolean color, ArrayList<Checker> checkersInUse, int checkerNum) {
		if(color == true) { //player x
			color = true;
			for(int i = 1; i <= 3; i++) { //iterates through three rows
				if(i%2 != 0) {
					for(int j = 1; j<= 7; j+=2) {
						checkersInUse.add(new Checker( j, i,true, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 2; j<= 8; j+=2) {
						checkersInUse.add(new Checker( j, i,  true, checkerNum +1));
						checkerNum ++;
					}
				}
			}
		}else{
			color = false;
			for(int i = 6; i <= 8; i++) {
				if(i%2 != 0) {
					for(int j = 1; j<=7; j+=2) {
						checkersInUse.add(new Checker(j, i, false, checkerNum +1));
						checkerNum ++;
					}
				}else {
					for(int j = 2; j<=8; j+=2) {
						checkersInUse.add(new Checker(j, i, false, checkerNum +1));
						checkerNum ++;
					}
				}
			}
			
		}
	}
	
	public void fillBoard(){
		Iterator<Checker> itr  = this.checkersInUse.iterator();
		while(itr.hasNext()){
			Checker checker = itr.next();
			int x = checker.x;
			int y = checker.y;
			//System.out.println("x = " + x + " y = " + y + " | " );
			board[y-1][x-1].setChecker(checker);
		}
	}

	public void cleanBoard(){
		for(int i = 1; i <= 8; i++){  	  // vertical
			for(int j = 1; j <= 8; j++){  // horizontal
				this.board[i-1][j-1] = new Square();
				this.board[i-1][j-1].setCoordinates(j,i);
			}
		}
	}

	//Inspired from Koolgee0's code to print A B E C E D on one side of the board
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("\n    1  2  3  4  5  6  7  8\n");
		for (int i = 0; i < 8; i++) {
			output.append(letters[i]).append("  ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getChecker() != null) {
					output.append(board[i][j].getChecker().toString()).append(" ");
				} else {
					output.append(board[i][j].toString()).append(" ");
				}
			}
			output.append("\n");
		}
		output.append("\n");
		return output.toString();
	}
/**
	public void printCheckersInUse() {
		System.out.print("[(" + checkersInUse.get(0).printCoordinates());
		for(int i = 1; i < checkersInUse.size(); i++) {
			System.out.print(", " + checkersInUse.get(i).printCoordinates());
		}
		System.out.println("]");
	}
 */
	//finds a checker with certain coordinates if it exists, given coordinates
	public Checker findChecker(int xCoordinate, int yCoordinate) {
		for(int i = 0; i < this.checkersInUse.size(); i++) {
			if(xCoordinate == checkersInUse.get(i).getX() && yCoordinate == checkersInUse.get(i).getY()) {
				return checkersInUse.get(i);
			} 
		}
		return null;
	}
	
	
	//finds a checker with certain coordinates if it exists, given a checker
		public Checker findChecker(Checker checker) {
			for(int i = 0; i < this.checkersInUse.size(); i++) {
				if(checker.getX() == checkersInUse.get(i).getX() && checker.getY() == checkersInUse.get(i).getY()) {
					return checkersInUse.get(i);
				}
			}
			return null;
		}
		
		//checks if a spot is already taken or it's open
		public boolean spotOpen(int xCoordinate, int yCoordinate) {
			for(int i = 0; i < this.checkersInUse.size(); i++) {
				if(this.checkersInUse.get(i).getX() == xCoordinate &&
				   this.checkersInUse.get(i).getY() == yCoordinate) {
					return false;
				}
			}
			return true;
		}

		public void removeChecker(Checker checker){
			Checker removed = checker;
			this.board[removed.getY()-1][removed.getX()-1].removeChecker();
			for(int i = 0; i < this.checkersInUse.size(); i++) {
				if(removed.getX() == checkersInUse.get(i).getX() && removed.getY() == checkersInUse.get(i).getY()) {
					this.checkersInUse.remove(i);
				}
			}
		}

		//Removes enemy checker that has been eaten
				public void removeChecker(int enemyxCoordinate, int enemyyCoordinate) {
					for(Checker c : this.checkersInUse) {
						if(c.getX() == enemyxCoordinate) {
							if(c.getY() == enemyyCoordinate) {
								this.checkersInUse.remove(c);
							}
						}
					}
				}
				
			//Checks if the Checker has reached the opposite side
			//Adds a new KingChecker and removes Checker if it has
				public void checkForKing() {
					for (Checker c : this.checkersInUse) {
						if(c.getColor() == true) {
							if (c.getX() == 8) {
								KingChecker newChecker = new KingChecker(c.getX(), c.getY(), true, c.getNumber());
								this.checkersInUse.add(newChecker);
								this.checkersInUse.remove(c);
							}
						}
						else {
							if(c.getX() == 1) {
								KingChecker newChecker = new KingChecker(c.getX(), c.getY(), false, c.getNumber());
								this.checkersInUse.add(newChecker);
								this.checkersInUse.remove(c);
							}
						}
					}
				}
	
				
	public static void main(String[] args){
		Board board = new Board();
		System.out.print(board);
	}

	//debug purpose
	public void printCheckerCoord(){
		StringBuilder builder = new StringBuilder();
		for (Checker checker : this.checkersInUse){
			builder.append("x = " + checker.x + " y = " + checker.y + " | " );
		}
		System.out.println(builder);
	}
}
