package finalproject;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * This is the class where the game is played. It's suppose to be a super simple class that implements 
 * everything from the other classes in just a few lines
 */
public class PlayGame {

	public static void main(String[] args) {
		Simlulation sim = new Simlulation();
		System.out.println(sim);
		for(int i = 0; i <= 10; i++) {
			sim.tick();	
		}

		System.out.println("END OF THE GAME");
		System.out.println(sim);
		

	}
}
