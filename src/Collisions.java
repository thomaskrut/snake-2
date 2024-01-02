import java.awt.*;
import java.util.List;
import java.util.Random;

public class Collisions {

    private static Direction direction = Direction.NORTH;
    public static Direction getNextDirection() {

        switch (direction) {
            case NORTH -> {
                direction = Direction.EAST;
                return Direction.NORTH;
            }
            case EAST -> {
                direction = Direction.SOUTH;
                return Direction.EAST;
            }
            case SOUTH -> {
                direction = Direction.WEST;
                return Direction.SOUTH;
            }
            case WEST -> {
                direction = Direction.NORTH;
                return Direction.WEST;
            }
        }
        return direction;
    }

    public static Direction getNextDirection(Direction currentDirection) {

        Random rand = new Random();

        switch (direction) {
            case NORTH, SOUTH -> {
                if (rand.nextInt(2) == 1) {
                    return Direction.WEST;
                } else {
                    return Direction.EAST;
                }
            }
            case EAST, WEST -> {
                if (rand.nextInt(2) == 1) {
                    return Direction.SOUTH;
                } else {
                    return Direction.NORTH;
                }
            }
        }

        return currentDirection;

    }

    public static int hasObstacleOnAxis(GameObject object, int x, int y, Direction direction) {

        int result = 999;

        switch (direction) {
            case NORTH -> {
                for (Point p : object.getPositionsList()) {
                    if (p.x == x && p.y < y) if (result > y - p.y) result = y - p.y;
                }
            }
            case SOUTH -> {
                for (Point p : object.getPositionsList()) {
                    if (p.x == x && p.y < y) if (result > p.y - y) result = p.y - y;
                }
            }
            case WEST -> {
                for (Point p : object.getPositionsList()) {
                    if (p.x < x && p.y == y) if (result > x - p.x) result = x - p.x;
                }
            }
            case EAST -> {
                for (Point p : object.getPositionsList()) {
                    if (p.x > x && p.y == y) if (result > p.x - x) result = p.x - x;
                }
            }
        }

        return result;

    }

    public static boolean checkForCollision(Point point, GameObject o) {
        return o.getPositionsList().stream().anyMatch(p -> p.equals(point));
    }

}
