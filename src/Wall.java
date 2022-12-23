import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall extends GameObject implements DrawableObject {

    List<Point> positions = new ArrayList<>();
    public Wall(Maze maze, int startX, int startY, int length, Direction direction) {

        int x = startX;
        int y = startY;
        int xfactor = 0;
        int yfactor = 0;

        switch (direction) {
            case UP -> yfactor = -1;
            case DOWN -> yfactor = 1;
            case LEFT -> xfactor = -1;
            case RIGHT -> xfactor = 1;
        }

        for (int i = 0; i < length; i++) {
            positions.add(new Point(x, y));
            x = x + xfactor;
            y = y + yfactor;
        }

        maze.addWall(this.positions);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.gray);
        for (Point p : positions) {
            g.fillRect(p.x*Game.ELEMENT_SIZE, p.y*Game.ELEMENT_SIZE, Game.ELEMENT_SIZE, Game.ELEMENT_SIZE);
        }

    }
}
