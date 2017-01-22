package uk.co.reinholds.challenges.exceptions;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class GameRuntimeException extends Exception {
    public GameRuntimeException(String message) {
        super(message);
    }

    public GameRuntimeException(String message, Exception cause) {
        super(message, cause);
    }
}
