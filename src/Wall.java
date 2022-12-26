import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall extends GameObject implements DrawableObject {

    List<Point> positions = new ArrayList<>();

    int startX;
    int startY;
    int width;
    int height;
    public Wall(Maze maze, int startX, int startY, int length, Alignment alignment) {

        this.startX = startX * Game.ELEMENT_SIZE;
        this.startY = startY * Game.ELEMENT_SIZE;
        int x = startX;
        int y = startY;
        int xfactor = 0;
        int yfactor = 0;

        switch (alignment) {
            case VERTICAL -> {
                yfactor = 1;
                height = length * Game.ELEMENT_SIZE;
                width = Game.ELEMENT_SIZE;
            }
            case HORIZONTAL -> {
                xfactor = 1;
                width = length * Game.ELEMENT_SIZE;
                height = Game.ELEMENT_SIZE;
            }
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
        g.fillRect(startX, startY, width, height);
    }
}
