package uk.co.reinholds.challenges;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Reinholds on 22/01/2017.
 */
public class CoordinatesTest {

    private Coordinates coordinates = new Coordinates(1, 3);
    private Coordinates matchingCoordinates = new Coordinates(1, 3);
    private Coordinates nonMatchingCoordinates = new Coordinates(3, 3);

    @Test
    public void equalsWorksCorrectly() throws Exception {
        assertEquals(coordinates, coordinates);
        assertEquals(coordinates, matchingCoordinates);
    }

    @Test
    public void hashcodeWorksCorrectly() throws Exception {
        assertEquals(coordinates.hashCode(), coordinates.hashCode());
        assertEquals(coordinates.hashCode(), matchingCoordinates.hashCode());
        assertNotEquals(coordinates.hashCode(), nonMatchingCoordinates.hashCode());
    }
}