package uk.co.reinholds.challenges;


import uk.co.reinholds.challenges.enums.Position;
import uk.co.reinholds.challenges.exceptions.*;
import uk.co.reinholds.challenges.enums.Orientation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class Task {


    public static final String PLACE_SHIPS_INSTRUCTION_SPLIT_REGEX = "( )(?=[(])";
    List<String> instructions;
    private final Board board;
    private static final Pattern PLACE_SHIPS_PATTERN = Pattern.compile("\\((\\d*), (\\d*), ([A-Z])\\)");
    private static final Pattern MOVE_SHIP_PATTERN = Pattern.compile("\\((\\d*), (\\d*)\\) ([A-Z]*)$");
    private static final Pattern SHOOT_SHIP_PATTERN = Pattern.compile("\\((\\d*), (\\d*)\\)");

    public Task(List<String> instructions) {
        assert instructions.get(0) != null;

        Integer boardSize = Integer.valueOf(instructions.remove(0));
        this.instructions = instructions;
        this.board = new Board(boardSize);
    }

    public Board getBoard() {
        return board;
    }

    public String getCurrentStateOfPlay(){
        return board.boardState();
    }

    public boolean shoot(Coordinates coordinates) {
        try {
            Ship ship = board.getShip(coordinates);
            ship.sink();
            return true;
        } catch (ShipNotFoundException e) {
            return false;
        }
    }

    public void placeShip(Position position) throws CoordinatesTakenException, CoordinatesOutOfBoundsException {
        board.placeShip(position);
    }

    public void moveShip(Coordinates startCoordinates, char[] directions) throws InvalidMovementRequestException, ShipNotFoundException, CoordinatesOutOfBoundsException {
        board.moveShip(startCoordinates, directions);
    }

    public void start() {
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            try {
                performInstruction(instruction);
            } catch (GameRuntimeException e) {
                System.out.println("Failed to perform instruction=\""+ instruction + "\", please verify input file!");
                e.printStackTrace();
            }
        }
        System.out.println(board.boardState());
    }

    public void performInstruction(String instruction) throws GameRuntimeException {
        Matcher placeShipMatcher = PLACE_SHIPS_PATTERN.matcher(instruction);
        Matcher moveShipMatcher = MOVE_SHIP_PATTERN.matcher(instruction);
        Matcher shootShipMatcher = SHOOT_SHIP_PATTERN.matcher(instruction);

        if (placeShipMatcher.find()) {
            placeShips(instruction);

        } else if (moveShipMatcher.find()) {
            Integer xCoordinate = Integer.valueOf(moveShipMatcher.group(1));
            Integer yCoordinate = Integer.valueOf(moveShipMatcher.group(2));
            String movements = moveShipMatcher.group(3);

            moveShip(new Coordinates(xCoordinate, yCoordinate), movements.toCharArray());

        } else if (shootShipMatcher.find()) {
            Integer xCoordinate = Integer.valueOf(shootShipMatcher.group(1));
            Integer yCoordinate = Integer.valueOf(shootShipMatcher.group(2));

            shoot(new Coordinates(xCoordinate, yCoordinate));
        }
    }

    private void placeShips(String instruction) throws CoordinatesTakenException, CoordinatesOutOfBoundsException {
        //Need to be able to place multiple ships from one instruction
        String[] split = instruction.split(PLACE_SHIPS_INSTRUCTION_SPLIT_REGEX);
        for (String placeShipInstruction : split) {
            Matcher matcher = PLACE_SHIPS_PATTERN.matcher(placeShipInstruction);

            if (matcher.find()) {
                Integer xCoordinate = Integer.valueOf(matcher.group(1));
                Integer yCoordinate = Integer.valueOf(matcher.group(2));
                Orientation orientation = Orientation.valueOf(matcher.group(3));

                placeShip(new Position(xCoordinate, yCoordinate, orientation));
            }
        }
    }
}
