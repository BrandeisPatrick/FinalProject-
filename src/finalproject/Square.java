package finalproject;

public class Square {
    public Checker checker;
    public char color;

    public Square(char color){
        setColor(color);
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
}
