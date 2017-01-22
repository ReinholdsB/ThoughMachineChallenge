package uk.co.reinholds.challenges;

import org.junit.Test;
import uk.co.reinholds.challenges.enums.MovementAction;
import uk.co.reinholds.challenges.enums.Position;
import uk.co.reinholds.challenges.enums.Orientation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by Reinholds on 22/01/2017.
 */
public class PositionTest {

    private Position position = new Position(new Coordinates(1, 3), Orientation.N);
    private Position matchingPosition = new Position(new Coordinates(1, 3), Orientation.N);
    private Position matchingPositionWithVariedOrientation = new Position(new Coordinates(1, 3), Orientation.N);
    private Position nonMatchingPosition = new Position(new Coordinates(3, 3), Orientation.N);

    @Test
    public void testToString() throws Exception {
        assertEquals("(1, 3, N)", position.toString());
    }

    @Test
    public void turnLeftWorksCorrectly() throws Exception {
        Coordinates coordinates = new Coordinates(1, 3);

        assertEquals(Orientation.W, new Position(coordinates, Orientation.N).move(MovementAction.L).getOrientation());

        assertEquals(Orientation.S, new Position(coordinates, Orientation.W).move(MovementAction.L).getOrientation());

        assertEquals(Orientation.E, new Position(coordinates, Orientation.S).move(MovementAction.L).getOrientation());

        assertEquals(Orientation.N, new Position(coordinates, Orientation.E).move(MovementAction.L).getOrientation());
    }

    @Test
    public void turnRightWorksCorrectly() throws Exception {
        Position tempPosition = new Position(new Coordinates(1, 3), Orientation.N);

        assertEquals(Orientation.E, new Position(new Coordinates(1, 3), Orientation.N).move(MovementAction.R).getOrientation());
        assertEquals(Orientation.S, new Position(new Coordinates(1, 3), Orientation.E).move(MovementAction.R).getOrientation());
        assertEquals(Orientation.W, new Position(new Coordinates(1, 3), Orientation.S).move(MovementAction.R).getOrientation());
        assertEquals(Orientation.N, new Position(new Coordinates(1, 3), Orientation.W).move(MovementAction.R).getOrientation());
    }

    @Test
    public void moveNorthForwardWorksCorrectly() throws Exception {
        Position tempPosition = new Position(new Coordinates(1, 3), Orientation.N);
        assertEquals(new Coordinates(1, 4), tempPosition.move(MovementAction.M).getCoordinates());
    }
    @Test
    public void moveWestWorksCorrectly() throws Exception {
        Position tempPosition = new Position(new Coordinates(1, 3), Orientation.W);
        assertEquals(new Coordinates(0, 3), tempPosition.move(MovementAction.M).getCoordinates());
    }
    @Test
    public void moveEastWorksCorrectly() throws Exception {
        Position tempPosition =  new Position(new Coordinates(1, 3), Orientation.E);
        assertEquals(new Coordinates(2, 3), tempPosition.move(MovementAction.M).getCoordinates());
    }
    @Test
    public void moveSouthWorksCorrectly() throws Exception {
        Position tempPosition = new Position(new Coordinates(1, 3), Orientation.S);
        assertEquals(new Coordinates(1, 2), tempPosition.move(MovementAction.M).getCoordinates());
    }

    @Test
    public void equalsWorksCorrectly() throws Exception {
        assertEquals(position, position);
        assertEquals(position, matchingPosition);
    }

    @Test
    public void equalsDoesNotCheckOrientation() throws Exception {
        assertEquals(position, matchingPositionWithVariedOrientation);
    }

    @Test
    public void hashcodeWorksCorrectly() throws Exception {
        assertEquals(position.hashCode(), position.hashCode());
        assertEquals(position.hashCode(), matchingPosition.hashCode());
        assertNotEquals(position.hashCode(), nonMatchingPosition.hashCode());
    }

    @Test
    public void hashcodeDoesNotCheckOrientation() throws Exception {
        assertEquals(position.hashCode(), matchingPositionWithVariedOrientation.hashCode());

        assertEquals(position.hashCode(), position.hashCode());
    }

}