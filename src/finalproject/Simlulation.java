package finalproject;
import java.util.Scanner;

public class Simlulation {
    public Player player1; //boolean = true, x
    public Player player2; //boolean false, o
    public Board board;

    public Simlulation() {
    	printHeader();
        setPlayer();
        board = new Board();
    }

    //distribute the players
    public void setPlayer() {
        System.out.println("Would you like to be Player 1 ? (please reply yes or no)");
        Scanner sc = new Scanner(System.in);
        String str  = sc.next();
        while(!str.equals("yes") && !str.equals("no")){
            System.out.println("Please give a valid answer.");
            str  = sc.next();
        }
        if(str.equals("yes")){
            System.out.println("The other Player will be Player 2");
        }else{
            System.out.println("The other Player will be Player 1");
        }
        this.player1 = new Player(str.equals("yes"));
        this.player2 = new Player(!str.equals("yes"));
    }

    //print header method
    public void printHeader(){
        System.out.println("This is checker game for two people;");
        System.out.println("Player 1 (red) will move first;");
        System.out.println("Player 2 (black) will move after Player 1;");
        System.out.println("red checkers will be represented as (X)");
        System.out.println("black checkers will be represented as (O)");
        System.out.println("In each move, select a checker by giving a coordinate;");
        System.out.println("then give the destination coordinate, separated by a space");
        System.out.println("For example E1 checker move to E2 position is \"E1 E2\"");
    }

    public void tick(){
        player1.tick(this.board.checkersInUse, this.board);
        player2.tick(this.board.checkersInUse, this.board);
        board.setBoard();

    }

    @Override
    public String toString() {
        return board.toString();
    }
    

    
    /*checks if the checker can move to a spot on the board specified by the player
     * x and y coordinates are those that the player is trying to move to, 
  	 * the player depends on which players turn it is, the checker is the checker the player wants to move*/
  	public boolean canMove(int xCoordinate, int yCoordinate, Player player, Checker c) { 
  		if(c instanceof KingChecker) {
  			c = (KingChecker) c; 
  		}
  		
  		//check to see if it's an enemy
  		if(player.isEnemyColor(c) == true) {
  			System.out.println("You cannot move the oppponent's checkers");
  			return false; 
  		}
  		//tests if coordinates are out of bounds
  		else if(xCoordinate < 1 || xCoordinate > 8 || yCoordinate < 1 || yCoordinate > 8) {
  			System.out.println("The Coordinates you entered are out of bounds");
  			return false;
  			//tests if the move was backwards
  		}else if(c.moveBackwards(yCoordinate, c.getY())){
  			System.out.println("Invalid move, you cant move backwards as a regualar checker");
  			return false;
  		//tests if the spot is taken
  		}else if(this.board.findChecker(xCoordinate, yCoordinate) != null) {
  			System.out.println("And that's an invalid move, the spot is taken");
  			return false;
  			
  		//If the move passes that stuff, it will now check if the move is valid based on if it's diagonal or straight
  		}else{
  			//if the move keeps the y or x coordinate the same, the move is horizontal or vertical, not diagonal
  			if(yCoordinate == c.y || xCoordinate == c.x) {
  				if(xCoordinate == c.x + 4){
  					zigzagMoveValid(player, xCoordinate,  yCoordinate,
  					c.x + 1, c.y -1, c.x + 3, c.y -1, c.x + 2, c.y - 2, c.x + 1, c.y + 1, c.x + 3, c.y  + 1, c.x + 2, c.y + 2);
  				}else if(xCoordinate == c.x - 4) {
  					zigzagMoveValid(player, xCoordinate,  yCoordinate,
  							c.x - 1, c.y -1, c.x - 3, c.y -1, c.x - 2, c.y - 2, c.x - 1, c.y + 1, c.x - 3, c.y  + 1, c.x - 2, c.y + 2);
  				}else if (yCoordinate == c.y + 4) {
  					zigzagMoveValid(player, xCoordinate,  yCoordinate,
  							c.x - 1, c.y + 1, c.x - 1, c.y + 3, c.x - 2, c.y + 2, c.x + 1, c.y + 1, c.x + 1, c.y  + 3, c.x + 2, c.y + 2);
  				}else if(yCoordinate == c.y - 4){
  					zigzagMoveValid(player, xCoordinate,  yCoordinate,
  							c.x - 1, c.y - 1, c.x - 1, c.y - 3, c.x - 2, c.y - 2, c.x + 1, c.y - 1,c.x + 1, c.y - 3, c.x + 2, c.y - 2);
  				}
  				
  				//The coordinates must be diagonal
  				}else {
  					//checks if the checker made a valid single jump over an enemy checker
  					if(xCoordinate == c.x + 2 && yCoordinate == c.y + 2) {
  						singleMoveValid(player, c.x + 2, c.y + 2);
  					}else if(xCoordinate == c.x - 2 && yCoordinate == c.y + 2) {
  						singleMoveValid(player, c.x - 2, c.y + 2);
  					}else if(xCoordinate == c.x + 2 && yCoordinate == c.y - 2){
  						singleMoveValid(player, c.x + 2, c.y - 2);
  					}else if(xCoordinate == c.x - 2 && yCoordinate == c.y - 2){
  						singleMoveValid(player, c.x - 2, c.y - 2);
  					
  						//checks if a double jump is valid
  					}else if(xCoordinate == c.x + 4 && yCoordinate == c.y + 4) {
  						doubleMoveValid(player, c.x + 1, c.y + 1, c.x + 3, c.y + 3, c.x + 2, c.y + 2);
  					}else if(xCoordinate == c.x - 4 && yCoordinate == c.y + 4) {
  						doubleMoveValid(player, c.x - 1, c.y + 1, c.x - 3, c.y + 3, c.x - 2, c.y + 2);
  					}else if(xCoordinate == c.x + 4 && yCoordinate == c.y - 4){
  						doubleMoveValid(player, c.x + 1, c.y - 1, c.x + 3, c.y - 3, c.x + 2, c.y - 2);
  					}else if(xCoordinate == c.x - 4 && yCoordinate == c.y - 4){
  						doubleMoveValid(player, c.x - 1, c.y - 1, c.x - 3, c.y - 3, c.x - 2, c.y - 2);
  						
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
  		return true;
  		}
  	
  //checks if a single jump move over an enemy is valid
  	public boolean singleMoveValid(Player player, int enemyX, int enemyY) {
  			if(player.isEnemyColor(board.findChecker(enemyX, enemyY))) {
  				//valid move
  				this.board.removeChecker(enemyX, enemyY);
  				return true;
  				}else {
  					System.out.println("Not a valid move");
  					return false;
  				}
  	}
  	
	/*combines the two methods above: checks to see if a double jump is valid
	 * the enemy coordinates are the coordinates of the two enemy checkers the checker wants to jump over
	 * the open coordinates are the coordinates of the space in between them, you want to make sure that space is open
	 */
	public boolean doubleMoveValid(Player player, int enemyX1, int enemyY1, int enemyX2, int enemyY2, int openX, int openY) {
		boolean enemySpotOpen1 = (!board.spotOpen(enemyX1, enemyY1)) && (player.isEnemyColor(board.findChecker(enemyX1, enemyY1)));
								
		boolean enemySpotOpen2 = (!board.spotOpen(enemyX2, enemyY2)) && (player.isEnemyColor(board.findChecker(enemyX2, enemyY2)));
		boolean openSpotOpen = board.spotOpen(openX, openY);
		if(enemySpotOpen1 && enemySpotOpen2 && openSpotOpen) { //if true, the move is valid)
			this.board.removeChecker(enemyX1,  enemyY1);
			this.board.removeChecker(enemyX2,  enemyY2);
			return true;
		}else {
			return false;
		}
		
		
	}
	
	/*checks to see if a zigzag move is valid: xPt and yPt are the coordinates
	 * of the two enemy checkers that the checker wants to jump over
	 * xMiddle and yMiddle are the coordinate3s of the point in between, you want to make sure that space is open
	 * 
	 * Different from straightDoubleMove() because for this move, you have to check two conditions because the
	 * checker could get to the target spot in two ways vs. only one in straightDoubleMove()
	 */
	public boolean zigzagMoveValid(Player player, int xCoordinate, int yCoordinate,
		int xPt1, int yPt1, int xPt2, int yPt2, int xMiddlePt12, int yMiddlePt12, int xPt3, int yPt3, int xPt4, int yPt4, int xMiddlePt34, int yMiddlePt34){
		if(doubleMoveValid(player, xPt1, yPt1, xPt2, yPt2, xMiddlePt12, yMiddlePt12)) {
			this.board.removeChecker(xPt1, yPt1);
			this.board.removeChecker(xPt2, yPt2);
			return true;
		}else if(doubleMoveValid(player, xPt3, yPt3, xPt4, yPt4, xMiddlePt34, yMiddlePt34)) {
			this.board.removeChecker(xPt3, yPt3);
			this.board.removeChecker(xPt4, yPt4);
			//valid move
			return true;
		}else {
			System.out.println("Not a valid move");
			return false;
		}
	}
	
}
