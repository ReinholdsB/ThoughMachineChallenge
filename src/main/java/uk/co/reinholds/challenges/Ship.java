package uk.co.reinholds.challenges;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class Ship {
    private boolean isSunk = false;

    public boolean isSunk() {
        return isSunk;
    }

    public void sink(){
        this.isSunk = true;
    }
}
