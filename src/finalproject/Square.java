package finalproject;

/**
 * This is dedicated to creating the appearance of the board
 */

public class Square {
    public static char RED = '\u2B1C';
//    public static char RED = '\u25A1';
//    public static char BLACK = '\u2B1B';
    public static char BLACK = ' ';

    public Checker checker;
    public int x;
    public int y;

    /**
     * Constructor
     */
    public Square(){
    }

    /**
     * set the coordinate of the Square
     * @param x
     * @param y
     */
    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Store a Checker
     * @param checker
     */
    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Checker getChecker() {
        return checker;
    }

    /**
     * Return a String value based on if this Square stores a checker and the Square's coordinate
     * @return
     */
    @Override
    public String toString() {
        if(this.checker != null){  //check if this hold a checker
            return this.checker.toString();
        }
        //Condition: this.checker == null
        if((this.x + this.y) % 2 == 0){
            return String.valueOf(BLACK);
        }else{
            return String.valueOf(RED);
        }
    }
}
