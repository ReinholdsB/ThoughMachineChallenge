package uk.co.reinholds.challenges.exceptions;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class CoordinatesTakenException extends GameRuntimeException {
    public CoordinatesTakenException(String message) {
        super(message);
    }
}
