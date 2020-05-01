package finalproject;

import java.io.FileNotFoundException;
import java.util.Random;

public class Simlulation_blacksheep extends Simlulation {
    public Simlulation_blacksheep() throws FileNotFoundException{
        super();
    }


    @Override
    public boolean tick(){
        Random rand = new Random();
        int randomInt = rand.nextInt(4);
        for(int i = 1; i <= randomInt; i++) {
            player1.tick(this.board.checkersInUse, this.board);
            if (player1.allPiecesGone(this.board.checkersInUse) == true) {
                System.out.println("Player " + this.player1 + "has won!");
                return false;
            }
        }
        player2.tick(this.board.checkersInUse, this.board);
        if(player2.allPiecesGone(this.board.checkersInUse) == true){
            System.out.println("Player "+ this.player2 + "has won!");
            return false;
        }
        return true;
    }
}
