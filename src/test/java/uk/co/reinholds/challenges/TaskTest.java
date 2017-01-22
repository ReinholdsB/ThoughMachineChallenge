package uk.co.reinholds.challenges;


import org.junit.Before;
import org.junit.Test;
import uk.co.reinholds.challenges.enums.Orientation;
import uk.co.reinholds.challenges.enums.Position;
import uk.co.reinholds.challenges.exceptions.CoordinatesOutOfBoundsException;
import uk.co.reinholds.challenges.exceptions.CoordinatesTakenException;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by Reinholds on 21/01/2017.
 */
public class TaskTest {

    private Coordinates startCoordinates;
    private Position startPosition;

    private Coordinates coordinates2;
    private Position position2;

    private Coordinates endCoordinates;
    private Position endPosition;

    private Task task;
    private ArrayList<String> instructions;

    @Before
    public void setUp() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("10");
        task = new Task(instructions);

        startCoordinates = new Coordinates(0, 0);
        startPosition = new Position(startCoordinates, Orientation.N);

        coordinates2 = new Coordinates(9, 2);
        position2 = new Position(coordinates2, Orientation.E);

        endCoordinates = new Coordinates(1, 3);
        endPosition = new Position(endCoordinates, Orientation.N);
    }

    @Test
    public void getBoard_returnsValidBoard() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("10");
        task = new Task(instructions);

        Board board = task.getBoard();
        assertNotNull(board);
        assertEquals(10, board.getBoardSize());
    }

    @Test
    public void shootShip_shouldFailIfMissesShip() throws Exception {
        assertFalse("Should be failed shot", task.shoot(startCoordinates));

        task.placeShip(position2);
        assertFalse("Should be failed shot", task.shoot(startCoordinates));
    }

    @Test
    public void shootShip_shouldSucceedIfHitsShip() throws Exception {
        task.placeShip(startPosition);
        assertTrue("Should be successful hit", task.shoot(startCoordinates));
    }

    @Test
    public void moveShip_shouldMoveToEndPoint_ifItsClear() throws Exception {
        task.placeShip(startPosition);
        task.moveShip(startCoordinates, "MRMLMM".toCharArray());

        System.out.println(task.getCurrentStateOfPlay());

        assertNotNull("Ship should have move to new location", task.getBoard().getShip(endCoordinates));
    }

    @Test
    public void fullSetOfCommands() throws Exception {
        task.placeShip(startPosition);
        task.placeShip(position2);

        task.moveShip(startCoordinates, "MRMLMM".toCharArray());

        task.shoot(position2.getCoordinates());


        assertTrue(task.getCurrentStateOfPlay().contains("(1, 3, N)"));
        assertTrue(task.getCurrentStateOfPlay().contains("(9, 2, E) SUNK"));

//        assertEquals("(1, 3, N)\n(9, 2, E) SUNK",
//                game.getCurrentStateOfPlay());

    }


    @Test
    public void instructionsToPlaceShipsIsRecognisedAndExecutedCorrectly() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("25");
        task = new Task(instructions);
        task.performInstruction("(0, 0, N) (9, 2, E)");
        assertTrue(task.getCurrentStateOfPlay().contains("(0, 0, N)"));
        assertTrue(task.getCurrentStateOfPlay().contains("(9, 2, E)"));

        task.performInstruction("(0, 5, W) (5, 22, S) (23, 2, S)");
        assertTrue(task.getCurrentStateOfPlay().contains("(0, 5, W)"));
        assertTrue(task.getCurrentStateOfPlay().contains("(5, 22, S)"));
        assertTrue(task.getCurrentStateOfPlay().contains("(23, 2, S)"));
    }

    @Test(expected = CoordinatesOutOfBoundsException.class)
    public void instructionsToPlaceOutOfBounds_shouldReturnException() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("10");
        task = new Task(instructions);
        task.performInstruction("(15, 2, S)");
    }

    @Test
    public void instructionsToMoveShipIsRecognisedAndExecutedCorrectly() throws Exception {
        task.performInstruction("(0, 0, N)");

        task.performInstruction("(0, 0) MRMLMM");
        assertEquals("(1, 3, N)", task.getCurrentStateOfPlay());

    }

    @Test
    public void instructionsToShootShipsIsRecognisedAndExecutedCorrectly() throws Exception {
        task.performInstruction("(9, 2, E)");

        task.performInstruction("(9, 2)");
        assertTrue(task.getCurrentStateOfPlay().equals("(9, 2, E) SUNK"));
    }

    @Test(expected = CoordinatesTakenException.class)
    public void whenTryToAddTwoShipsToTheSameLocation_shouldThrowException() throws Exception {
        task.performInstruction("(0, 0, N) (0, 0, N)");
        assertEquals("(0, 0, N)", task.getCurrentStateOfPlay());
    }
}