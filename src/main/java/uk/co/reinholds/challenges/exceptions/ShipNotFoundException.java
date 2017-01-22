package uk.co.reinholds.challenges.exceptions;

/**
 * Created by Reinholds on 21/01/2017.
 */
public class ShipNotFoundException extends GameRuntimeException {
    public ShipNotFoundException(String message) {
        super(message);
    }
}
