package finalproject;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Player represents a player in the game, either x or o. 
 * String[] letters and Map<String,Integer> letterToInt exist for translation of the board coordinates
 * into (x,y) coordinates
 * color is also associated with a player which links the player with its checkers
 * numMoves will keep track of how many moves each player takes during the game
 *
 * Player has a number of important methods including the tick method for each players turn
 * and the method to see if a certain move is valid. It also has smaller methods accosiated with a player
 * like a method to tell is a checker is an enemy checker or not and a method to see if all the .
 * player's pieces are gone
 */
public class Player {
	public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
	public boolean color; //true = x false = o.....
	Map<String,Integer> letterToInt;
	int numMoves;
	String name;


	/**
	 * Constructor for Player
	 * @param color is the identity for Player X (true) or Player O (false)
	 */
	public Player(boolean color, int playerNum) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a name for player " + playerNum);
		String str  = sc.nextLine();
        this.name = str;
		this.color = color;
		setLetterToInt();
		this.numMoves = 1;
	}

	/**
	 * Default Constructor
	 */
	public Player() {
		this.color = true;
		this.name = "Player X";
		this.numMoves = 1;
	}

	/**
	 * set up a Map which can translates a letter on the board to its corresponding integer 1-8
	 * for example ( A to 1 )
	 */
	public void setLetterToInt() {
		this.letterToInt = new HashMap<String,Integer>();
		for(int i = 1; i <= 8; i++){
			letterToInt.put(this.letters[i-1],i);
		}
	}

	/**
	 * toString
	 * @return Player Identity
	 */
	public String toString() {
		if(this.color == true) {
			return "Player X";
		}else {
			return "Player O";
		}
	}

	/*
	 *
	 * resets the board to show the new arrangement
	 */

	/**
	 * executes the player's turn: asks for input until given valid input
	 * process the player's instruction for moving checkers
	 *
	 * @param checkersInUse the ArraryList of Checkers
	 * @param board			the Class has checkersInUse,
	 *                      board also is responsible for displaying all the checkers.
	 *                      after every turn, board reassign the checker to the correct Square based on the checker's coordinate
	 */
	public void tick(ArrayList<Checker> checkersInUse, Board board){
		Scanner userinput = new Scanner(System.in);
		System.out.println(this.name + "'s Turn   (move: " + this.numMoves + ")");

		System.out.println("Your move  ");
		String select = userinput.next();
		String destination = userinput.next();


		while(select.length()!= 2 || destination.length() != 2){
			System.out.println("your input is invalid");
			System.out.println("Enter your move again// ");
			select = userinput.next();
			destination = userinput.next();
		}

		int iy = letterToInt.get(select.substring(0,1));  // expect: E would be mapped to 1
		int ix = Integer.parseInt(select.substring(1));

		int fy = letterToInt.get(destination.substring(0,1));
		int fx = Integer.parseInt(destination.substring(1));


		while(board.findChecker(ix, iy) == null || !canMove(fx, fy, board, board.findChecker(ix, iy)) ){  //can be simplified
			//there is no Checker or the move is mistaken.
			//needs to enter an new move.
			if(board.findChecker(ix, iy) == null){		
				System.out.println("Invalid move: There's no checker in tha spot to move");
			}
			System.out.println("Enter a vaild move :)");
			select = userinput.next();
			destination = userinput.next();
			while(select.length()!= 2 || destination.length() != 2){
				System.out.println("your input is invalid");
				System.out.println("Enter your move again// ");
				select = userinput.next();
				destination = userinput.next();
			}

			iy = letterToInt.get(select.substring(0,1));  // expect: E would be mapped to 1
			ix = Integer.parseInt(select.substring(1));

			fy = letterToInt.get(destination.substring(0,1));
			fx = Integer.parseInt(destination.substring(1));
		}
		//System.out.println("[debug] coordinate = " + ix + " " + iy  + " " + fx  + " " + fy);
		this.numMoves++;
		board.findChecker(ix, iy).move(fx,fy);
		board.checkForKing();
		//System.out.println("after move checker");
		board.cleanBoard();
		board.fillBoard();
		System.out.println(board);
	//	board.printCheckerCoord();
	}

	/**
	 * This method tells if the checker is a checker of the opponents or not: returns true if it is, false if it's not
	 * @param checker	is the checker selected
	 * @return if it is a checker of the opponents
	 */
	//Tells if the checker is a checker of the opponents or not: returns true if it is, false if it's not
	public boolean isEnemyColor(Checker checker) {
		if(checker.getColor() == this.color) {
			return false;
		}
		return true;
	}


	/**
	 * This method tells if all pieces of a player is gone: returns false if there are pieces still left, false if all gone
	 * Used as an indicator of the End of the game
	 * @param checkersInUse   ArrayList of Checkers
	 * @return				  if all pieces are gone
	 */
	public boolean allPiecesGone(ArrayList<Checker> checkersInUse) {
		for(Checker c : checkersInUse) {
			if(c.getColor() == this.color) {
				return false;
			}
		}
		return true;
	}
	
	public int getMoves() {
		return this.numMoves;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * checks if the checker can move to a spot on the board specified by the player
	 * x and y coordinates are those that the player is trying to move to,
	 * the player depends on which players turn it is, the checker is the checker the player wants to move
	 * @param xCoordinate
	 * @param yCoordinate  is the destination coordinate
	 * @param board        is the board Class
	 * @param c			   is the Checker selected
	 * @return			   if this checker can move
	 */
	public boolean canMove(int xCoordinate, int yCoordinate, Board board, Checker c) {
  		//System.out.println("Entered canMove method");
  		if(c instanceof KingChecker) {
  			c = (KingChecker) c; 
  		}
  		
  		//check to see if it's an enemy
  		if(this.isEnemyColor(c)) {
  			System.out.println("Invalid move: You cannot move the oppponent's checkers");
  			return false; 
  		}
  		//tests if coordinates are out of bounds
  		else if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
  			System.out.println("Invalid move: The Coordinates you entered are out of bounds");
  			return false;
  			//tests if the move was backwards, **the move will always pass this test is it's a king checker
  		}else if(c.moveBackwards(yCoordinate, c.getY())){
  			System.out.println("Invalid move: You cant move backwards as a regualar checker");
  			return false;
  		//tests if the spot is taken
  		}else if(!board.spotOpen(xCoordinate, yCoordinate)){
  			System.out.println("Invalid move: That spot is taken");
  			return false;
  			
  		//if the move keeps the y or x coordinate the same, the move is horizontal or vertical, not diagonal
  		}else if(yCoordinate == c.y || xCoordinate == c.x) {
  				if(xCoordinate == c.x + 4){
  					return zigzagMoveValid(board, xCoordinate,  yCoordinate,
  					c.x + 1, c.y -1, c.x + 3, c.y -1, c.x + 2, c.y - 2, c.x + 1, c.y + 1, c.x + 3, c.y  + 1, c.x + 2, c.y + 2);
  				}else if(xCoordinate == c.x - 4) {
  					return zigzagMoveValid(board, xCoordinate,  yCoordinate,
  							c.x - 1, c.y -1, c.x - 3, c.y -1, c.x - 2, c.y - 2, c.x - 1, c.y + 1, c.x - 3, c.y  + 1, c.x - 2, c.y + 2);
  				}else if (yCoordinate == c.y + 4) {
  					return zigzagMoveValid(board, xCoordinate,  yCoordinate,
  							c.x - 1, c.y + 1, c.x - 1, c.y + 3, c.x - 2, c.y + 2, c.x + 1, c.y + 1, c.x + 1, c.y  + 3, c.x + 2, c.y + 2);
  				}else if(yCoordinate == c.y - 4){
  					return zigzagMoveValid(board, xCoordinate,  yCoordinate,
  							c.x - 1, c.y - 1, c.x - 1, c.y - 3, c.x - 2, c.y - 2, c.x + 1, c.y - 1,c.x + 1, c.y - 3, c.x + 2, c.y - 2);
  				}else {
  					System.out.println("Invalid move: You have to move in a straight line, you must move diagonally");
  					return false;
  				}	
  		//The coordinates must be diagonal
  		}else {
  			//System.out.println("Entered diagonal loop");
  			//checks if the checker made a valid single jump over an enemy checker.
  			if(xCoordinate == c.x + 2 && yCoordinate == c.y + 2) {
  				//System.out.println("Entered test 1");
  				return singleMoveValid(board, c.x + 1, c.y + 1);
  			}else if(xCoordinate == c.x - 2 && yCoordinate == c.y + 2) {
  				return singleMoveValid(board, c.x - 1, c.y + 1);
  			}else if(xCoordinate == c.x + 2 && yCoordinate == c.y - 2){
  				return singleMoveValid(board, c.x + 1, c.y - 1);
  			}else if(xCoordinate == c.x - 2 && yCoordinate == c.y - 2){
  				return singleMoveValid(board, c.x - 1, c.y - 1);
  					
  				//checks if a double jump is valid
  			}else if(xCoordinate == c.x + 4 && yCoordinate == c.y + 4) {
  				//System.out.println("Entered double test 1");
  				return doubleMoveValid(board, c.x + 1, c.y + 1, c.x + 3, c.y + 3, c.x + 2, c.y + 2);
  			}else if(xCoordinate == c.x - 4 && yCoordinate == c.y + 4) {
  				return doubleMoveValid(board, c.x - 1, c.y + 1, c.x - 3, c.y + 3, c.x - 2, c.y + 2);
  			}else if(xCoordinate == c.x + 4 && yCoordinate == c.y - 4){
  				return doubleMoveValid(board, c.x + 1, c.y - 1, c.x + 3, c.y - 3, c.x + 2, c.y - 2);
  			}else if(xCoordinate == c.x - 4 && yCoordinate == c.y - 4){
  				return doubleMoveValid(board, c.x - 1, c.y - 1, c.x - 3, c.y - 3, c.x - 2, c.y - 2);
  						
  			//By now, the move is either a single move or invalid
  			}else {
  				if((xCoordinate == c.x +1 && yCoordinate == c.y + 1) ||
  				   (xCoordinate == c.x +1  && yCoordinate == c.y -1) ||
  				   (xCoordinate == c.x - 1 && yCoordinate == c.y - 1) ||
  				   (xCoordinate == c.x - 1 && yCoordinate == c.y +1)){
  					return true;
  				}else {
  					System.out.println("Invalid move");
  					return false;
  				}
  			}

  		}
  	}
 


	/**
	 * This method checks if a single jump move over an enemy is valid: returns false if either the there is no checker to eat or if the checker is not an enemy checker
	 * you can do jump only if you are jumping over an enemy
	 *
	 * @param board
	 * @param enemyX
	 * @param enemyY
	 * @return
	 */
	public boolean singleMoveValid(Board board, int enemyX, int enemyY) {
		if(board.findChecker(enemyX, enemyY) != null) {
			if(this.isEnemyColor(board.findChecker(enemyX, enemyY))) {
				//valid move
				board.removeChecker(enemyX, enemyY);
				return true;
			}else {
				System.out.println("Not a valid move");
				return false;
			}
		}else {
			System.out.println("You can't move more than one space without eating an enemy checker");
			return false;
		}
    			
	}
  	
	/**combines the two methods above: checks to see if a double jump is valid
	 * the enemy coordinates are the coordinates of the two enemy checkers the checker wants to jump over
	 * the open coordinates are the coordinates of the space in between them, you want to make sure that space is open
	 */
	public boolean doubleMoveValid(Board board, int enemyX1, int enemyY1, int enemyX2, int enemyY2, int openX, int openY) {
		boolean firstJumpValid = singleMoveValid(board, enemyX1, enemyY1) && board.spotOpen(openX, openY);
		boolean secondJumpValid = (singleMoveValid(board, enemyX2, enemyY2));
		if(firstJumpValid && secondJumpValid) { //if true, the move is valid)
			board.removeChecker(enemyX1,  enemyY1);
			board.removeChecker(enemyX2,  enemyY2);
			return true;
		}else {
			System.out.println("Invalid double jump move");
			return false;
		}
	}


	/**
	 * checks to see if a Zigzag Move is valid:
	 *
	 * Different from straightDoubleMove() because for this move, you have to check two conditions because the checker could get to the target spot in two ways vs. only one in straightDoubleMove()
	 *
	 * @param board
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param xPt1
	 * @param yPt1
	 * @param xPt2
	 * @param yPt2
	 * @param xMiddlePt12
	 * @param yMiddlePt12
	 * @param xPt3
	 * @param yPt3
	 * @param xPt4
	 * @param yPt4
	 * @param xMiddlePt34
	 * @param yMiddlePt34
	 * xPt and yPt are the coordinates of the two enemy checkers that the checker wants to jump over
	 * xMiddle and yMiddle are the coordinate3s of the point in between, you want to make sure that space is open
	 * @return	if you can move
	 */
	public boolean zigzagMoveValid(Board board, int xCoordinate, int yCoordinate,
		int xPt1, int yPt1, int xPt2, int yPt2, int xMiddlePt12, int yMiddlePt12, int xPt3, int yPt3, int xPt4, int yPt4, int xMiddlePt34, int yMiddlePt34){
		if(xMiddlePt12 < 1 || xMiddlePt12 > 8 || yMiddlePt34 < 1 || yMiddlePt34 > 8) {
			return false;
		}
		else if(doubleMoveValid(board, xPt1, yPt1, xPt2, yPt2, xMiddlePt12, yMiddlePt12)) {
			board.removeChecker(xPt1, yPt1);
			board.removeChecker(xPt2, yPt2);
			return true;
		}else if(doubleMoveValid(board, xPt3, yPt3, xPt4, yPt4, xMiddlePt34, yMiddlePt34)) {
			board.removeChecker(xPt3, yPt3);
			board.removeChecker(xPt4, yPt4);
			//valid move
			return true;
		}else {
			System.out.println("Invalid zigzag move");
			return false;
		}
	}



	/**
	 * testing code(static method)
	 * @return
	 
	public static void main(String[] args) {
		Player pTrue = new Player(true);
		Player pFalse = new Player(false);
		System.out.println(pTrue);
		System.out.println();
		System.out.println(pFalse);

	}
	 */


}

