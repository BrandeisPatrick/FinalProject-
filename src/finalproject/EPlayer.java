package finalproject;
import java.util.*;

/**
 * This is an extension of Player that simulates an AI to play the user instead of another person
 * This EPlayer will move randomly each turn
 */
public class EPlayer extends Player {
	private HashMap<Integer, String> intToLetter;

	public EPlayer(boolean color) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a name for this computer ");
		String str  = sc.nextLine();
        this.name = str;
		this.color = color;
		setLetterToInt();
		this.numMoves = 0;
		setIntToLetter();
		// TODO Auto-generated constructor stub
	}

	public EPlayer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Convert Int to Letters
	 */
	public void setIntToLetter() {
		this.intToLetter = new HashMap<Integer,String>();
		for(int i = 1; i <= 8; i++){
			intToLetter.put(i,this.letters[i-1]);
		}
	}

	/**executes the player's turn: asks for input until given valid input moves the players checkers
	 * then resets the board to show the new arrangement
	 */
	public void tick(ArrayList<Checker> checkersInUse, Board board){
		System.out.println("Computer's Turn ");
		boolean keepGoing = true;
		while(keepGoing) {
			Checker testChecker = getRandomChecker(checkersInUse);
			for(int i = 0; i <= 8; i++) {
				for(int j = 0; j <= 8; j++) {
					if (keepGoing) {
						if (canMove(j, i, board, testChecker)) {
							keepGoing = false;
							int iy = testChecker.getY();
							int ix = testChecker.getX();

							int fy = i;
							int fx = j;
							board.findChecker(ix, iy).move(fx, fy);
							System.out.println("Computers' move: ");
							System.out.println(intToLetter.get(iy)+ix + " " + intToLetter.get(fy)+fx);
						}
					}
				}
				}
			}
		
		//System.out.println("[debug] coordinate = " + ix + " " + iy  + " " + fx  + " " + fy);
		board.checkForKing();
		//System.out.println("after move checker");
		board.cleanBoard();
		board.fillBoard();
		System.out.println(board);
		//	board.printCheckerCoord();
	}
	
	//Chooses a random checker from checkersInUse that is of its own color, not of enemy color
	public Checker getRandomChecker(ArrayList<Checker> checkersInUse) {
		Random r = new Random();
		boolean keepGoing = true;
		Checker checkerFound = new Checker();
		while(keepGoing) {
			int num = r.nextInt(checkersInUse.size());
			if(!checkersInUse.get(num).getColor()) {
				checkerFound = checkersInUse.get(num);
				keepGoing = false;
			}
		}
		return checkerFound;
	}
	
	//Override the canMove method to not display comments about invalid moves
  	public boolean canMove(int xCoordinate, int yCoordinate, Board board, Checker c) { 
  		//System.out.println("Entered canMove method");
  		if(c instanceof KingChecker) {
  			c = (KingChecker) c; 
  		}
  		
  		//check to see if it's an enemy
  		if(this.isEnemyColor(c)) {
  			return false; 
  		}
  		//tests if coordinates are out of bounds
  		else if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
  			return false;
  			//tests if the move was backwards, **the move will always pass this test is it's a king checker
  		}else if(c.moveBackwards(yCoordinate, c.getY())){
  			return false;
  		//tests if the spot is taken
  		}else if(!board.spotOpen(xCoordinate, yCoordinate)){
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
  					return false;
  				}	
  		//The coordinates must be diagonal
  		}else {
  			//System.out.println("Entered diagonal loop");
  			//checks if the checker made a valid single jump over an enemy checker.
  			if(xCoordinate == c.x + 2 && yCoordinate == c.y + 2) {
  				return singleMoveValid(board, c.x + 1, c.y + 1);
  			}else if(xCoordinate == c.x - 2 && yCoordinate == c.y + 2) {
  				return singleMoveValid(board, c.x - 1, c.y + 1);
  			}else if(xCoordinate == c.x + 2 && yCoordinate == c.y - 2){
  				return singleMoveValid(board, c.x + 1, c.y - 1);
  			}else if(xCoordinate == c.x - 2 && yCoordinate == c.y - 2){
  				return singleMoveValid(board, c.x - 1, c.y - 1);
  					
  				//checks if a double jump is valid
  			}else if(xCoordinate == c.x + 4 && yCoordinate == c.y + 4) {
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
  					return false;
  				}
  			}

  		}
  	}
  	
  //Override
	public boolean singleMoveValid(Board board, int enemyX, int enemyY) {
			if(board.findChecker(enemyX, enemyY) != null) {
				if(this.isEnemyColor(board.findChecker(enemyX, enemyY))) {
	  				//valid move
	  				board.removeChecker(enemyX, enemyY);
	  				return true;
	  				}else {
	  					return false;
	  				}
			}else {
				return false;
			}
			
	}
	
	//override
	public boolean doubleMoveValid(Board board, int enemyX1, int enemyY1, int enemyX2, int enemyY2, int openX, int openY) {
		boolean firstJumpValid = singleMoveValid(board, enemyX1, enemyY1) && board.spotOpen(openX, openY);						
		boolean secondJumpValid = (singleMoveValid(board, enemyX2, enemyY2));
		if(firstJumpValid && secondJumpValid) { //if true, the move is valid)
			board.removeChecker(enemyX1,  enemyY1);
			board.removeChecker(enemyX2,  enemyY2);
			return true;
		}else {
			return false;
		}
	}

//override
public boolean zigzagMoveValid(Board board, int xCoordinate, int yCoordinate,
	int xPt1, int yPt1, int xPt2, int yPt2, int xMiddlePt12, int yMiddlePt12, int xPt3, int yPt3, int xPt4, int yPt4, int xMiddlePt34, int yMiddlePt34){
	if(doubleMoveValid(board, xPt1, yPt1, xPt2, yPt2, xMiddlePt12, yMiddlePt12)) {
		board.removeChecker(xPt1, yPt1);
		board.removeChecker(xPt2, yPt2);
		return true;
	}else if(doubleMoveValid(board, xPt3, yPt3, xPt4, yPt4, xMiddlePt34, yMiddlePt34)) {
		board.removeChecker(xPt3, yPt3);
		board.removeChecker(xPt4, yPt4);
		//valid move
		return true;
	}else {
		return false;
	}
}
}
