package finalproject;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

/*
 * This is the class where the game is played. It's suppose to be a super simple class that implements 
 * everything from the other classes in just a few lines
 */
public class PlayGame {

	public static void main(String[] args) throws FileNotFoundException{
		Simlulation sim = new Simlulation();
		System.out.println(sim);
		while(sim.tick() == true) {	
		}

		System.out.println("END OF THE GAME");
		System.out.println(sim);
		

	}
}
