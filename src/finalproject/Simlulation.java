package finalproject;

import java.util.Scanner;

public class Simlulation {
    public Player player1;
    public Player player2;
    public Board board;

    public Simlulation() {
        setPlayer();
        board = new Board();
    }

    public void tick(){
        player1.tick(this.board.checkersInUse, this.board);
        player2.tick(this.board.checkersInUse, this.board);
        board.setBoard();
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


    @Override
    public String toString() {
        return board.toString();
    }
}
