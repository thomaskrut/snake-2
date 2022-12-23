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

}
