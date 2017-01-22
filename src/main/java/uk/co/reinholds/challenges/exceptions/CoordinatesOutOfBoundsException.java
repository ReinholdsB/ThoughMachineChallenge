package uk.co.reinholds.challenges.exceptions;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class CoordinatesOutOfBoundsException extends GameRuntimeException {
    public CoordinatesOutOfBoundsException(String message) {
        super(message);
    }
}
