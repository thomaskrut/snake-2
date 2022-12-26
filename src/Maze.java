import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Maze extends GameObject {

    List<Point> walls = new ArrayList<>();

    public void addWall(List<Point> wall) {
        walls.addAll(wall);
    }

    @Override
    public List<Point> getPositionsList() {

        return walls;
    }

    public int hasObstacleAlongAxis(int x, int y, Direction direction) {

        int result = 999;

        switch (direction) {
            case NORTH -> {
                for (Point p : walls) {
                    if (p.x == x && p.y < y) if (result > y - p.y) result = y - p.y;
                }
            }
            case SOUTH -> {
                for (Point p : walls) {
                    if (p.x == x && p.y < y) if (result > p.y - y) result = p.y - y;
                }
            }
            case WEST -> {
                for (Point p : walls) {
                    if (p.x < x && p.y == y) if (result > x - p.x) result = x - p.x;
                }
            }
            case EAST -> {
                for (Point p : walls) {
                    if (p.x > x && p.y == y) if (result > p.x - x) result = p.x - x;
                }
            }
        }

        return result;

    }

}
