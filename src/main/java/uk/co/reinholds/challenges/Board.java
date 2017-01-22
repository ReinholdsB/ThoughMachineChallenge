package uk.co.reinholds.challenges;

import uk.co.reinholds.challenges.enums.MovementAction;
import uk.co.reinholds.challenges.enums.Position;
import uk.co.reinholds.challenges.exceptions.CoordinatesOutOfBoundsException;
import uk.co.reinholds.challenges.exceptions.CoordinatesTakenException;
import uk.co.reinholds.challenges.exceptions.InvalidMovementRequestException;
import uk.co.reinholds.challenges.exceptions.ShipNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class Board {
    private Map<Position, Ship> shipLocations = new HashMap<>();
    private int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    public void placeShip(Position position) throws CoordinatesTakenException, CoordinatesOutOfBoundsException {
        int maxCoordinateSize = boardSize - 1;
        if (position.getCoordinates().getxCoordinate() > maxCoordinateSize ||
                position.getCoordinates().getyCoordinate() > maxCoordinateSize) {
            throw new CoordinatesOutOfBoundsException(
                    "Coordinates=" + position.getCoordinates().toString() + " are out-of-bounds of the board, boardSize=" + boardSize);
        }
        if (shipLocations.get(position) == null) {
            shipLocations.put(position, new Ship());
        } else {
            throw new CoordinatesTakenException(
                    "Coordinates=" + position.getCoordinates().toString() + " already taken! Ship not placed on board!");
        }
    }

    public String boardState() {
        StringBuilder boardState = new StringBuilder();
        shipLocations.forEach((position, ship) -> {
            if (boardState.length() > 0) {
                boardState.append("\n");
            }
            boardState.append(position.toString());
            if (ship.isSunk()) {
                boardState.append(" SUNK");
            }
        });

        return boardState.toString();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Ship getShip(Coordinates coordinates) throws ShipNotFoundException {
        System.out.println("Retrieving Ship from coordinates=" + coordinates);
        Position position = getPosition(coordinates);
        return getShip(position);
    }

    public Ship getShip(Position position) throws ShipNotFoundException {
        System.out.println("Retrieving Ship from position=" + position);
        Ship ship = shipLocations.get(position);
        if (ship != null) {
            return ship;
        }
        throw new ShipNotFoundException("No ship located at position=\"" + position + "\"");
    }


    private Position getPosition(Coordinates coordinates) throws ShipNotFoundException {
        Optional<Position> positionOptional = shipLocations.keySet()
                .stream()
                .filter((position) -> position.getCoordinates().equals(coordinates))
                .findAny();

        if (positionOptional.isPresent()) {
            return positionOptional.get();
        }
        throw new ShipNotFoundException("Could not find ship at supplied coordinates=\"" + coordinates.toString() + "\"");
    }

    public void moveShip(Coordinates startCoordinates, char[] directions) throws ShipNotFoundException, InvalidMovementRequestException {
        Position startPosition = getPosition(startCoordinates);
        Ship ship = getShip(startPosition);
        if (!ship.isSunk()) {
            Position currentPosition = startPosition;
            for (char c : directions) {
                currentPosition = currentPosition.move(MovementAction.valueOf(String.valueOf(c)));
            }
            shipLocations.remove(startPosition);
            shipLocations.put(currentPosition, ship);
        }
    }
}
