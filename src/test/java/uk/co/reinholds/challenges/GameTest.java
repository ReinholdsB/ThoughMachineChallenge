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
public class GameTest {

    private Coordinates startCoordinates;
    private Position startPosition;

    private Coordinates coordinates2;
    private Position position2;

    private Coordinates endCoordinates;
    private Position endPosition;

    private Game game;
    private ArrayList<String> instructions;

    @Before
    public void setUp() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("10");
        game = new Game(instructions);

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
        game = new Game(instructions);

        Board board = game.getBoard();
        assertNotNull(board);
        assertEquals(10, board.getBoardSize());
    }

    @Test
    public void shootShip_shouldFailIfMissesShip() throws Exception {
        assertFalse("Should be failed shot", game.shoot(startCoordinates));

        game.placeShip(position2);
        assertFalse("Should be failed shot", game.shoot(startCoordinates));
    }

    @Test
    public void shootShip_shouldSucceedIfHitsShip() throws Exception {
        game.placeShip(startPosition);
        assertTrue("Should be successful hit", game.shoot(startCoordinates));
    }

    @Test
    public void moveShip_shouldMoveToEndPoint_ifItsClear() throws Exception {
        game.placeShip(startPosition);
        game.moveShip(startCoordinates, "MRMLMM".toCharArray());

        System.out.println(game.getCurrentStateOfPlay());

        assertNotNull("Ship should have move to new location", game.getBoard().getShip(endCoordinates));
    }

    @Test
    public void fullSetOfCommands() throws Exception {
        game.placeShip(startPosition);
        game.placeShip(position2);

        game.moveShip(startCoordinates, "MRMLMM".toCharArray());

        game.shoot(position2.getCoordinates());


        assertTrue(game.getCurrentStateOfPlay().contains("(1, 3, N)"));
        assertTrue(game.getCurrentStateOfPlay().contains("(9, 2, E) SUNK"));

//        assertEquals("(1, 3, N)\n(9, 2, E) SUNK",
//                game.getCurrentStateOfPlay());

    }


    @Test
    public void instructionsToPlaceShipsIsRecognisedAndExecutedCorrectly() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("25");
        game = new Game(instructions);
        game.performInstruction("(0, 0, N) (9, 2, E)");
        assertTrue(game.getCurrentStateOfPlay().contains("(0, 0, N)"));
        assertTrue(game.getCurrentStateOfPlay().contains("(9, 2, E)"));

        game.performInstruction("(0, 5, W) (5, 22, S) (23, 2, S)");
        assertTrue(game.getCurrentStateOfPlay().contains("(0, 5, W)"));
        assertTrue(game.getCurrentStateOfPlay().contains("(5, 22, S)"));
        assertTrue(game.getCurrentStateOfPlay().contains("(23, 2, S)"));
    }

    @Test(expected = CoordinatesOutOfBoundsException.class)
    public void instructionsToPlaceOutOfBounds_shouldReturnException() throws Exception {
        instructions = new ArrayList<>();
        instructions.add("10");
        game = new Game(instructions);
        game.performInstruction("(15, 2, S)");
    }

    @Test
    public void instructionsToMoveShipIsRecognisedAndExecutedCorrectly() throws Exception {
        game.performInstruction("(0, 0, N)");

        game.performInstruction("(0, 0) MRMLMM");
        assertEquals("(1, 3, N)", game.getCurrentStateOfPlay());

    }

    @Test
    public void instructionsToShootShipsIsRecognisedAndExecutedCorrectly() throws Exception {
        game.performInstruction("(9, 2, E)");

        game.performInstruction("(9, 2)");
        assertTrue(game.getCurrentStateOfPlay().equals("(9, 2, E) SUNK"));
    }

    @Test(expected = CoordinatesTakenException.class)
    public void whenTryToAddTwoShipsToTheSameLocation_shouldThrowException() throws Exception {
        game.performInstruction("(0, 0, N) (0, 0, N)");
        assertEquals("(0, 0, N)", game.getCurrentStateOfPlay());
    }
}