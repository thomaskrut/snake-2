import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Food extends GameObject implements DrawableObject {

    List<Point> foodList = new ArrayList<>();

    Maze maze;

    public Food(Maze maze) {
        this.maze = maze;
    }

    public void eatFood(Point foodEaten) {

        Point foodToRemove = new Point();

        for (Point p : foodList) {
            if (p.equals(foodEaten)) {
                foodToRemove = p;
            }
        }

        foodList.remove(foodToRemove);

    }

    public void generateNewFood(Maze maze) {

        if (!foodList.isEmpty()) return;

        Random rand = new Random();
        Point p = new Point();

        while (true) {
            int x = rand.nextInt(Game.GAME_WIDTH);
            int y = rand.nextInt(Game.GAME_HEIGHT);
            p.setLocation(x, y);
            if (!Collisions.checkForCollision(p, maze)) {
                break;
            }

        }
        foodList.add(p);
    }


    @Override
    public List<Point> getPositionsList() {
        return foodList;
    }

    @Override
    public synchronized Point getSinglePosition() {
        if (!foodList.isEmpty()) {
            return foodList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public void draw(Graphics g) {

        for (Point p : foodList) {
            g.setColor(Color.RED);
            g.fillOval(p.x * Game.ELEMENT_SIZE, p.y * Game.ELEMENT_SIZE, Game.ELEMENT_SIZE, Game.ELEMENT_SIZE);
        }

    }


}
