import java.awt.*;
import java.util.*;
import java.util.List;

public class Snake extends GameObject implements MovableObject, DrawableObject {

    List<BodyElement> body = new ArrayList<>();

    int elementsToGrow;

    private Direction currentDirection = Direction.SOUTH;

    public Snake(Point startingPosition, int size) {

        body.add(new BodyElement(null, startingPosition));

        grow(size);

    }

    public Direction getCurrentDirection() {
        return currentDirection;
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


    public void setDirection(Direction newDirection) {
        if (newDirection != null) {

            switch (newDirection){
                case NORTH -> {
                    if (!currentDirection.equals(Direction.SOUTH)) currentDirection = newDirection;
                }
                case SOUTH -> {
                    if (!currentDirection.equals(Direction.NORTH)) currentDirection = newDirection;
                }
                case WEST -> {
                    if (!currentDirection.equals(Direction.EAST)) currentDirection = newDirection;
                }
                case EAST -> {
                    if (!currentDirection.equals(Direction.WEST)) currentDirection = newDirection;
                }
            }


        }

    }


    public void grow(int numberOfElements) {
        elementsToGrow = numberOfElements;
    }

    @Override
    public void move() {

        for (BodyElement b : body) {
            b.moveElement(currentDirection);
        }

        if (elementsToGrow > 0) {
            body.add(new BodyElement(getTail(), getTail().previousPosition));
            elementsToGrow--;
        }

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
                case NORTH -> position.y--;
                case SOUTH -> position.y++;
                case WEST -> position.x--;
                case EAST -> position.x++;
            }
        } else {
            previousPosition.setLocation(position);
            position.setLocation(nextElement.previousPosition);
        }

    }
}
