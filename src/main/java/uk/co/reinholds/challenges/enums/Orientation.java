package uk.co.reinholds.challenges.enums;

/**
 * Created by Reinholds on 20/01/2017.
 */
public enum Orientation {
    N, W, S, E;

    public Orientation rotateLeft() {
        switch (this){
            case N : return W;
            case W : return S;
            case S : return E;
            default: return N;
        }
    }

    public Orientation rotateRight() {
        switch (this){
            case N : return E;
            case E : return S;
            case S : return W;
            default: return N;
        }
    }
}
