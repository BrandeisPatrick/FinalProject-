package finalproject;

import java.util.ArrayList;

public class Board_ababcd extends Board {
    public Board_ababcd() {
        super();
    }

    /**
     * This Board is prepared for CheatCode
     * This new Board will set KingCheckers for player X at the start of the game
     *
     *
     * @param color is the identity for Player X (true) or Player O (false)
     * @param checkersInUse is the ArrayList contains all the checkers
     * @param checkerNum  is the number of Checkers will be added
     */
    @Override
    public void setCheckers(boolean color, ArrayList<Checker> checkersInUse, int checkerNum) {
        if(color == true) { //player x
            color = true;
            for(int i = 1; i <= 3; i++) { //iterates through three rows
                if(i%2 != 0) {
                    for(int j = 1; j<= 7; j+=2) {
                        checkersInUse.add(new KingChecker( j, i,true, checkerNum +1));
                        checkerNum ++;
                    }
                }else {
                    for(int j = 2; j<= 8; j+=2) {
                        checkersInUse.add(new KingChecker( j, i,  true, checkerNum +1));
                        checkerNum ++;
                    }
                }
            }
        }else{
            color = false;
            for(int i = 6; i <= 8; i++) {
                if(i%2 != 0) {
                    for(int j = 1; j<=7; j+=2) {
                        checkersInUse.add(new Checker(j, i, false, checkerNum +1));
                        checkerNum ++;
                    }
                }else {
                    for(int j = 2; j<=8; j+=2) {
                        checkersInUse.add(new Checker(j, i, false, checkerNum +1));
                        checkerNum ++;
                    }
                }
            }

        }
    }

}
