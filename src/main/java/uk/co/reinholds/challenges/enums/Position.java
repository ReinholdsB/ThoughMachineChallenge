package uk.co.reinholds.challenges.enums;

import uk.co.reinholds.challenges.Coordinates;
import uk.co.reinholds.challenges.exceptions.InvalidMovementRequestException;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class Position {
    private final Coordinates coordinates;
    private Orientation orientation;

    public Position(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    public Position(int xCoordinate, int yCoordinate, Orientation orientation) {
        this.coordinates = new Coordinates(xCoordinate, yCoordinate);
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "(" + coordinates.getxCoordinate() + ", " +
                coordinates.getyCoordinate() + ", " +
                orientation.toString() + ")";
    }

    public Position move(MovementAction movementAction) throws InvalidMovementRequestException {
        switch (movementAction) {
            case L:
                return new Position(coordinates, orientation.rotateLeft());
            case R:
                return new Position(coordinates, orientation.rotateRight());
            case M: {
                return new Position(coordinates.move(orientation), orientation);
            }
            default:
                throw new InvalidMovementRequestException("No such action available");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return !(coordinates != null ? !coordinates.equals(position.coordinates) : position.coordinates != null);

    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        return result;
    }
}
