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
     // board.printCheckerCoord();
    }

    //distribute the players
    public void setPlayer() {
        System.out.println("Would you like to be Player X ? (please reply yes or no)");
        Scanner sc = new Scanner(System.in);
        String str  = sc.next();
        while(!str.equals("yes") && !str.equals("no")){
            System.out.println("Please give a valid answer.");
            str  = sc.next();
        }
        if(str.equals("yes")){
        	this.player1 = new Player(true);
            this.player2 = new Player(false);
        }else{
        	this.player1 = new Player(false);
            this.player2 = new Player(true);
        }
        System.out.println("You are " + this.player1);
        System.out.println("The other person is " + this.player2);
        
    }

    //print header method
    public void printHeader(){
        System.out.println("This is checker game for two people;");
        System.out.println("Player X  will move first;");
        System.out.println("Player O  will move after;");
        System.out.println("In each move, select a checker by giving a coordinate, then give the destination coordinate, separated by a space");
        System.out.println("For example A1 checker move to B2 position is \"A1 B2\"");
        System.out.println();
    }

    public void tick(){
        player1.tick(this.board.checkersInUse, this.board);
        player2.tick(this.board.checkersInUse, this.board);
    }

    @Override
    public String toString() {
        return board.toString();
    }

}
