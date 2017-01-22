package uk.co.reinholds.challenges;

import uk.co.reinholds.challenges.enums.Orientation;

/**
 * Created by Reinholds on 20/01/2017.
 */
public class Coordinates {
    private final long xCoordinate;
    private final long yCoordinate;

    public Coordinates(long xCoordinate, long yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Coordinates move(Orientation orientation) {
        switch (orientation) {
            case N:
                return new Coordinates(xCoordinate, yCoordinate + 1);
            case S:
                return new Coordinates(xCoordinate, yCoordinate - 1);
            case E:
                return new Coordinates(xCoordinate + 1, yCoordinate);
            default:
                return new Coordinates(xCoordinate - 1, yCoordinate);
        }
    }

    public long getxCoordinate() {
        return xCoordinate;
    }

    public long getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return xCoordinate + ", " + yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (xCoordinate != that.xCoordinate) return false;
        return yCoordinate == that.yCoordinate;

    }

    @Override
    public int hashCode() {
        int result = (int) (xCoordinate ^ (xCoordinate >>> 32));
        result = 31 * result + (int) (yCoordinate ^ (yCoordinate >>> 32));
        return result;
    }
}
