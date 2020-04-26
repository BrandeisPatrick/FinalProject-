package finalproject;

public class Square {
    public static char RED = '\u25A0';
    public static char BLACK = '\u25A1';

    public Checker checker;
    public char color;
    public int x;
    public int y;

    public Square(char color){
        setColor(color);
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public Checker getChecker() {
        return checker;
    }

    public char getColor() {
        return color;
    }

    @Override
    public String toString() {
        if(this.checker != null){
            return this.checker.toString();
        }
        //Condition: this.checker == null
        if((this.x + this.y) % 2 == 0){
            return String.valueOf(RED);
        }else{
            return String.valueOf(BLACK);
        }
    }
}
