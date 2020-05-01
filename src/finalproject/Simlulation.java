package finalproject;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
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

    public Simlulation() throws FileNotFoundException {
    	printHeader();
        setPlayer();
        board = new Board();
       //board.printCheckerCoord();
    }

    //distribute the players and checkers to start the game
    public void setPlayer() {
        System.out.println("Would you like to play the computer(alterantive: another person)? (please reply yes or no)");
        Scanner sc = new Scanner(System.in);
        String str  = sc.next();
        while(!str.equals("yes") && !str.equals("no")){
            System.out.println("Please give a valid answer. Must type \"yes\" or \"no\"");
            str  = sc.next();
        }
       
        if(str.equals("yes")){
        	this.player1 = new Player(true);
            this.player2 = new EPlayer(false);
        	System.out.println("You are " + this.player1);
            System.out.println("The computer is " + this.player2);
        }else{
        	 this.player1 = new Player(true);
             this.player2 = new Player(false);
        	System.out.println("You are " + this.player2);
            System.out.println("The other person is " + this.player1);
        }
        
    }

    //print header method at the start of the program. Displays instructions if the person wants to see them
    public void printHeader() throws FileNotFoundException{
    	Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to this checkers game!");
        System.out.println("Would you like to read the rules of the game? (yes or no)");
        String str  = sc.next();
        while(!str.equals("yes") && !str.equals("no")){
            System.out.println("Please give a valid answer. Must type \"yes\" or \"no\"");
            str  = sc.next();
        }
        if(str.equals("yes")) {
        	Scanner readThis = new Scanner(new File("Instructions.txt"));
        	while(readThis.hasNextLine()) {
        		System.out.println(readThis.nextLine());
        	}
        }else {
        	System.out.println("Ok. Proceeding to game");
        }
        
    }



    public boolean tick(){
        player1.tick(this.board.checkersInUse, this.board);
        if(player1.allPiecesGone(this.board.checkersInUse) == true){
        	System.out.println("Player "+ this.player1 + "has won!");
        	printGameStats();
        	return false;
        }
        player2.tick(this.board.checkersInUse, this.board);
        if(player2.allPiecesGone(this.board.checkersInUse) == true){
        	System.out.println("Player "+ this.player2 + "has won!");
        	printGameStats();
        	return false;
        }
        return true;
    }
    
    public void printGameStats() {
    	System.out.println("Here are the game stats:");
    	System.out.println("Player 1: " + this.player1.getMoves()+ " total moves");
    	System.out.println("Player 1: " + this.player2.getMoves()+ " total moves");
    }

    public void board_ababcd(){
        this.board = new Board_ababcd();
    }

    @Override
    public String toString() {
        return board.toString();
    }

}
