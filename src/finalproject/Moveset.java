package finalproject;

class MoveSet implements Comparable<MoveSet>{
    private int ix;
    private int iy;
    private int fx;
    private int fy;
    private Checker checker;
    private double distance;
    private int checkerNum;

    public MoveSet(int ix, int iy, int fx, int fy, Checker checker, int checkerNum) {
        this.ix = ix;
        this.iy = iy;
        this.fx = fx;
        this.fy = fy;
        this.checker = checker;
        this.checkerNum = checkerNum;
        setDistance();
    }

    public void setDistance(){
        double dy = (fy - iy);
        double dx = (fx - ix);
        double dist = Math.sqrt(dy*dy+dx*dx);
        this.distance = dist;
    }

    @Override
    public int compareTo(MoveSet o) {
        return (int) (this.distance - o.distance);
    }

    public int getIx() {
        return this.ix;
    }

    public int getIy() {
        return this.iy;
    }

    public int getFx() {
        return this.fx;
    }

    public int getFy() {
        return this.fy;
    }

    public Checker getChecker() {
        return checker;
    }

    @Override
    public String toString() {
        return "MoveSet{" +
                "ix=" + ix +
                ", iy=" + iy +
                ", fx=" + fx +
                ", fy=" + fy +
                ", checker=" + checker +
                ", distance=" + distance +
                ", checkerNum=" + checkerNum +
                '}';
    }
}
