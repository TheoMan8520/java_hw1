package thegame.app.numgame;

public class UserClass {
    private int leastMove;
    private int currentMove;

    public UserClass() {
        this.leastMove = 100;
        this.currentMove = 0;
    }

    public int getCurrentMove(){
        return this.currentMove;
    }
    public void setCurrentMove(int move) {
        this.currentMove = move;
    }
    public int getLeastMove(){
        return this.leastMove;
    }
    public void setLeastMove(int move){
        this.leastMove = move;
    }
}
