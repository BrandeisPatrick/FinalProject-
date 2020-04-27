package finalproject;
import java.util.ArrayList;

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
	
	//checks if the checker can move to that spot 
	//coordinates are those that the player is trying to move to, the array is the array of ALL checkers on the board
	public boolean canMove(Board board, int xCoordinate, int yCoordinate, ArrayList<Checker> checkersInUse) { 
		if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
			System.out.println("The Coordinates you entered are out of bounds");
			return false;
			//tests if the move was backwards
		}else if(moveBackwards(xCoordinate, yCoordinate)){
			System.out.println("Invalid move, you cant move backwards as a regualar checker");
			return false;
				
		//tests if the spot is taken
		}else if(!spotOpen(checkersInUse, xCoordinate, yCoordinate)) {
			System.out.println("And that's an invalid move, the spot is taken");
			return false;
			
		//If the move passes that stuff, it will now check if the move is valid based on if it's diagonal or straight
		}else{
			//if the move keeps the y or x coordinate the same, the move is horizontal or vertical, not diagonal
			if(yCoordinate == this.y || xCoordinate == this.x) {
				if(xCoordinate == this.x + 4){
					zigzagMoveValid(checkersInUse, board, xCoordinate,  yCoordinate,
					this.x + 1, this.y -1, this.x + 3, this.y -1, this.x + 2, this.y - 2, this.x + 1, this.y + 1,this.x + 3, this.y  + 1, this.x + 2, this.y + 2);
				}else if(xCoordinate == this.x - 4) {
					zigzagMoveValid(checkersInUse, board, xCoordinate,  yCoordinate,
							this.x - 1, this.y -1, this.x - 3, this.y -1, this.x - 2, this.y - 2, this.x - 1, this.y + 1,this.x - 3, this.y  + 1, this.x - 2, this.y + 2);
				}else if (yCoordinate == this.y + 4) {
					zigzagMoveValid(checkersInUse, board, xCoordinate,  yCoordinate,
							this.x - 1, this.y + 1, this.x - 1, this.y + 3, this.x - 2, this.y + 2, this.x + 1, this.y + 1, this.x + 1, this.y  + 3, this.x + 2, this.y + 2);
				}else if(yCoordinate == this.y - 4){
					zigzagMoveValid(checkersInUse, board, xCoordinate,  yCoordinate,
							this.x - 1, this.y - 1, this.x - 1, this.y - 3, this.x - 2, this.y - 2, this.x + 1, this.y - 1,this.x + 1, this.y - 3, this.x + 2, this.y - 2);
				}
				
				//The coordinates must be diagonal
				}else {
					//checks if the checker made a valid single jump over an enemy checker
					if(xCoordinate == this.x + 2 && yCoordinate == this.y + 2) {
						singleMoveValid(board, checkersInUse, xCoordinate,  yCoordinate, this.x + 2, this.y + 2);
					}else if(xCoordinate == this.x - 2 && yCoordinate == this.y + 2) {
						singleMoveValid(board, checkersInUse, xCoordinate,  yCoordinate, this.x - 2, this.y + 2);
					}else if(xCoordinate == this.x + 2 && yCoordinate == this.y - 2){
						singleMoveValid(board, checkersInUse, xCoordinate,  yCoordinate, this.x + 2, this.y - 2);
					}else if(xCoordinate == this.x - 2 && yCoordinate == this.y - 2){
						singleMoveValid(board, checkersInUse, xCoordinate,  yCoordinate, this.x - 2, this.y - 2);
					
						//checks if a double jump is valid
					}else if(xCoordinate == this.x + 4 && yCoordinate == this.y + 4) {
						doubleMoveValid(checkersInUse, board, xCoordinate, yCoordinate, this.x + 1, this.y + 1, this.x + 3, this.y + 3, this.x + 2,this.y + 2);
					}else if(xCoordinate == this.x - 4 && yCoordinate == this.y + 4) {
						doubleMoveValid(checkersInUse, board, xCoordinate, yCoordinate, this.x - 1, this.y + 1, this.x - 3, this.y + 3, this.x - 2,this.y + 2);
					}else if(xCoordinate == this.x + 4 && yCoordinate == this.y - 4){
						doubleMoveValid(checkersInUse, board, xCoordinate, yCoordinate, this.x + 1, this.y - 1, this.x + 3, this.y - 3, this.x + 2,this.y - 2);
					}else if(xCoordinate == this.x - 4 && yCoordinate == this.y - 4){
						doubleMoveValid(checkersInUse, board, xCoordinate, yCoordinate, this.x - 1, this.y - 1, this.x - 3, this.y - 3, this.x - 2,this.y - 2);
						
					//By now, the move is either a single move or invalid
					}else {
						if((xCoordinate == this.x +1 && yCoordinate == this.y + 1) ||
						   (xCoordinate == this.x +1  && yCoordinate == this.y -1) ||
						   (xCoordinate == this.x - 1 && yCoordinate == this.y - 1) ||
						   (xCoordinate == this.x - 1 && yCoordinate == this.y +1)){
							return true;
						}else {
							System.out.println("Invalid move");
							return false;
						}
					}

				}
			}
		return true;
		}
				
	
	//checks if its a backwards move
	public boolean moveBackwards(int xCoordinate, int yCoordinate) {
		if (this.color) { //true = x, false = o
			if((xCoordinate == this.x + 1 && yCoordinate == this.y - 1) ||
			  (xCoordinate == this.x - 1 && yCoordinate == this.y - 1)) {
				return true;
				}
		}else{
			if((xCoordinate == this.x - 1 && yCoordinate == this.y + 1) ||
			  (xCoordinate == this.x + 1 && yCoordinate == this.y + 1)) {
				return true;
			}
		}
		return false;
	}
	
	
	//checks if a spot is already taken or it's open
	public boolean spotOpen(ArrayList<Checker> checkersInUse, int xCoordinate, int yCoordinate) {
		for(int i = 0; i < checkersInUse.size(); i++) {
			if(checkersInUse.get(i).getX() == xCoordinate &&
			   checkersInUse.get(i).getY() == yCoordinate) {
				return false;
			}
		}
		return true;
	}
	
	//checks to see if a checker is an enemy checker
	public boolean isEnemyChecker(Board board, int enemyxCoordinate, int enemyyCoordinate) {
		boolean enemyColor = board.findChecker(enemyxCoordinate, enemyyCoordinate).getColor();//true = x
		if(enemyColor == this.getColor()) { //checks to see if the 'color' fields are equal
			return false;
		}else {
			return true;
		}
	}
	
	//combines the two methods above: checks to see if a double jump is valid
	public boolean doubleMoveValid(ArrayList<Checker> checkersInUse, Board board, int enemyxCoordinate1, int enemyyCoordinate1, 
								int enemyxCoordinate2, int enemyyCoordinate2, int openxCoordinate, int openyCoordinate) {
		boolean enemySpotOpen1 = spotOpen(checkersInUse, enemyxCoordinate1, enemyyCoordinate1) && 
								(isEnemyChecker(board, enemyxCoordinate1, enemyyCoordinate1));
		boolean enemySpotOpen2 = spotOpen(checkersInUse, enemyxCoordinate2, enemyyCoordinate2) && 
				(isEnemyChecker(board, enemyxCoordinate2, enemyyCoordinate2));
		boolean openSpotOpen = spotOpen(checkersInUse, openxCoordinate, openyCoordinate);
		return enemySpotOpen1 && enemySpotOpen2 && openSpotOpen; //if true, the move is valid
	}
	
	//checks to see if a zig zag move is valid
	public boolean zigzagMoveValid(ArrayList<Checker> checkersInUse, Board board, int xCoordinate, int yCoordinate,
		int xPt1, int yPt1, int xPt2, int yPt2, int xMiddlePt12, int yMiddlePt12, int xPt3, int yPt3, int xPt4, int yPt4, int xMiddlePt34, int yMiddlePt34){
		if(doubleMoveValid(checkersInUse, board, xPt1, yPt1, xPt2, yPt2, xMiddlePt12, yMiddlePt12)) {
			removeChecker(checkersInUse, board, xPt1, yPt1);
			removeChecker(checkersInUse, board, xPt2, yPt2);
			return true;
		}else if(doubleMoveValid(checkersInUse, board, xPt3, yPt3, xPt4, yPt4, xMiddlePt34, yMiddlePt34)) {
			removeChecker(checkersInUse, board, xPt3, yPt3);
			removeChecker(checkersInUse, board, xPt4, yPt4);
			//valid move
			return true;
		}else {
			System.out.println("Not a valid move");
			return false;
		}
	}
	
	//checks if a single jump move is valid
	public boolean singleMoveValid(Board board, ArrayList<Checker> checkersInUse, int xCoordinate, int yCoordinate, 
			int originalxCoordinate, int originalyCoordinate) {
			if(spotOpen(checkersInUse, xCoordinate, yCoordinate) && isEnemyChecker(board, originalxCoordinate - 1, originalyCoordinate - 1)) {
				//valid move
				removeChecker(checkersInUse, board, originalxCoordinate - 1, originalyCoordinate - 1);
				return true;
				}else {
					System.out.println("Not a valid move");
					return false;
				}
	}
	
	//checks to see if a double jump move is valid
		public boolean doubleMoveValid(ArrayList<Checker> checkersInUse, Board board, int xCoordinate, int yCoordinate,
			int xPt1, int yPt1, int xPt2, int yPt2, int xMiddlePt12, int yMiddlePt12){
			if(doubleMoveValid(checkersInUse, board, xPt1, yPt1, xPt2, yPt2, xMiddlePt12, yMiddlePt12)) {
				removeChecker(checkersInUse, board, xPt1, yPt1);
				removeChecker(checkersInUse, board, xPt2, yPt2);
				//valid move
				return true;
			}else {
				System.out.println("Not a valid move");
				return false;
			}
		}

	//Removes enemy checker that has been eaten
		public void removeChecker(ArrayList<Checker> checkersInUse, Board board, int enemyxCoordinate, int enemyyCoordinate) {
			for(Checker c : checkersInUse) {
				if(c.getX() == enemyxCoordinate) {
					if(c.getY() == enemyyCoordinate) {
						checkersInUse.remove(c);
					}
				}
			}
		}
		
	//Checks if the Checker has reached the opposite side
	//Adds a new KingChecker and removes Checker if it has
		public void checkForKing(ArrayList<Checker> checkersInUse, Board board) {
			for (Checker c : checkersInUse) {
				if(c.getColor() == true) {
					if (c.getX() == 8) {
						KingChecker newChecker = new KingChecker(c.getX(), c.getY(), true, c.getNumber());
						checkersInUse.add(newChecker);
						checkersInUse.remove(c);
					}
				}
				else {
					if(c.getX() == 1) {
						KingChecker newChecker = new KingChecker(c.getX(), c.getY(), false, c.getNumber());
						checkersInUse.add(newChecker);
						checkersInUse.remove(c);
					}
				}
			}
		}
		
	public String toString(){
		if (color) {
			return "x";
		} else {
			return "o";
		}
	}
	
	public String printCoordinates() {
		return("(" + this.x + ", "+ this.y + ")");
	}
	
	public int getX() {
		return this.y;
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
	
	
}
