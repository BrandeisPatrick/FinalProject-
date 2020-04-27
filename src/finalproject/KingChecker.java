package finalproject;

import java.util.ArrayList;

public class KingChecker extends Checker {


	public KingChecker(int x, int y, boolean color, int number) {
		super(x,y,color, number);

	}
	
	
	public String toString(){
		if (color) {
			return "X";
		} else {
			return "O";
		}
	}

	//checks if the checker can move to that spot 
		//coordinates are those that the player is trying to move to, the array is the array of ALL checkers on the board
		public boolean canMove(Board board, int xCoordinate, int yCoordinate, ArrayList<Checker> checkersInUse) { 
			if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
				System.out.println("The Coordinates you entered are out of bounds");
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
				
	

}
