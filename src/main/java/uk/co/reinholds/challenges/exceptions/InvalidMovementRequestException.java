package uk.co.reinholds.challenges.exceptions;

/**
 * Created by Reinholds on 21/01/2017.
 */
public class InvalidMovementRequestException extends GameRuntimeException {
    public InvalidMovementRequestException(String message) {
        super(message);
    }

    public InvalidMovementRequestException(String message, Exception cause) {
        super(message, cause);
    }
}
