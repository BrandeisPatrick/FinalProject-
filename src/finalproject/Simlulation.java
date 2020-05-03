package finalproject;
import java.util.Scanner;

/**
 * This class handles all the larger pictures things that 
 * involve multiple other classes. It does jobs related to setting up the game and running it.
 * 
 * Simulation initiates the two players and the board for the game.
 * It also prints a welcome message and executes each player's tick method in one larger tick method.
 */

public class Simlulation {
    public Player player1; //boolean = true, x
    public Player player2; //boolean false, o
    public Board board;

    /**
     * Constructor
     */
    public Simlulation() {
    	printHeader();
        setPlayer();
        board = new Board();
       //board.printCheckerCoord();
    }

    /**
     * distribute the players and checkers to start the game
     */
    public void setPlayer() {
        System.out.println("Would you like to be Player X ? (please reply yes or no)");
        Scanner sc = new Scanner(System.in);
        String str  = sc.next();
        while(!str.equals("yes") && !str.equals("no")){
            System.out.println("Please give a valid answer.");
            str  = sc.next();
        }
        this.player1 = new Player(true);
        this.player2 = new Player(false);
        if(str.equals("yes")){
        	System.out.println("You are " + this.player1);
            System.out.println("The other person is " + this.player2);
        }else{
        	System.out.println("You are " + this.player2);
            System.out.println("The other person is " + this.player1);
        }
        
    }

    /**
     * print header method at the start of the program
     */
    public void printHeader(){
        System.out.println("This is checker game for two people;");
        System.out.println("Player X  will move first;");
        System.out.println("Player O  will move after;");
        System.out.println("In each move, select a checker by giving a coordinate, then give the destination coordinate, separated by a space");
        System.out.println("For example A1 checker move to B2 position is \"A1 B2\"");

        System.out.println();
    }


    /**
     * process players' movement in each turn
     * @return if this the END OF THE GAME
     */
    public boolean tick(){
        player1.tick(this.board.checkersInUse, this.board);
        if(player1.allPiecesGone(this.board.checkersInUse) == true){
        	System.out.println("Player "+ this.player1 + "has won!");
        	return false;
        }
        player2.tick(this.board.checkersInUse, this.board);
        if(player2.allPiecesGone(this.board.checkersInUse) == true){
        	System.out.println("Player "+ this.player2 + "has won!");
        	return false;
        }
        return true;
    }

    /**
     *  Initialize a Board board_ababcd
     *  Used for CheatCode
     */
    public void board_ababcd(){
        this.board = new Board_ababcd();
    }

    /**
     * @return board.toString
     */
    @Override
    public String toString() {
        return board.toString();
    }

}
