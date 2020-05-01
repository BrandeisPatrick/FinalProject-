package finalproject;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class PlayGame_cheatcode {

    public static void main(String[] args) throws FileNotFoundException{

        System.out.println("Do you have cheatcode? Type it ");

        Scanner sc = new Scanner(System.in);
        String cheatcode = sc.next();
        if(cheatcode.equals("blacksheep")){
            Simlulation_blacksheep simlulation_blacksheep = new Simlulation_blacksheep();
            System.out.println(simlulation_blacksheep);
            while(simlulation_blacksheep.tick() == true) {
            }
            System.out.println("END OF THE GAME");
            System.out.println(simlulation_blacksheep);

        }else if(cheatcode.equals("ababcd")){
            Simlulation_ababcd simlulation_ababcd = new Simlulation_ababcd();
            System.out.println(simlulation_ababcd);
            while(simlulation_ababcd.tick() == true) {
            }
            System.out.println("END OF THE GAME");
            System.out.println(simlulation_ababcd);


        }else {
            System.out.println("Sorry you don't have cheatcode; cheatcode can be found the READ_ME File");
            System.out.println();
            System.out.println();
            Simlulation sim = new Simlulation();
            System.out.println(sim);
            while (sim.tick() == true) {
            }
            System.out.println("END OF THE GAME");
            System.out.println(sim);
        }

    }
}
