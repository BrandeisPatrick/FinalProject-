package finalproject;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayGame {

	public static void main(String[] args) {

		Simlulation sim = new Simlulation();
		System.out.println(sim);
		sim.tick();
		System.out.println(sim);


	/**
		Player player1 = new Player(true);
		Player player2 = new Player(false);

		System.out.println(player1.checkers.get(0).canMove(board, 4, 5, board.checkersInUse));
	*/
		/*
		for(int i = 0; i < player1.checkers.size(); i++ ) {
			board.setChecker(player1.checkers.get(i));
		}
		
		for(int i = 0; i < player2.checkers.size(); i++ ) {
			board.setChecker(player2.checkers.get(i));
		}
		
		board.printCheckersInUse();
		
		player1.tick(console);
		*/
		
		

	}

}
