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
        board.printCheckerCoord();
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
        System.out.println("Player X (red) will move first;");
        System.out.println("Player O (black) will move after Player X;");
        System.out.println("red checkers will be represented as (X)");
        System.out.println("black checkers will be represented as (O)");
        System.out.println("In each move, select a checker by giving a coordinate;");
        System.out.println("then give the destination coordinate, separated by a space");
        System.out.println("For example E1 checker move to E2 position is \"E1 E2\"");
        System.out.println();
    }

    public void tick(){
        player1.tick(this.board.checkersInUse, this.board);
//      player2.tick(this.board.checkersInUse, this.board);.
    }

    @Override
    public String toString() {
        return board.toString();
    }

}
