import java.awt.*;
import java.util.*;
import java.util.List;

public class Snake extends GameObject implements MovableObject, DrawableObject {

    List<BodyElement> body = new ArrayList<>();

    int elementsToGrow;

    private Direction direction = Direction.DOWN;

    public Snake(Point startingPosition, int size) {

        body.add(new BodyElement(null, startingPosition));

        grow(size);

    }

    @Override
    public List<Point> getPositionsList() {
        List<Point> positions = new ArrayList<>();
        for (BodyElement b : body) {
            positions.add(b.position);
        }
        positions.remove(0);
        return positions;
    }

    @Override
    public Point getSinglePosition() {
        return body.get(0).position;
    }

    private BodyElement getTail() {
        return body.get(body.size()-1);
    }


    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }

    }


    public void grow(int numberOfElements) {
        elementsToGrow = numberOfElements;
    }

    @Override
    public void move() {

        for (BodyElement b : body) {
            b.moveElement(direction);
        }

        if (elementsToGrow > 0) {
            body.add(new BodyElement(getTail(), getTail().previousPosition));
            elementsToGrow--;
        }

    }


    public boolean checkForCollision() {

        for (int i = 1; i < body.size(); i++) {

            if (body.get(0).position.equals(body.get(i).position)) {
                return true;
            }

        }

        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        for (BodyElement b : body) {
            g.fillRect(b.position.x*Game.ELEMENT_SIZE, b.position.y*Game.ELEMENT_SIZE, Game.ELEMENT_SIZE, Game.ELEMENT_SIZE);
        }

    }
}


class BodyElement {

    Point position = new Point();

    Point previousPosition = new Point();

    BodyElement nextElement;

    public BodyElement(BodyElement nextElement, Point position) {

        this.nextElement = nextElement;
        this.position.setLocation(position);
    }

    public void moveElement(Direction direction) {

        if (this.nextElement == null) {
            previousPosition.setLocation(position);
            switch (direction) {
                case UP -> position.y--;
                case DOWN -> position.y++;
                case LEFT -> position.x--;
                case RIGHT -> position.x++;
            }
        } else {
            previousPosition.setLocation(position);
            position.setLocation(nextElement.previousPosition);
        }

    }
}
