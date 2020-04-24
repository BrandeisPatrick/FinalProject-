package finalproject;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner console = new Scanner(System.in);
		
		Board board = new Board();
		System.out.print(board);
		
		Player player1 = new Player(true);
		Player player2 = new Player(false);
		
		player1.tick(console);
		
		

	}

}
