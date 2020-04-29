package finalproject;

public class Simlulation_blacksheep extends Simlulation {
    public Simlulation_blacksheep() {
        super();
    }


    @Override
    public boolean tick(){
        for(int i = 1; i <= 4; i++) {
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
