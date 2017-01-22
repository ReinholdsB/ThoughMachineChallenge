package uk.co.reinholds.challenges;

import org.junit.Before;
import org.junit.Test;
import uk.co.reinholds.challenges.enums.Position;
import uk.co.reinholds.challenges.enums.Orientation;
import uk.co.reinholds.challenges.exceptions.CoordinatesTakenException;
import uk.co.reinholds.challenges.exceptions.ShipNotFoundException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Reinholds on 20/01/2017.
 */
public class BoardTest {

    private static final int BOARD_SIZE = 10;
    private Position position1;
    private Position position2;

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board(BOARD_SIZE);
        position1 = new Position(new Coordinates(0, 0), Orientation.N);
        position2 = new Position(new Coordinates(9, 2), Orientation.W);
    }

    @Test
    public void whenPlaceShip_itShouldGetPlacedOnBoard() throws Exception {
        board.placeShip(position1);

        assertEquals("(0, 0, N)", board.boardState());
    }

    @Test
    public void whenPlaceMultipleShips_theyShouldAllGetPlacedOnBoard() throws Exception {
        board.placeShip(position1);
        board.placeShip(position2);

        assertTrue(board.boardState().contains(position1.toString()));
        assertTrue(board.boardState().contains(position2.toString()));
    }

    @Test(expected = CoordinatesTakenException.class)
    public void whenPlaceMultipleShipsOnSameCoordinates_exceptionsShouldBeExpected() throws Exception {
        board.placeShip(position1);
        board.placeShip(position1);
    }


    @Test
    public void whenGetShip_shouldReturnCorrectShip() throws Exception {
        board.placeShip(position1);
        assertNotNull(board.getShip(position1.getCoordinates()));
    }


    @Test(expected = ShipNotFoundException.class)
    public void whenGetShipWithInvalidCoordinates_shouldThrowShipNotFoundException() throws Exception {
        board.placeShip(position1);
        board.getShip(position2.getCoordinates());
    }


}